package cn.edu.tyut.connectx.oss.entity;

import lombok.Data;

/**
 * @Author 吴庆涛
 * @DATE 2024/6/6
 */
@Data
public class FileInfo {
    private String fileName;
    private String etag;
    private Long size;
    private Boolean latestFlag;
    private Boolean dirFlag;
}
