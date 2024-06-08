package cn.edu.tyut.connectx.oss.adapter;

import cn.edu.tyut.connectx.oss.entity.FileInfo;
import cn.edu.tyut.connectx.oss.util.MinioUtil;
import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/7
 */
public class MinioStorageAdapter implements StorageAdapter {
    @Resource
    private MinioUtil minioUtil;

    @Override
    @SneakyThrows
    public Boolean createBucket(String bucketName) {
        return minioUtil.createBucket(bucketName);
    }

    @Override
    @SneakyThrows
    public Boolean deleteBucket(String bucketName) {
        return minioUtil.deleteBucket(bucketName);
    }

    @Override
    @SneakyThrows
    public Boolean updateFile(MultipartFile multipartFile, String bucketName, String objectName) {
        minioUtil.createBucket(bucketName);
        if (objectName != null) {
            return minioUtil.updateFile(multipartFile.getInputStream(), bucketName, objectName + "/" + multipartFile.getName());
        } else {
            return minioUtil.updateFile(multipartFile.getInputStream(), bucketName, multipartFile.getName());
        }
    }

    @Override
    @SneakyThrows
    public InputStream downloadFile(String bucketName, String objectName) {
        return minioUtil.downloadFile(bucketName, objectName);
    }

    @Override
    @SneakyThrows
    public List<String> getAllBucket() {
        return minioUtil.getAllBucket();
    }

    @Override
    @SneakyThrows
    public List<FileInfo> getAllObject(String bucketName) {
        return minioUtil.getAllObject(bucketName);
    }

    @Override
    @SneakyThrows
    public void deleteObject(String bucketName, String objectName) {
        minioUtil.deleteObject(bucketName, objectName);
    }
}
