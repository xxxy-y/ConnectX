package cn.edu.tyut.connectx.oss.controller;

import cn.edu.tyut.connectx.oss.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/getAllBuckets")
    public String testGetAllBuckets() {
        return fileService.getAllBucket().toString();
    }

    /**
     * 上传文件
     *
     * @return 返回图片上传的oss（http）路径
     */
    @RequestMapping("/upload")
    public String upload(MultipartFile uploadFile, String bucket, String objectName) {
        return fileService.uploadFile(uploadFile, bucket, objectName);
    }
}
