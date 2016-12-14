package com.lcpa.lclove.web.controller;

import com.lcpa.lclove.model.Image;
import com.lcpa.lclove.service.ImageService;
import com.lcpa.lclove.support.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by shaoheng.huang on 2016/12/14.
 */
@Controller
@RequestMapping(value = "/")
public class ImageController {

    @Autowired
    public ImageService imageService;

    @RequestMapping(value = "/uploadImage.do")
    public String uploadImage(@RequestParam(value = "file", required = false) MultipartFile file,
                              Image image, ModelMap model){
        image.setPosition(1);
        image.setDescription("this is test");
        String fileName = file.getOriginalFilename();

        int id = imageService.saveImage(image);
        String uploadPath = new Config().getGlobalProp("uploadPath");
        String newFileName = String.valueOf(id) + "_" + fileName;
        String uploadFileUrl = new Config().getGlobalProp("uploadContext") + newFileName;
        File targetFile = new File(uploadPath, newFileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        image.setImgSrc(uploadFileUrl);
        image.setUrl(uploadFileUrl);
        image.setFileName(fileName);
        imageService.updateImageSrc(image);
        List<Image> allImages = imageService.getAllImage();
        model.addAllAttributes(allImages);
        return "admin/uploadtest.jsp";

    }

    @RequestMapping(value = "/test")
    public String test(@ModelAttribute Image image, ModelMap model){
        String a = image.getDescription();
        return "";
    }
}
