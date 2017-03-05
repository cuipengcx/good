package com.jk.controller.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jk.controller.BaseController;
import com.xiaoleilu.hutool.json.JSONObject;

/**
 * @author yangF
 *         Created by JK on 2017/2/7.
 */
@Controller
@RequestMapping(value="/upload", method = { RequestMethod.POST, RequestMethod.GET })
public class FileUploadControl extends BaseController {

//    private static final String BASE_PATH = "/upload/";

    private static final String FILE_UPLOAD_PATH = "/goodmanage/attachment";
    
    private static final long MAX_IMG_SIZE = 1048576;
    
    @Autowired
    HttpServletRequest request; //这里可以获取到request 
    
    @Autowired
    HttpServletResponse response;
    
    @RequestMapping(value="/kindeditorimage", method=RequestMethod.POST)  
    public @ResponseBody String keFileUpload(@RequestParam("imgFile") MultipartFile file){  
        
        return fileupload(file).toString();
        
    }  
    
    @RequestMapping(value="/uploadimage", method=RequestMethod.POST)  
    public @ResponseBody String uploadimage(@RequestParam("imgFile") MultipartFile file){  
        
        return fileupload(file).toString();
        
    }  
    
    @RequestMapping(value="/images", method=RequestMethod.POST)
    @ResponseBody
    public void getImagesURL(String filename){ 
        String absTempPath = new File(FILE_UPLOAD_PATH).getAbsolutePath();

        FileInputStream fis = null;  
        OutputStream os = null;  
        try {  
            fis = new FileInputStream(absTempPath + "/" + filename);  
            os = response.getOutputStream();  
            int count = 0;  
            byte[] buffer = new byte[1024 * 8];  
            while ((count = fis.read(buffer)) != -1) {  
                os.write(buffer, 0, count);  
                os.flush();  
            }  
        } catch (Exception e) {  
//            e.printStackTrace();  
            log.error("读取文件出错！ pageInfo = {}");
            log.error(e.getMessage());
        }  
        try {  
            fis.close();  
            os.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }
    
    public JSONObject fileupload(MultipartFile file){
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
                if(file.getSize() > MAX_IMG_SIZE){
                    throw new Exception("图片超过1M");
                }
                
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
