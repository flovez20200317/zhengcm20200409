package com.zcm.server.config;

import com.baomidou.mybatisplus.extension.plugins.*;
import com.baomidou.mybatisplus.extension.plugins.inner.*;
import org.mybatis.spring.annotation.*;
import org.springframework.context.annotation.*;

/**
 * @program: mx-album-cloud
 * @ClassName MybatisPlusConfig
 * @Description
 * @Author zcm
 * @Date 2021/5/6 16:50
 * @Version V1.0
 */
@Configuration
@MapperScan("com.zcm.mapper")
public class MybatisPlusConfig {

    /**
     * 功能描述: <br>
     * 分页插件
     *
     * @return com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
     * @Author: FJW
     * @Version: 1.0.0
     * @Param:
     * @Date: 2021/5/11 14:10
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mp = new MybatisPlusInterceptor();
        mp.addInnerInterceptor(new PaginationInnerInterceptor());
        return mp;
    }
}
