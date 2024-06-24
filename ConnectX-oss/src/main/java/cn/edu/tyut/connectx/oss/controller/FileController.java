package cn.edu.tyut.connectx.oss.controller;

import cn.edu.tyut.connectx.oss.entity.Result;
import cn.edu.tyut.connectx.oss.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/6
 */
@RestController
public class FileController {
    private FileService fileService;

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping("/testGetAllBuckets")
    public String testGetAllBuckets() {
        List<String> allBucket = fileService.getAllBucket();
        return allBucket.get(0);
    }

    @RequestMapping("/getUrl")
    public String getUrl(String bucketName, String objectName) {
        return fileService.getUrl(bucketName, objectName);
    }

    /**
     * 上传文件
     */
    @RequestMapping("/upload")
    public Result<Object> upload(MultipartFile uploadFile, String bucket, String objectName) {
        String url = fileService.uploadFile(uploadFile, bucket, objectName);
        return Result.ok(url);
    }
}
