package com.zcm.server.config;

import org.springframework.boot.web.servlet.*;
import org.springframework.context.annotation.*;
import org.springframework.web.cors.*;
import org.springframework.web.filter.*;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @ClassName: CrossOriginConfig
 * @Author: zcm
 * @Date: 2021/5/12 09:41
 * @Version: 1.0.0
 * @Description: 跨域访问
 */
@Configuration
public class CrossConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路径
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                .allowedOrigins("*")
                //是否允许证书 不再默认开启
                .allowCredentials(true)
                //设置允许的方法
                .allowedMethods("*")
                //跨域允许时间
                .maxAge(3600);
    }

    private CorsConfiguration buildConfig() {
        //1.添加CORS配置信息
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 1) 允许的域,不要写*，否则cookie就无法使用了
        //corsConfiguration.addAllowedOrigin("http://9pbz3j.natappfree.cc");
        corsConfiguration.addAllowedOrigin("*");
        // 2) 是否发送Cookie信息
        corsConfiguration.setAllowCredentials(true);
        // 3) 允许的请求方式
        corsConfiguration.addAllowedMethod("OPTIONS");
        corsConfiguration.addAllowedMethod("HEAD");
        corsConfiguration.addAllowedMethod("GET");
        corsConfiguration.addAllowedMethod("PUT");
        corsConfiguration.addAllowedMethod("POST");
        corsConfiguration.addAllowedMethod("DELETE");
        corsConfiguration.addAllowedMethod("PATCH");
        corsConfiguration.setMaxAge(3600L);
        // 4）允许的头信息
        corsConfiguration.addAllowedHeader("*");
        return corsConfiguration;
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        //2.添加映射路径，我们拦截一切请求
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        //3.返回新的CorsFilter.
        CorsFilter corsFilter = new CorsFilter(source);
        //Filter实例注册
        FilterRegistrationBean bean = new FilterRegistrationBean(corsFilter);
        //4.如果执行顺序有问题时配置，filter执行顺序，从小到大执行
        bean.setOrder(0);
        return bean;
    }
}
