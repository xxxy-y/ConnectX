package cn.edu.tyut.connectx.auth.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页请求实体
 *
 * @Author 吴庆涛
 * @DATE 2024/5/29
 */
@Data
public class PageInfo implements Serializable {
    private Integer pageNo = 1;
    private Integer pageSize = 20;

    public Integer getPageNo() {
        if (pageNo == null || pageNo < 1) {
            return 1;
        }
        return pageNo;
    }

    public Integer getPageSize() {
        if (pageSize == null || pageSize < 1) {
            return 20;
        }
        return pageSize;
    }
}
