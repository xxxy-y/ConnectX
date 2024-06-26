package cn.edu.tyut.connectx.subject.infra.basic.service.impl;

import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectJudge;
import cn.edu.tyut.connectx.subject.infra.basic.mapper.SubjectJudgeDao;
import cn.edu.tyut.connectx.subject.infra.basic.service.SubjectJudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 判断题(SubjectJudge)表服务实现类
 *
 * @author makejava
 * @since 2024-05-28 17:37:11
 */
@Service("subjectJudgeService")
public class SubjectJudgeServiceImpl implements SubjectJudgeService {
    private SubjectJudgeDao subjectJudgeDao;

    @Autowired
    public void setSubjectJudgeDao(SubjectJudgeDao subjectJudgeDao) {
        this.subjectJudgeDao = subjectJudgeDao;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectJudge queryById(Long id) {
        return this.subjectJudgeDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param subjectJudge 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(SubjectJudge subjectJudge) {
        return this.subjectJudgeDao.insert(subjectJudge);
    }

    /**
     * 修改数据
     *
     * @param subjectJudge 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectJudge update(SubjectJudge subjectJudge) {
        this.subjectJudgeDao.update(subjectJudge);
        return this.queryById(subjectJudge.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.subjectJudgeDao.deleteById(id) > 0;
    }

    @Override
    public int batchInsert(List<SubjectJudge> subjectJudgeList) {
        return subjectJudgeDao.insertBatch(subjectJudgeList);
    }

    @Override
    public List<SubjectJudge> queryByCondition(SubjectJudge subjectJudge) {
        return subjectJudgeDao.queryAllByLimit(subjectJudge);
    }
}
