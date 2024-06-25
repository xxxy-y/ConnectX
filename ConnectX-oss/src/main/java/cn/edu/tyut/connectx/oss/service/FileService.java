package cn.edu.tyut.connectx.oss.service;

import cn.edu.tyut.connectx.oss.adapter.StorageAdapter;
import cn.edu.tyut.connectx.oss.entity.FileInfo;
import org.jetbrains.annotations.NotNull;
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
     * 获取文件路径
     */
    public String getUrl(String bucketName, String objectName) {
        return storageAdapter.getUrl(bucketName, objectName);
    }

    /**
     * 上传文件
     */
    public String uploadFile(@NotNull MultipartFile uploadFile, String bucket, String objectName) {
        storageAdapter.uploadFile(uploadFile, bucket, objectName);
        objectName = objectName + "/" + uploadFile.getOriginalFilename();
        return storageAdapter.getUrl(bucket, objectName);
    }

    /**
     * 获取桶下的所有文件
     *
     * @param bucket 桶名称
     */
    public List<FileInfo> getAllFiles(String bucket) {
        return storageAdapter.getAllFile(bucket);
    }
}
