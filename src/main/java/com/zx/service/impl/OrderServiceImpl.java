package com.zx.service.impl;

import com.zx.domain.OrderMaster;
import com.zx.domain.ProductInfo;
import com.zx.dto.OrderDTO;
import com.zx.enums.ResultEum;
import com.zx.exception.SellException;
import com.zx.repository.OrderDetailRepository;
import com.zx.repository.OrderMasterRepository;
import com.zx.service.OrderService;
import com.zx.service.ProductService;
import com.zx.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 订单
 */
@Service
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
    public OrderDTO create(OrderDTO orderDTO) {
        //生成订单主表id
        String orderId = KeyUtil.generateUniqueKey();
        //定义总价对象，默认为0
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        //1.查询商品(数量、价格(为了安全价格不能从前端传入))
        orderDTO.getOrderDetailList().forEach(orderDetail -> {
            //查询商品
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            //如果商品不存在，抛出商品不存在异常
            if(productInfo == null){
                throw new SellException(ResultEum.PRODUCT_NOT_EXIST);
            }
            //2.计算总价,每次递增，加上 查询出的商品价格乘以商品数量
            orderAmount.add(productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())));
            //3.设置好id，
            orderDetail.setDetailId(KeyUtil.generateUniqueKey());
            orderDetail.setOrderId(orderId);
            //4.将productInfo中的属性值拷贝到orderDetail中
            BeanUtils.copyProperties(productInfo,orderDetail);
        });
        //5.保存所有 订单详情数据
        orderDetailRepository.save(orderDTO.getOrderDetailList());
        //6.保存订单主表数据
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMasterRepository.save(orderMaster);
        //4.减库存



        return null;
    }

    @Override
    public OrderMaster findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
