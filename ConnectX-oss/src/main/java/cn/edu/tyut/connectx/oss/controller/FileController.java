package cn.edu.tyut.connectx.oss.controller;

import cn.edu.tyut.connectx.oss.entity.FileInfo;
import cn.edu.tyut.connectx.oss.entity.Result;
import cn.edu.tyut.connectx.oss.service.FileService;
import com.google.common.base.Preconditions;
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

    /**
     * 列出桶中的文件
     *
     * @param bucket 桶的名称
     * @return 返回列出的文件信息
     */
    @RequestMapping("/listFiles")
    public Result<Object> listFiles(String bucket) {
        Preconditions.checkNotNull(bucket, "bucket is null");
        List<FileInfo> allFiles = fileService.getAllFiles(bucket);
        return Result.ok(allFiles);
    }

    @RequestMapping("/downloadFile")
    public Result<Object> downloadFile(String bucket, String objectName) {
        Preconditions.checkNotNull(bucket, "bucket is null");
        Preconditions.checkNotNull(objectName, "objectName is null");
        // TODO minio下载文件未完成
        return null;
    }
}
