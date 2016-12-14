package com.lcpa.lclove.web.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import com.lcpa.lclove.support.Config;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * Created by shaoheng.huang on 2016/12/14.
 */
@Controller
public class UploadController {

    @RequestMapping(value = "/upload.do")
    public String upload(HttpServletRequest request, ModelMap model) throws IllegalStateException, IOException {
        long  startTime=System.currentTimeMillis();
        String uploadPath = new Config().getGlobalProp("uploadPath");
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        ArrayList fileUrls = new ArrayList<String>();
        if(multipartResolver.isMultipart(request))
        {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            //获取multiRequest 中所有的文件名
            Iterator iter=multiRequest.getFileNames();

            while(iter.hasNext())
            {
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null)
                {
                    String path = uploadPath + file.getOriginalFilename();
                    //上传
                    file.transferTo(new File(path));
                    fileUrls.add(path);
                }

            }

        }
        long  endTime=System.currentTimeMillis();
        System.out.println("方法三的运行时间：" + String.valueOf(endTime - startTime) + "ms");
        model.addAttribute("fileUrl", fileUrls.get(0));

        return "admin/uploadresult.jsp";
    }
}
