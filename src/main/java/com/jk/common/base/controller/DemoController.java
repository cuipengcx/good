package com.jk.common.base.controller;

import com.xiaoleilu.hutool.io.IoUtil;
import com.xiaoleilu.hutool.io.resource.ClassPathResource;
import com.xiaoleilu.hutool.util.CharsetUtil;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

/**
 * SpringBoot jar方式打包时,下载jar包内的文件和响应jar包内文本文件内容的例子
 * Created by JK on 2017/6/28.
 */
@Controller
public class DemoController {

    /***************************spring版****************************************/
    /**
     * 下载文件
     * @return
     */
    @GetMapping("/getFile1")
    public ResponseEntity<byte[]> getFile() throws IOException {

        Resource resource=  new org.springframework.core.io.ClassPathResource ("quartz.properties");

        byte[] b = IoUtil.readBytes(resource.getInputStream());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "quartz.properties");
        return new ResponseEntity<byte[]>(b, headers, HttpStatus.OK);
    }

    /**
     * 读取文件内容并响应
     * @return
     * @throws IOException
     */
    @GetMapping("/getFileToString1")
    public ResponseEntity<String> getFileToSting() throws IOException {

        Resource resource=  new org.springframework.core.io.ClassPathResource("quartz.properties");

        String result = IoUtil.read(resource.getInputStream(), CharsetUtil.CHARSET_UTF_8);

        return new ResponseEntity<String>(result, HttpStatus.OK);
    }


    /***************************Hutool版****************************************/

    /**
     * 下载文件
     * @return
     * @throws IOException
     */
    @GetMapping("/getFile2")
    public ResponseEntity<byte[]> getFile2() throws IOException {
        ClassPathResource resource=  new ClassPathResource("quartz.properties");

        byte[] b = IoUtil.readBytes(resource.getStream());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "quartz.properties");
        return new ResponseEntity<byte[]>(b, headers, HttpStatus.OK);
    }

    /**
     * 读取文件内容并响应
     * @return
     * @throws IOException
     */
    @GetMapping("/getFileToString2")
    public ResponseEntity<String> getFileToString2() throws IOException {

        ClassPathResource resource=  new ClassPathResource("quartz.properties");

        String result = IoUtil.read(resource.getStream(), CharsetUtil.CHARSET_UTF_8);

        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

}
