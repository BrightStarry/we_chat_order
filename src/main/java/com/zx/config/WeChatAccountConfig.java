package com.zx.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信账户配置参数
 * 该类从yml文件中读取自定义参数,其他类中可以通过注入该类获取
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WeChatAccountConfig {

    private String mpAppId;
    private String mpAppSecret;
}
