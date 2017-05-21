package com.jk.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate客户端配置
 */
@Configuration
public class RestTemplateConfig{

    /**
     * 通过自动配置的RestTemplateBuilder创建自己需要的RestTemplate实例。自动配置的RestTemplateBuilder会确保应用到RestTemplate实例的HttpMessageConverters是合适的。
     * RestTemplateBuilder包含很多有用的方法，可以用于快速配置一个RestTemplate, 默认使用的是SimpleClientHttpRequestFactory(jdk自带的HttpURLConnection)
     * 例如，你可以使用builder.basicAuthorization("user", "password").build()添加基本的认证支持（BASIC auth）
     * @return
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder
                .setReadTimeout(5000) //ms
                .setConnectTimeout(15000) //ms
                .build();
    }



    /****************************************spring传统 java config配置方式***************************************************/
    //    @Bean
//    public RestTemplate restTemplate(ClientHttpRequestFactory factory){
//        RestTemplate restTemplate =  new RestTemplate();
//
//        //配置MessageConverter转换器
//        List<HttpMessageConverter<?>> httpMessageConverterList = new ArrayList<>();
//
//        httpMessageConverterList.add(new FormHttpMessageConverter());
//        httpMessageConverterList.add(new MappingJackson2XmlHttpMessageConverter());
//        httpMessageConverterList.add(new MappingJackson2HttpMessageConverter());
//        httpMessageConverterList.add(new StringHttpMessageConverter());
//
//        restTemplate.setMessageConverters(httpMessageConverterList);
//        restTemplate.setRequestFactory(factory);
//        return restTemplate;
//    }

//    @Bean
//    public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
//        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
//        factory.setReadTimeout(5000);//ms
//        factory.setConnectTimeout(15000);//ms
//        return factory;
//    }
}