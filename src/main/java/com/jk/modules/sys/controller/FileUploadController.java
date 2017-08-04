package com.jk.modules.sys.controller;

import com.feilong.core.DatePattern;
import com.jk.common.base.controller.BaseController;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.http.HttpUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: FileUploadControl
 * @packageName: com.jk.modules.sys.controller
 * @description: 文件上传(包括文件和图片)
 * @author: cuiP
 * @date: 2017/8/2 JK
 * @version: V1.0.0
 */
@Slf4j
@Controller
@RequestMapping(value="/upload")
public class FileUploadController extends BaseController {

    private static final long MAX_IMG_SIZE = 1048576;

    @Value("${repositoryPath}")
    public String REPOSITORY_PATH;
    @Value("${imageUrl}")
    public String IMAGE_URL;
    @Value("${docUrl}")
    public String DOC_URL;

    // 允许上传的格式
    private static final String[] IMAGE_TYPE = new String[] { "image/gif", "image/jpeg", "image/bmp", "image/jpg", "image/png" };

    /*
     * @methodName: uploadImage
     * @param: [request, file]
     * @description: 上传图片
     * @return: java.lang.String
     * @author: cuiP
     * @date: 2017/8/4 13:17
     * @version: V1.0.0
     */
    @ResponseBody
    @PostMapping(value="/images")
    public ResponseEntity<Map<String, Object>> uploadImage(HttpServletRequest request, @RequestParam("file") MultipartFile file){
        Map<String, Object> resultMap = new HashMap<>();

        if(file.isEmpty()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //文件类型
        String fileMimeType = HttpUtil.getMimeType(file.getOriginalFilename());

        boolean flag = false;
        for (String s : IMAGE_TYPE) {
            if (s.equalsIgnoreCase(fileMimeType)) {
                flag = true;
                break;
            }
        }

        //图片格式不允许上传
        if(!flag){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        String path = getFilePath(file.getOriginalFilename(), "images");

        //图片数据库存储路径
        String imageUrl =StringUtils.replace(StrUtil.subSuf(path, REPOSITORY_PATH.length()), "\\", "/");
        resultMap.put("imageUrl", imageUrl);

        File newFile = new File(path);
        try {
            // 写文件到磁盘
            file.transferTo(newFile);
            return ResponseEntity.status(HttpStatus.OK).body(resultMap);
        } catch (IOException e) {
            log.error("上传文件错误！{}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /*
     * @methodName: getFilePath
     * @param: sourceFileName 源文件名
     * @param: typeFolder  上传文件分类目录
     * @description: 生成上传文件的路径
     * @return: java.lang.String
     * @author: cuiP
     * @date: 2017/8/4 13:11
     * @version: V1.0.0
     */
    private String getFilePath(String sourceFileName, String typeFolder) {
        String baseFolder = REPOSITORY_PATH + File.separator + typeFolder;
        Date nowDate = new Date();
        // yyyy/MM/dd
        String fileFolder = baseFolder + File.separator + DateUtil.format(nowDate, DatePattern.yyyy)
                + File.separator + DateUtil.format(nowDate, DatePattern.MM) + File.separator
                + DateUtil.format(nowDate, "dd");

        File file = new File(fileFolder);

        if (!file.isDirectory()) {
            // 如果目录不存在，则创建目录
            file.mkdirs();
        }

        // 生成新的文件名
        String fileName = DateUtil.format(nowDate, DatePattern.TIMESTAMP_WITH_MILLISECOND)
                + RandomUtils.nextInt(100, 9999) + "." + StringUtils.substringAfterLast(sourceFileName, ".");
        return fileFolder + File.separator + fileName;
    }
}
