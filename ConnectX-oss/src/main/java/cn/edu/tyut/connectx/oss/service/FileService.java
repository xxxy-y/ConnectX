package cn.edu.tyut.connectx.oss.service;

import cn.edu.tyut.connectx.oss.adapter.StorageAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/7
 */
@Service
public class FileService {
    private StorageAdapter storageAdapter;

    @Autowired
    public void setStorageService(StorageAdapter storageAdapter) {
        this.storageAdapter = storageAdapter;
    }

    /**
     * 列出所有桶
     *
     * @return 返回所有桶名称
     */
    public List<String> getAllBucket() {
        return storageAdapter.getAllBucket();
    }

    /**
     * 上传文件
     *
     * @param multipartFile 上传文件
     * @param bucketName    桶名称
     * @param objectName    文件名称
     * @return Boolean 是否成功上传文件
     */
    public String uploadFile(MultipartFile multipartFile, String bucketName, String objectName) {
        storageAdapter.uploadFile(multipartFile, bucketName, objectName);
        objectName = objectName + "/" + multipartFile.getOriginalFilename();
        return storageAdapter.getUrl(bucketName, objectName);
    }
}
