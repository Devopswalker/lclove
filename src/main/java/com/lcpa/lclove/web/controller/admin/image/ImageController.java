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
import com.lcpa.lclove.web.controller.AnnotationController;

import java.io.File;
import java.util.List;

/**
 * Created by shaoheng.huang on 2016/12/14.
 */
@Controller
public class ImageController extends AnnotationController{

    @Autowired
    public ImageService imageService;

    @RequestMapping(value = "/admin/common/uploadImage", method= RequestMethod.POST)
    public String uploadImage(@RequestParam(value = "file", required = false) MultipartFile file,
                              ModelMap model){
        String fileName = file.getOriginalFilename();
        String uploadContext = new Config().getGlobalProp("uploadContext");
        String uploadPath = new Config().getGlobalProp("uploadPath");
        Image image = new Image();
        image.setName(fileName);
        image.setUrl(uploadContext);
        imageService.saveUploadImage(image, file, uploadPath, uploadContext);

        return "redirect:/admin/common/imgList.xhtml";

    }
    
    @RequestMapping(value = "/admin/common/imgList.xhtml")
    public String imgList(ModelMap model){
        List<Image> allImages = imageService.getAllImage();
        model.put("images", allImages);
        return "admin/common/imgList.vm";
    }

    @RequestMapping(value = "/admin/common/deleteImage.xhtml")
    public String removeImage(Integer id, String fileName, ModelMap model){
        String uploadPath = new Config().getGlobalProp("uploadPath");
        if(id == null){
            return showJsonError(model, "ID为空！");
        }
        imageService.removeUploadedImageById(id,fileName, uploadPath);
        return showJsonSuccess(model);
    }
}
