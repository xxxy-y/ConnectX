package cn.edu.tyut.connectx.oss.adapter;

import cn.edu.tyut.connectx.oss.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/7
 */
public class AliStorageAdapter implements StorageAdapter {
    @Override
    public Boolean createBucket(String bucketName) {
        return null;
    }

    @Override
    public Boolean deleteBucket(String bucketName) {
        return null;
    }

    @Override
    public Boolean updateFile(MultipartFile multipartFile, String bucketName, String objectName) {
        return null;
    }

    @Override
    public InputStream downloadFile(String bucketName, String objectName) {
        return null;
    }

    @Override
    public List<String> getAllBucket() {
        return List.of("ALiYun");
    }

    @Override
    public List<FileInfo> getAllObject(String bucketName) {
        return List.of();
    }

    @Override
    public void deleteObject(String bucketName, String objectName) {

    }
}
