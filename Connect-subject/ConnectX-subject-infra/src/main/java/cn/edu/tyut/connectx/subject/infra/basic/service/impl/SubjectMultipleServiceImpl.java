package cn.edu.tyut.connectx.subject.infra.basic.service.impl;

import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectMultiple;
import cn.edu.tyut.connectx.subject.infra.basic.mapper.SubjectMultipleDao;
import cn.edu.tyut.connectx.subject.infra.basic.service.SubjectMultipleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 多选题信息表(SubjectMultiple)表服务实现类
 *
 * @author makejava
 * @since 2024-05-28 17:38:17
 */
@Service("subjectMultipleService")
public class SubjectMultipleServiceImpl implements SubjectMultipleService {
    private SubjectMultipleDao subjectMultipleDao;

    @Autowired
    public void setSubjectMultipleDao(SubjectMultipleDao subjectMultipleDao) {
        this.subjectMultipleDao = subjectMultipleDao;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectMultiple queryById(Long id) {
        return this.subjectMultipleDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param subjectMultiple 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectMultiple insert(SubjectMultiple subjectMultiple) {
        this.subjectMultipleDao.insert(subjectMultiple);
        return subjectMultiple;
    }

    /**
     * 修改数据
     *
     * @param subjectMultiple 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectMultiple update(SubjectMultiple subjectMultiple) {
        this.subjectMultipleDao.update(subjectMultiple);
        return this.queryById(subjectMultiple.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.subjectMultipleDao.deleteById(id) > 0;
    }

    @Override
    public int batchInsert(List<SubjectMultiple> multipleList) {
        return subjectMultipleDao.insertBatch(multipleList);
    }

    @Override
    public List<SubjectMultiple> queryBySubjectId(Long subjectId) {
        return subjectMultipleDao.queryBySubjectId(subjectId);
    }

    @Override
    public List<SubjectMultiple> queryByCondition(SubjectMultiple subjectMultiple) {
        return subjectMultipleDao.queryAllByLimit(subjectMultiple);
    }
}
