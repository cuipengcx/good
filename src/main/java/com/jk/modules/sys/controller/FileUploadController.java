package com.jk.modules.sys.controller;

import com.jk.common.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @className: FileUploadControl
 * @packageName: com.jk.modules.sys.controller
 * @description: 文件上传(包括文件和图片)
 * @author: cuiP
 * @date: 2017/8/2 JK
 * @version: V1.0.0
 */
@Controller
@RequestMapping(value="/upload")
public class FileUploadController extends BaseController {

    private static final String FILE_UPLOAD_PATH = "/goodmanage/attachment";
    
    private static final long MAX_IMG_SIZE = 1048576;

    
//    @RequestMapping(value="/kindeditorimage", method=RequestMethod.POST)
//    public @ResponseBody String keFileUpload(@RequestParam("imgFile") MultipartFile file){
//
//        return fileupload(file).toString();
//
//    }


    @ResponseBody
    @PostMapping(value="/images")
    public String uploadImage(HttpServletRequest request, @RequestParam("file") MultipartFile file){

        //使用 uuid 作为filename
        UUID uuid = UUID.randomUUID();
        String name= uuid.toString();

        //生成文件的绝对路径
        String absTempPath = new File(FILE_UPLOAD_PATH).getAbsolutePath();

        //上传文件的文件名
        String filename = file.getOriginalFilename();

        //上传文件的扩展名
        String fileExt = filename.substring(filename.lastIndexOf(".")+1);

        //上传完毕后文件访问路径uri
        String serverurl = request.getRequestURL().substring(0,request.getRequestURL().indexOf(request.getRequestURI()));

        if (!file.isEmpty()) {

                File dest = new File(absTempPath + "/" + name + "." + fileExt);

                try {
                    file.transferTo(dest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return serverurl;
        }
//        return fileupload(file, request).toString();
        return "";
    }
}
