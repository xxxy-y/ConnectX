package cn.edu.tyut.util;

import cn.edu.tyut.entity.FileInfo;
import io.minio.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Minio文件操作工具
 *
 * @Author 吴庆涛
 * @DATE 2024/6/6
 */
@Component
public class MinioUtil {
    private MinioClient minioClient;

    @Autowired
    public void setMinioClient(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    /**
     * 创建桶
     *
     * @param bucketName 桶名称
     * @return Boolean 是否成功创建桶
     * @throws Exception 抛出异常
     */
    public Boolean createBucket(String bucketName) throws Exception {
        boolean flag = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!flag) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            return true;
        }
        return false;
    }

    /**
     * 删除桶
     *
     * @param bucketName 桶名称
     * @return Boolean 是否删除成功
     * @throws Exception 异常
     */
    public Boolean deleteBucket(String bucketName) throws Exception {
        boolean flag = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (flag) {
            minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
            return true;
        }
        return false;
    }

    /**
     * 上传文件
     *
     * @param inputStream 输入流
     * @param bucketName  桶名称
     * @param objectName  文件名称
     * @return Boolean 是否成功上传文件
     * @throws Exception 抛出异常
     */
    public Boolean updateFile(InputStream inputStream, String bucketName, String objectName) throws Exception {
        ObjectWriteResponse response = minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(inputStream, -1, Integer.MAX_VALUE).build());
        return !StringUtils.isNotEmpty(response.etag());
    }

    /**
     * 下载文件
     *
     * @param bucketName 桶名称
     * @param objectName 文件名称
     * @return 下载流
     * @throws Exception 抛出异常
     */
    public InputStream downloadFile(String bucketName, String objectName) throws Exception {
        return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }

    /**
     * 列出所有桶，返回的是名称
     *
     * @return 返回列出的名称
     * @throws Exception 抛出异常
     */
    public List<String> getAllBucket() throws Exception {
        List<Bucket> buckets = minioClient.listBuckets();
        return buckets.stream().map(Bucket::name).collect(Collectors.toList());
    }

    /**
     * 列出当前桶的所有文件名称
     *
     * @param bucketName 桶名称
     * @return 返回桶中的文件名称
     * @throws Exception 抛出异常
     */
    public List<FileInfo> getAllObject(String bucketName) throws Exception {
        Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).build());
        List<FileInfo> list = new ArrayList<>();
        for (Result<Item> result : results) {
            Item item = result.get();
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileName(item.objectName());
            fileInfo.setEtag(item.etag());
            fileInfo.setSize(item.size());
            fileInfo.setLatestFlag(item.isLatest());
            fileInfo.setDirFlag(item.isDir());
            list.add(fileInfo);
        }
        return list;
    }

    /**
     * 删除文件
     *
     * @param bucketName 桶名称
     * @param objectName 文件名称
     * @throws Exception 抛出异常
     */
    public void deleteObject(String bucketName, String objectName) throws Exception {
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }
}
