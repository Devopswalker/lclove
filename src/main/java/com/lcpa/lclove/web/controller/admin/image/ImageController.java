package com.lcpa.lclove.web.controller.admin.image;

import com.lcpa.lclove.model.Image;
import com.lcpa.lclove.service.ImageService;
import com.lcpa.lclove.support.Config;

import org.apache.commons.collections.CollectionUtils;
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
    
    @RequestMapping(value = "/imglist.xhtml")
    public String imgList(ModelMap model){
        List<Image> allImages = imageService.getAllImage();
        //TODO:Only Demo
        if(CollectionUtils.isEmpty(allImages)){
        	for (int i = 0; i < 5 ; i++) {
				Image img = new Image();
				img.setId(i);
				img.setFileName("fileName" + i);
				img.setImgSrc("http://www.src" + i + ".com");
				img.setDescription("SRC:http://www.src" + i + ".com");
				allImages.add(img);
			}
        }
        model.put("images", allImages);
        return "admin/common/imgList.vm";

    }

    @RequestMapping(value = "/test", method= RequestMethod.POST)
    public String test(@ModelAttribute Image image, ModelMap model){
        String a = image.getDescription();
        return "";
    }
}