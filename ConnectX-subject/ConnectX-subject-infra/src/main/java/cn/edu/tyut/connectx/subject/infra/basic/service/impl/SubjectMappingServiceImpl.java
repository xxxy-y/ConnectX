package cn.edu.tyut.connectx.subject.infra.basic.service.impl;

import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectMapping;
import cn.edu.tyut.connectx.subject.infra.basic.mapper.SubjectMappingDao;
import cn.edu.tyut.connectx.subject.infra.basic.service.SubjectMappingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 题目分类关系表(SubjectMapping)表服务实现类
 *
 * @author makejava
 * @since 2024-05-28 14:08:40
 */
@Service("subjectMappingService")
public class SubjectMappingServiceImpl implements SubjectMappingService {
    @Resource
    private SubjectMappingDao subjectMappingDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectMapping queryById(Long id) {
        return this.subjectMappingDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param subjectMapping 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectMapping insert(SubjectMapping subjectMapping) {
        this.subjectMappingDao.insert(subjectMapping);
        return subjectMapping;
    }

    /**
     * 修改数据
     *
     * @param subjectMapping 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectMapping update(SubjectMapping subjectMapping) {
        this.subjectMappingDao.update(subjectMapping);
        return this.queryById(subjectMapping.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.subjectMappingDao.deleteById(id) > 0;
    }

    /**
     * 查询分类id下的标签
     *
     * @param subjectMapping 分类id
     * @return 返回查询结果
     */
    @Override
    public List<SubjectMapping> queryLabelId(SubjectMapping subjectMapping) {
        return subjectMappingDao.queryDistinctLabelId(subjectMapping);
    }

    /**
     * 批量插入
     *
     * @param subjectMappingList 传入的SubjectMapping
     * @return 受影响行数
     */
    @Override
    public int batchInsert(List<SubjectMapping> subjectMappingList) {
        return subjectMappingDao.insertBatch(subjectMappingList);
    }

    /**
     * 在mapping表中根据subjectId查询数据
     *
     * @param id 传入的subjectId
     * @return 查询到的数据
     */
    @Override
    public List<SubjectMapping> queryBySubjectId(Long id) {
        return subjectMappingDao.queryBySubjectId(id);
    }
}
