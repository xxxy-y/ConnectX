package cn.edu.tyut.controller;

import cn.edu.tyut.service.FileService;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.Data;
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
    @NacosValue(value = "${storage.service.type}", autoRefreshed = true)
    private String type;

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/getAllBuckets")
    public String testGetAllBuckets() {
        return fileService.getAllBucket().toString();
    }

    @GetMapping("/testNacos")
    public String testNacos() {
        return type;
    }
}
