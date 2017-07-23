package com.zx.controller;

import com.zx.domain.ProductCategory;
import com.zx.domain.ProductInfo;
import com.zx.service.CategoryService;
import com.zx.service.ProductService;
import com.zx.utils.ResultVOUtil;
import com.zx.vo.ProductInfoVO;
import com.zx.vo.ProductVO;
import com.zx.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品控制层
 * Created by 97038 on 2017-07-23.
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list() {
        //1.查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        //2.查询类目（一次性查询,in）
        //提取出productInfoList中的每个元素的id到categoryTypeList
        List<Integer> categoryTypeList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        //查询出所有类目
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //3.数据拼装

        //返回对象中的data
        List<ProductVO> productVOList = new ArrayList<>();
        //循环类目,每个类目是一个ProductVO
        productCategoryList.forEach(productCategory -> {
            //创建VOc
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());//设置name
            productVO.setCategoryType(productCategory.getCategoryType());//设置type
            //该类目下的所有 productInfoVO 集合
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            //循环商品，取出该类目下所有的商品,并将其转为VO实体类
            //这里用了java8的stream写法，不过可能比传统写法要多循环一次
            productInfoList.forEach(productInfo -> {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    //创建VO对象
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    //将原对象的属性 复制到 vo对象中
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    //将vo对象 添加 到一个list中
                    productInfoVOList.add(productInfoVO);
                }
            });

            productVO.setProductInfoVOList(productInfoVOList);

            //构建好ProductVO后，添加到productVOList
            productVOList.add(productVO);
        });

        //总的返回对象
        return ResultVOUtil.success(productVOList);
    }
}
