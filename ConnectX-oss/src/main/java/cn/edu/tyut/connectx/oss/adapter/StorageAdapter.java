package cn.edu.tyut.connectx.oss.adapter;

import cn.edu.tyut.connectx.oss.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/7
 */
public interface StorageAdapter {
    /**
     * 创建桶
     *
     * @param bucketName 桶名称
     * @return Boolean 是否成功创建桶
     */
    Boolean createBucket(String bucketName);

    /**
     * 删除桶
     *
     * @param bucketName 桶名称
     * @return Boolean 是否删除成功
     */
    Boolean deleteBucket(String bucketName);

    /**
     * 上传文件
     *
     * @param multipartFile 上传文件
     * @param bucketName    桶名称
     * @param objectName    文件名称
     * @return Boolean 是否成功上传文件
     */
    Boolean uploadFile(MultipartFile multipartFile, String bucketName, String objectName);

    /**
     * 下载文件
     *
     * @param bucketName 桶名称
     * @param objectName 文件名称
     * @return 下载流
     */
    InputStream downloadFile(String bucketName, String objectName);

    /**
     * 列出所有桶，返回的是名称
     *
     * @return 返回列出的名称
     */
    List<String> getAllBucket();

    /**
     * 列出当前桶的所有文件名称
     *
     * @param bucketName 桶名称
     * @return 返回桶中的文件名称
     */
    List<FileInfo> getAllObject(String bucketName);

    /**
     * 删除文件
     *
     * @param bucketName 桶名称
     * @param objectName 文件名称
     */
    void deleteObject(String bucketName, String objectName);

    /**
     * 获取文件的url
     *
     * @param bucketName 桶的名称
     * @param objectName 文件名称
     * @return 返回对应文件的url
     */
    String getUrl(String bucketName, String objectName);
}
