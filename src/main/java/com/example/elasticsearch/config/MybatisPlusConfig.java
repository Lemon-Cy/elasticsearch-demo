package com.example.elasticsearch.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Title CorsFilter
 * @Package cn.net.cse.containerdatavisualapi.filter
 * @Description Mybatis-plus分页拦截器
 * @Author Joshua
 * @Date 2020/12/17 18:18
 */
@Configuration
public class MybatisPlusConfig {
	    @Bean
	    public MybatisPlusInterceptor mybatisPlusInterceptor() {
	        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
	        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
	        return interceptor;
	    }
}

