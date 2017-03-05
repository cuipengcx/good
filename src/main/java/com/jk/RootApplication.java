package com.jk;

import com.jk.util.MyMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.MultipartConfigElement;
import java.io.File;

/**
 *
 * Created by JK on 2017/1/17.
 */
@SpringBootApplication
@EnableTransactionManagement // 开启注解事务管理，等同于xml配置文件中的 <tx:annotation-driven />
@MapperScan(basePackages = "com.jk.mapper", markerInterface = MyMapper.class)
public class RootApplication {
    public static final String MAX_FILE_SIZE = "1MB";
    public static final String MAX_REQUEST_SIZE = "1MB";
    public static final String FILE_SIZE_THRESHOLD = "1MB";

    private static final String FILE_UPLOAD_PATH = "/goodmanage/attachment";
    
    public static void main(String[] args) {
//        System.setProperty("spring.devtools.restart.enabled","false");
        SpringApplication.run(RootApplication.class, args);
    }
    
    @SuppressWarnings("deprecation")
    @Bean
    MultipartConfigElement multipartConfigElement() {
        String absTempPath = new File(FILE_UPLOAD_PATH).getAbsolutePath();

        File attachpath = new File(absTempPath);

        if(!attachpath.exists()){
            attachpath.mkdirs();
        }

        MultipartConfigFactory  factory = new MultipartConfigFactory();
//        //设置文件最大限制1M
//        factory.setMaxFileSize(MAX_FILE_SIZE);
//        factory.setMaxRequestSize(MAX_REQUEST_SIZE);
//        factory.setFileSizeThreshold(FILE_SIZE_THRESHOLD);
        //设置templete路径
        factory.setLocation(absTempPath);
        return factory.createMultipartConfig();
    }

//
//    @Bean  
//    @SuppressWarnings("deprecation")
//    public MultipartConfigElement multipartConfigElement() {  
//        MultipartConfigFactory factory = new MultipartConfigFactory();  
//        factory.setLocation("/attachment/kindeditor");
//        factory.setMaxFileSize("128KB");  
//        factory.setMaxRequestSize("128KB");  
//        return factory.createMultipartConfig();  
//    }  

    
    /**
     * 增加string 转date转换器
     * @return
     */
//    @Bean
//    public Converter<String, Date> addNewConvert() {
//        return new Converter<String, Date>() {
//            @Override
//            public Date convert(String source) {
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                Date date = null;
//                try {
//                    date = sdf.parse((String) source);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                return date;
//            }
//        };
//    }
}
