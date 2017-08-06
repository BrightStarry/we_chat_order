package com.zx.converter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.zx.domain.OrderDetail;
import com.zx.dto.OrderDTO;
import com.zx.enums.ResultEnum;
import com.zx.exception.SellException;
import com.zx.form.OrderForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 转换
 */
@Slf4j
public class OrderForm2OrderDTOConverter {
    /**
     * 转换
     * @return
     */
    public static OrderDTO convert(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        //先将一些属性复制
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerName(orderForm.getName());

        /**
         * 将items（json串） 转为List<OrderDetail>格式
         */
        List<OrderDetail> orderDetailList = null;
        Gson gson = new Gson();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (JsonSyntaxException e) {
            log.error("【对象转换】错误，String={}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
