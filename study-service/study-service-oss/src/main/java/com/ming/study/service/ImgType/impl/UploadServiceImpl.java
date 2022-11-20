package com.ming.study.service.ImgType.impl;

import com.ming.study.service.UploadService;
import com.ming.study.utils.AliOSSUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author: ziming
 * @Time: 2022/11/9 16:39
 */
@Service
public class UploadServiceImpl implements UploadService {

    /**
     * 文件上传
     *
     * @param file
     * @param filePath
     * @return
     * @throws IOException
     */
    @Override
    public String upload(MultipartFile file, String filePath) throws IOException {
        List<String> list = AliOSSUtil.listFileName(filePath.substring(0, filePath.length() - 1));
        if (list.size() > 0)
            AliOSSUtil.deleteFiles(list);
        return AliOSSUtil.upload(file, filePath);
    }

    /**
     * 获取文件夹下所有文件
     *
     * @param filePath
     * @return
     */
    @Override
    public List<String> getUrl(String filePath) {
        if (filePath != null)
            try {
                return AliOSSUtil.listFileUrl(filePath);
            } catch (Exception e) {
                return null;
            }
        return null;
    }
}
