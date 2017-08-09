package com.zx.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 微信
 * 如果自己写的话，是这么写，可以使用github上的微信轮子
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
@Deprecated
public class WeiXinController {

    /**
     * 用户同一授权后，进入该回调方法，能从该回调方法中获取用户code
     * @param code
     */
    @GetMapping("auth")
    public void auth(@RequestParam("code")String code) {
        log.info("进入auth方法");
        log.info("code={}",code);
        String url = "";//TODO 微信中用code获取token的url
        //向该url发送请求，获取返回的用户token、openid等信息
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        log.info("response={}",response);
    }
}
