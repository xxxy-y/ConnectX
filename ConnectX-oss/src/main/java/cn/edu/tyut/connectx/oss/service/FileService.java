package cn.edu.tyut.connectx.oss.service;

import cn.edu.tyut.connectx.oss.adapter.StorageAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/7
 */
@Service
public class FileService {
    private StorageAdapter storageAdapter;

    @Autowired
    public void setStorageService(StorageAdapter storageAdapter) {
        this.storageAdapter = storageAdapter;
    }

    /**
     * 列出所有桶
     *
     * @return 返回所有桶名称
     */
    public List<String> getAllBucket() {
        return storageAdapter.getAllBucket();
    }
}
