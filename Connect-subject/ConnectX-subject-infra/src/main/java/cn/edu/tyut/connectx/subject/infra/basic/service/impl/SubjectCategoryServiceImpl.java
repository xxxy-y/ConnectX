package cn.edu.tyut.connectx.subject.infra.basic.service.impl;

import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectCategory;
import cn.edu.tyut.connectx.subject.infra.basic.mapper.SubjectCategoryDao;
import cn.edu.tyut.connectx.subject.infra.basic.service.SubjectCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 题目分类(SubjectCategory)表服务实现类
 *
 * @author makejava
 * @since 2024-05-23 20:40:30
 */
@Service("subjectCategoryService")
public class SubjectCategoryServiceImpl implements SubjectCategoryService {
    private SubjectCategoryDao subjectCategoryDao;

    @Autowired
    public void setSubjectCategoryDao(SubjectCategoryDao subjectCategoryDao) {
        this.subjectCategoryDao = subjectCategoryDao;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectCategory queryById(Long id) {
        return this.subjectCategoryDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param subjectCategory 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectCategory insert(SubjectCategory subjectCategory) {
        this.subjectCategoryDao.insert(subjectCategory);
        return subjectCategory;
    }

    /**
     * 修改数据
     *
     * @param subjectCategory 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectCategory update(SubjectCategory subjectCategory) {
        this.subjectCategoryDao.update(subjectCategory);
        return this.queryById(subjectCategory.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.subjectCategoryDao.deleteById(id) > 0;
    }
}
