package com.imooc.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "wechat")//注入properties中开头为wechat的属性
public class WechatAccountConfig {
        private String mpAppId;

        private String mpAppSecret;
}