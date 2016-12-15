package com.lcpa.lclove.service;

import com.lcpa.lclove.dao.ImageMapper;
import com.lcpa.lclove.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void updateImageSrc(Image image){
        imageMapper.updateImageSrc(image);
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
