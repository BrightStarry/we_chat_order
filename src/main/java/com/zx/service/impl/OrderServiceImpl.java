package com.zx.service.impl;

import com.zx.converter.OrderMaster2OrderDTOConverter;
import com.zx.domain.OrderDetail;
import com.zx.domain.OrderMaster;
import com.zx.domain.ProductInfo;
import com.zx.dto.CartDTO;
import com.zx.dto.OrderDTO;
import com.zx.enums.OrderStatusEnum;
import com.zx.enums.PayStatusEnum;
import com.zx.enums.ResultEnum;
import com.zx.exception.SellException;
import com.zx.repository.OrderDetailRepository;
import com.zx.repository.OrderMasterRepository;
import com.zx.service.OrderService;
import com.zx.service.ProductService;
import com.zx.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductService productService;
    /**
     * 创建 订单
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        //生成订单主表id
        String orderId = KeyUtil.generateUniqueKey();
        //定义总价对象，默认为0
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
//        //减库存需要传入的对象 这样写会把两个两个业务混合在一起，不太好
//        List<CartDTO> cartDTOList = new ArrayList<>();
        //1.查询商品(数量、价格(为了安全价格不能从前端传入))
        for(OrderDetail orderDetail : orderDTO.getOrderDetailList()){
            //查询商品
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            //如果商品不存在，抛出商品不存在异常
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算总价,每次递增，加上 查询出的商品价格乘以商品数量
            orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
            //3.设置好id，
            orderDetail.setDetailId(KeyUtil.generateUniqueKey());
            orderDetail.setOrderId(orderId);
            //4.将productInfo中的属性值拷贝到orderDetail中
            BeanUtils.copyProperties(productInfo,orderDetail);
            //5.拼装CartDTO对象
//            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
//            cartDTOList.add(cartDTO);
        }
        //5.保存所有 订单详情数据
        orderDetailRepository.save(orderDTO.getOrderDetailList());
        //6.保存订单主表数据
        OrderMaster orderMaster = new OrderMaster();
        //属性拷贝
        orderDTO.setOrderAmount(orderAmount);
        orderDTO.setOrderId(orderId);
        orderDTO.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderDTO.setPayStatus(PayStatusEnum.WAIT.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMasterRepository.save(orderMaster);
        //4.减库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    /**
     * 查询单个订单
     * @param orderId
     * @return
     */
    @Override
    public OrderDTO findOne(String orderId) {
        //查询主表
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        //如果为空
        if(orderMaster == null)
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        //查询详情集合
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList))
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        //创建dto对象
        OrderDTO orderDTO = new OrderDTO();
        //将主表信息拷贝到dto对象
        BeanUtils.copyProperties(orderMaster,orderDTO);
        //将集合放入
        orderDTO.setOrderDetailList(orderDetailList);
        //返回
        return orderDTO;
    }

    /**
     * 查询某用户订单列表
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        //查询某个用户的所有订单主表信息
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        //将orderMasterList转为DTOList
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());
        //新建要返回的page
        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());

        return orderDTOPage;
    }

    /**
     * 取消一个订单
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {

        //判断订单状态,如果不是新订单，无法退款
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【取消订单】订单状态不正确，orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //修改订单状态
        //先修改，再拷贝到OrderMaster中保存到数据库
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【取消订单】更新失败，orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //返还库存
        //如果商品详情为空，抛出异常
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【取消订单】订单无商品详情，orderDTO={}",orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        //转为购物车对象，传入
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList()
                .stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        //增加库存
        productService.increaseStock(cartDTOList);

        //如果已支付，退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            //TODO
        }
        return orderDTO;
    }

    /**
     * 完结订单
     * @param orderDTO
     * @return
     */
    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态,是否为新下单
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【完结订单】订单状态不正确,orderId={},orderDTO={}",orderDTO.getOrderId(),orderDTO);
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【完结订单】更新失败，orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    /**
     * 支付订单
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【支付订单】订单状态不正确,orderId={},orderDTO={}",orderDTO.getOrderId(),orderDTO);
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("【支付订单】支付状态不正确,orderId={},orderDTO={}",orderDTO.getOrderId(),orderDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【支付订单】更新失败，orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }
}
