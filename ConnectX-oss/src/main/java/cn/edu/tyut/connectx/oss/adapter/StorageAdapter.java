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
     * @param bucket 桶名称
     */
    void createBucket(String bucket);

    /**
     * 上传文件
     *
     * @param uploadFile 上传文件
     * @param bucket     桶名称
     * @param objectName 桶中文件夹名称
     */
    void uploadFile(MultipartFile uploadFile, String bucket, String objectName);

    /**
     * 获取所有桶
     *
     * @return 返回所有桶
     */
    List<String> getAllBucket();

    /**
     * 获取当前桶中的所有文件信息
     *
     * @param bucket 桶名称
     * @return 返回当前桶中的所有文件信息
     */
    List<FileInfo> getAllFile(String bucket);

    /**
     * 下载文件
     *
     * @param bucket     桶名称
     * @param objectName 文件夹名称
     * @return 返回流
     */
    InputStream downLoad(String bucket, String objectName);

    /**
     * 删除桶
     *
     * @param bucket 桶名称
     */
    void deleteBucket(String bucket);

    /**
     * 删除文件
     *
     * @param bucket     桶名称
     * @param objectName 文件夹名称
     */
    void deleteObject(String bucket, String objectName);

    /**
     * 拼接url
     *
     * @param bucket     桶名称
     * @param objectName 文件夹名称
     * @return 返回url
     */
    String getUrl(String bucket, String objectName);
}
