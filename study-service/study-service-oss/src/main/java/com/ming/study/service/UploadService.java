package com.ming.study.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author: ziming
 * @Time: 2022/11/9 16:38
 */
public interface UploadService {

    public String upload(MultipartFile file,String filePath) throws IOException;
    public List<String> getUrl(String filePath);
}
