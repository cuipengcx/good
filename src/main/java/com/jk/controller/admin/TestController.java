/** 
 * Project Name:good-manage 
 * File Name:TestController.java 
 * Package Name:com.jk.controller.admin 
 * Date:2017年2月10日下午5:10:21 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.jk.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/** 
 * ClassName:TestController <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Date:     2017年2月10日 下午5:10:21 <br/> 
 * @author   dfz
 * @version   
 * @since    JDK 1.8 
 * @see       
 */

@Controller
public class TestController {
	Logger logger=Logger.getLogger(this.getClass());
	 public static final String ROOT = "upload-dir";  	
	 @RequestMapping("/file")
	    public String file(){
	       return"/file";
	  }

	    /**
	     * 文件上传具体实现方法;
	     * @param file
	     * @return
	     */
	    @RequestMapping("/upload")
	    @ResponseBody
	    public String handleFileUpload(@RequestParam("file")MultipartFile file){
	       if(!file.isEmpty()){
	           try {	        	   	 
	        	     String fileName = file.getOriginalFilename();// 获取文件名
	        	     logger.info("上传的文件名为：" + fileName);        	     
	        	     String suffixName = fileName.substring(fileName.lastIndexOf("."));// 获取文件的后缀名
	        	     logger.info("上传的后缀名为：" + suffixName);
	        	     String filePath = "E:/test/";	        	     
	        	     fileName = UUID.randomUUID() + suffixName;// 解决中文问题，liunx下中文路径，图片显示问题
	        	     File dest = new File(filePath + fileName);	        	     
	        	     if (!dest.getParentFile().exists()) {// 检测是否存在目录
	        	         dest.getParentFile().mkdirs();
	        	     }
	        	     try {
	        	         file.transferTo(dest);
	        	         return "上传成功";
	        	     } catch (IllegalStateException e) {
	        	         e.printStackTrace();
	        	     } catch (IOException e) {
	        	         e.printStackTrace();
	        	     }
	        	   	        	     
	        	/*第二种方法-----开始*/     
//	        	  System.out.println("============11111111111111=================="+file.getOriginalFilename());
//	              BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("E:/test/"+file.getOriginalFilename())));
//	              out.write(file.getBytes());
//	              out.flush();
//	              out.close();
	           /*第二种方法------*/
	           } catch (Exception e) {
	              e.printStackTrace();
	              return"上传失败,"+e.getMessage();
	           }
	           return"上传成功";
	       }else{
	           return"上传失败，因为文件是空的.";
	       }
	    }
	    
	    
	    
}
  