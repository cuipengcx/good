package com.jk.config.converter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Date;

/**
 * @packageName: com.jk.config.converter
 * @description: 自定义类型转换器
 * @author: cuiP
 * @date: 2017/8/14 0:50
 * @version: V1.0.0
 */
@Slf4j
@Configuration
public class ConverterConfig {

    public static final String[] DATE_PATTERNS = new String[] { "yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss" };

    /**
     * string转date转换器
     * @return
     */
    @Bean
    public Converter<String, Date> stringToDate() {
        return new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                Date date = null;
                try {
                    date = DateUtils.parseDate(source,DATE_PATTERNS);
                } catch (ParseException e) {
                    log.error("字符串转换器成日期类型错误!{}", e);
                }
                return date;
            }
        };
    }
}
