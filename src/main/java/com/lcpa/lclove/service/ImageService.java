package com.lcpa.lclove.service;

import com.lcpa.lclove.dao.ImageMapper;
import com.lcpa.lclove.model.Image;
import com.lcpa.lclove.util.DateUtil;
import com.lcpa.lclove.vo.Paging;
import com.lcpa.lclove.vo.QueryParameter;
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

    public void saveUploadImage(Image image, MultipartFile file, String uploadPath, String uploadContext){
        imageMapper.insert(image);
        String imageSuffix = image.getName().substring(image.getName().lastIndexOf("."));
        String uploadFileName = String.valueOf(image.getId()) + DateUtil.timeMillis() + imageSuffix;
//        String uploadFileName = String.valueOf(image.getId()) +"_" + image.getName();
        String uploadImageUrl = uploadContext + uploadFileName;
        image.setUrl(uploadImageUrl);
        image.setFileName(uploadFileName);
        imageMapper.update(image);

        File targetFile = new File(uploadPath, uploadFileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        //save file to server
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            throw new RuntimeException("upload occur error!");
        }
    }

    public void removeUploadedImageById(Integer id, String fileName, String uploadPath){
        File targetFile = new File(uploadPath, fileName);
        imageMapper.deleteByPrimaryKey(id);
        if (targetFile.exists()){
            targetFile.delete();
        }

    }

    public List<Image> getAllImage(Integer pageNo, Integer pageSize){
        Paging paging = new Paging(pageNo, pageSize);
        QueryParameter queryParameter = new QueryParameter(paging,null);
        return imageMapper.selectAll(queryParameter);
    }
    public Paging getAllImagePaging(Integer pageNo, Integer pageSize){
        QueryParameter queryParameter = new QueryParameter(null,null);
        List<Image> articles = imageMapper.selectAll(queryParameter);
        int totalSize = articles.size();
        int total = totalSize/pageSize;
        int lastPages = totalSize%pageSize;
        if (lastPages > 0){
            total += 1;
        }
        Paging paging = new Paging(pageNo, pageSize);
        paging.setTotal(total);
        return paging;
    }
}
