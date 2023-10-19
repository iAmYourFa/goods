package com.wang.goods.controller;

import com.wang.goods.common.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/common")
public class CommonController {


    @Value("${goods.basePath}")
    private String basePath;
    /**
     * 将图片上传到服务器
     *      修改图片昵称
     *      图片保存到指定服务器路径
     *      返回图片名称
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public CommonResult<String> upload(MultipartFile file){

        //原始文件名
        String originalFilename = file.getOriginalFilename();
        //原始文件后缀 .jpg
        String suffix = Objects.requireNonNull(originalFilename).substring(originalFilename.indexOf("."));
        //新文件名
        String newFilename = UUID.randomUUID() + suffix;
        System.out.println(newFilename);
        //判断图片所在包的是否存在，不存在则创建
        File dir = new File(basePath);
        if (!dir.exists()){
            boolean mkdirs = dir.mkdirs();
        }
        try {
            file.transferTo(new File(basePath + newFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return CommonResult.success(newFilename);
    }

    /**
     *   headers: {
     *       'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
     *     },
     *     url: '/common/download',
     *     method: 'get',
     *     params
     */
    @GetMapping("/download")
    public void download(HttpServletResponse httpServletResponse, String name){


        ServletOutputStream fileOutputStream = null;
        FileInputStream fileInputStream = null;
        try {
            httpServletResponse.setContentType("image/jpeg");
            File file = new File(basePath + name);

            fileInputStream = new FileInputStream(file);
            fileOutputStream = httpServletResponse.getOutputStream();

            byte [] bytes = new byte[1024];
            int readLength;

            while ((readLength = fileInputStream.read(bytes))!=-1){
                    fileOutputStream.write(bytes, 0, readLength);
                    fileOutputStream.flush();
            }
            fileInputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
