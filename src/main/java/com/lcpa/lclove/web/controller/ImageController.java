package com.lcpa.lclove.web.controller;

import com.lcpa.lclove.model.Image;
import com.lcpa.lclove.service.ImageService;
import com.lcpa.lclove.support.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * Created by shaoheng.huang on 2016/12/14.
 */
@Controller
@RequestMapping(value = "/")
public class ImageController {

    @Autowired
    public ImageService imageService;

    @RequestMapping(value = "/uploadImage.do", method= RequestMethod.POST)
    public String uploadImage(@RequestParam(value = "file", required = false) MultipartFile file,
                              Image image, ModelMap model){
        String fileName = file.getOriginalFilename();
        String uploadContext = new Config().getGlobalProp("uploadContext");
        String uploadPath = new Config().getGlobalProp("uploadPath");
        image.setFileName(fileName);
        imageService.saveUploadImage(image, file, uploadContext, uploadPath);

        List<Image> allImages = imageService.getAllImage();
        model.addAllAttributes(allImages);
        return "admin/uploadtest.jsp";

    }

    @RequestMapping(value = "/test", method= RequestMethod.POST)
    public String test(@ModelAttribute Image image, ModelMap model){
        String a = image.getDescription();
        return "";
    }
}
