package com.jk.modules.sys.controller;

import com.jk.common.base.controller.BaseController;
import com.xiaoleilu.hutool.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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

    private static final String FILE_UPLOAD_PATH = "/jkfa/attachment";
    
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
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getName());
        return fileupload(file, request).toString();
    }

    
    private JSONObject fileupload(MultipartFile file, HttpServletRequest request){
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
        
        JSONObject obj = new JSONObject();
        
        if (!file.isEmpty()) {  
            try {  

                //超过1M，字节数超过 1048576
//                if(file.getSize() > MAX_IMG_SIZE){
//                    throw new Exception("图片超过1M");
//                }
                
                File dest = new File(absTempPath + "/" + name + "." + fileExt); 
                
                file.transferTo(dest);
                
                //以json形式返回url，在富文本中显示
                obj.put("error", 0);
                obj.put("url", serverurl + "/upload/images?filename=" + name + "." + fileExt);
                obj.put("dataurl", "/upload/images?filename=" + name + "." + fileExt);
                
                return obj;
            } catch (Exception e) {  
                obj.put("error",1);
                obj.put("message","图片文件上传出错或上传图片超过1M");
                return obj;
            }  
        } else {  
            obj.put("error",1);
            obj.put("message","上传图片文件为空");
            return obj;
        }  
    }
}
