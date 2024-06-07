package cn.edu.tyut.controller;

import cn.edu.tyut.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
