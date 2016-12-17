package com.lcpa.lclove.service;

import com.lcpa.lclove.dao.ImageMapper;
import com.lcpa.lclove.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * Created by shaoheng.huang on 2016/12/14.
 */
@Service
public class ImageService {

    @Autowired
    public ImageMapper imageMapper;

    public int saveImage(Image image){
        return imageMapper.insert(image);
    }
    public void saveUploadImage(Image image, MultipartFile file,
                               String uploadContext, String uploadPath){
        imageMapper.insert(image);

        String uploadedFileName = String.valueOf(image.getId()) + "_" + image.getFileName();
        String uploadImageUrl = uploadContext + uploadedFileName;
        image.setImgSrc(uploadImageUrl);
        imageMapper.updateImageSrc(image);

        File targetFile = new File(uploadPath, uploadedFileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO throw a exception, transaction will not commit.delete action is not needed?
            imageMapper.deleteByPrimaryKey(image.getId());
        }
    }

    public void saveImages(List<Image> imageList){
        imageMapper.insertImages(imageList);
    }

    public List<Image> getImageByPosition(Integer position){
        return imageMapper.selectByPosition(position);
    }

    public List<Image> getAllImage(){
        return imageMapper.selectAll();
    }
}
