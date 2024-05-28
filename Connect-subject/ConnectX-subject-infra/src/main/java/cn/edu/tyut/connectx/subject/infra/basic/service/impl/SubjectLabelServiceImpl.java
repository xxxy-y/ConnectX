package cn.edu.tyut.connectx.subject.infra.basic.service.impl;

import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectLabel;
import cn.edu.tyut.connectx.subject.infra.basic.mapper.SubjectLabelDao;
import cn.edu.tyut.connectx.subject.infra.basic.service.SubjectLabelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 题目标签表(SubjectLabel)表服务实现类
 *
 * @author makejava
 * @since 2024-05-27 13:54:58
 */
@Slf4j
@Service("subjectLabelService")
public class SubjectLabelServiceImpl implements SubjectLabelService {
    @Resource
    private SubjectLabelDao subjectLabelDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectLabel queryById(Long id) {
        return this.subjectLabelDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param subjectLabel 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectLabel insert(SubjectLabel subjectLabel) {
        this.subjectLabelDao.insert(subjectLabel);
        return subjectLabel;
    }

    /**
     * 修改数据
     *
     * @param subjectLabel 实例对象
     */
    @Override
    public boolean update(SubjectLabel subjectLabel) {
        int update = this.subjectLabelDao.update(subjectLabel);
        return update > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.subjectLabelDao.deleteById(id) > 0;
    }

    @Override
    public Integer add(SubjectLabel subjectLabel) {
        return subjectLabelDao.insert(subjectLabel);
    }

    @Override
    public List<SubjectLabel> batchQueryById(List<Long> categoryIdList) {
        return subjectLabelDao.batchQueryById(categoryIdList);
    }
}
