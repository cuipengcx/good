package com.jk.common.util;

import com.xiaoleilu.hutool.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @package: com.jk.common.util
 * @description: 文件16进制头信息校验工具,处理上传附件,校验是否合法
 * 由于客户端的任何输入都是不可靠的，非法用户可能通过修改文件后缀名绕过前端框架的校验而上传可执行脚本或者木马病毒到服务器
 * 从而拿到服务器的操作权限,所以服务器必须对上传的文件进行校验过滤
 * 另一种方案：从环境上来处理 对文件目录设置权限 杀毒软件处理，或者云服务器
 * @author: cuiP
 * @date: 2017/8/11 13:44
 * @version: V1.0.0
 */
@Slf4j
public class FileValidateUtil {

    //记录各个常见文件头信息及对应的文件类型
    private static Map<String, String> FILE_TYPES = new ConcurrentHashMap<String, String>();

    static {
        // images
        FILE_TYPES.put("FFD8FFE0", ".jpg");
        FILE_TYPES.put("89504E47", ".png");
        FILE_TYPES.put("47494638", ".gif");
        FILE_TYPES.put("49492A00", ".tif");
        FILE_TYPES.put("424D228C",".bmp"); //16色位图(bmp)
        FILE_TYPES.put("424D8240",".bmp"); //24位位图(bmp)
        FILE_TYPES.put("424D8E1B",".bmp"); //256色位图(bmp)

        //PS和CAD
        FILE_TYPES.put("38425053", ".psd");
        FILE_TYPES.put("41433130", ".dwg"); // CAD
        FILE_TYPES.put("25215053",".ps");

        //办公文档类
        FILE_TYPES.put("D0CF11E0", ".doc .ppt .xls"); //ppt、doc、xls
        FILE_TYPES.put("504B0304", ".docx .pptx .xlsx .jar .zip");//pptx、docx、xlsx

        //txt
        //注意由于文本文档录入内容过多，则读取文件头时较为多变,故特殊处理只要是以.txt后缀结尾的不进行文件头信息校验都可以允许上传

        FILE_TYPES.put("7B5C7274", ".rtf"); // 日记本

        FILE_TYPES.put("25504446", ".pdf");

        //视频或音频类
        FILE_TYPES.put("3026B275",".wma");
        FILE_TYPES.put("52494646", ".wav .avi");  // wav与avi相同
        FILE_TYPES.put("3026B275", ".wmv .asf"); // wmv与asf相同
        FILE_TYPES.put("4D546864", ".mid");
        FILE_TYPES.put("2E524D46", ".rmvb .rm"); // rmvb/rm相同
        FILE_TYPES.put("464C5601", ".flv .f4v"); // flv与f4v相同
        FILE_TYPES.put("000001BA", ".mpg");
        FILE_TYPES.put("000001B3", ".mpg");
        FILE_TYPES.put("6D6F6F76", ".mov");

        //压缩包
        FILE_TYPES.put("52617221", ".rar");
        FILE_TYPES.put("1F8B0800", ".gz");

        //程序文件
        FILE_TYPES.put("3C3F786D", ".xml");
        FILE_TYPES.put("23546869", ".ini");
        FILE_TYPES.put("3C21444F", ".html");
        FILE_TYPES.put("7061636B", ".java");
        FILE_TYPES.put("3C254020", ".jsp");
        FILE_TYPES.put("4D5A9000", ".exe");


        FILE_TYPES.put("46726F6D", ".eml"); // 邮件
        FILE_TYPES.put("5374616E", ".mdb");//Access数据库文件

        FILE_TYPES.put("null", "null");
        FILE_TYPES.put("", "");
    }

    /**
     * 校验上传文件是否合法
     * 1. 校验文件后缀扩展名是否合法
     * 2. 校验上传文件的头信息，防止非法用户通过更改文件后缀名绕过第一步校验
     * @param file 上传的文件
     * @param allowTypes  允许上传的文件后缀名集合
     * @return
     */
    public static boolean validateType(MultipartFile file, String[] allowTypes) {
        boolean flag = false;
        if(!file.isEmpty()){

            //1.校验文件后缀扩展名是否合法
            //文件的真实名字
            String realName = file.getOriginalFilename();
            String suffix = "";
            if(StrUtil.isNotEmpty(realName)){
                //文件后缀
                suffix = StrUtil.subSuf(realName, realName.lastIndexOf("."));
                for (String s : allowTypes) {
                    if (s.equalsIgnoreCase(suffix)) {
                        flag = true;
                        break;
                    }
                }
            }

            //2. 校验上传文件的头信息，防止非法用户通过更改文件后缀名绕过第一步校验
            if(flag){
                try {
                    //根据文件头信息前四位(2个算1位)key获取对应的真实的扩展名
                    String realSuffix = getRealFileType(file.getInputStream(), 4);
                    //判断根据头文件信息获取的文件后缀是否和第一步根据上传文件获取的后缀名一致
                    flag = StrUtil.isNotEmpty(realSuffix) && realSuffix.toLowerCase().contains(suffix);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /**
     * 解析文件真实的后缀名
     * @param is 文件输入流
     * @param length 读取的长度
     * @return
     */
    private static String getRealFileType(InputStream is, int length){
        byte[] bytes = new byte[length];
        if(is != null){
            try {
                is.read(bytes, 0, bytes.length);
            } catch (IOException e) {
                log.error("解析文件真实的后缀名失败! e = {}", e);
            }
        }
        String headerInfo = getHeaderInfo(bytes);
        System.out.println(headerInfo);
        return FILE_TYPES.get(headerInfo);
    }

    /**
     * 得到上传文件的文件头
     * @param bytes
     * @return
     */
    private static String getHeaderInfo(byte[] bytes){
        return bytesToHexString(bytes);
    }

    /**
     * 将字节数组转换为二进制字符串
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder =new StringBuilder();
        if (src ==null || src.length <= 0) {
            return null;
        }
        for (int i = 0;i < src.length; i++) {
            // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
            int v =src[i] & 0xFF;
            String hv = Integer.toHexString(v).toUpperCase();
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
