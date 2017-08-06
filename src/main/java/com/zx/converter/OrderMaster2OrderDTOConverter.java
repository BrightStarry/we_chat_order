package com.zx.converter;

import com.zx.domain.OrderMaster;
import com.zx.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 转换器
 * 订单主表 转 订单DTO
 */
public class OrderMaster2OrderDTOConverter {
    /**
     * 一对一 转换
     * @return
     */
    public static OrderDTO convert(OrderMaster orderMaster){
        //新建要转成的DTO
        OrderDTO orderDTO = new OrderDTO();
        //将原对象的属性复制过去
        BeanUtils.copyProperties(orderMaster, orderDTO);
        //返回
        return orderDTO;
    }

    /**
     * list 转换
     * @param orderMasterList
     * @return
     */
    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream()
                .map(e -> convert(e)).collect(Collectors.toList());
    }
}
