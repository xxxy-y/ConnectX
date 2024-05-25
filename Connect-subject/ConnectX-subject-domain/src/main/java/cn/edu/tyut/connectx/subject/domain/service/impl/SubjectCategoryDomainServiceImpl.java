package cn.edu.tyut.connectx.subject.domain.service.impl;

import cn.edu.tyut.connectx.subject.domain.convert.SubjectCategoryConvert;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectCategoryBO;
import cn.edu.tyut.connectx.subject.domain.service.SubjectCategoryDomainService;
import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectCategory;
import cn.edu.tyut.connectx.subject.infra.basic.service.SubjectCategoryService;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/24
 */
@Slf4j
@Service
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {
    private SubjectCategoryService subjectCategoryService;

    @Autowired
    public void setSubjectCategoryService(SubjectCategoryService subjectCategoryService) {
        this.subjectCategoryService = subjectCategoryService;
    }

    @Override
    public void add(SubjectCategoryBO subjectCategoryBo) {
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.add.subjectCategoryBo:{}", JSON.toJSONString(subjectCategoryBo));
        }
        SubjectCategory subjectCategory = SubjectCategoryConvert.INSTANCE.subjectCategoryBoToSubjectCategory(subjectCategoryBo);
        subjectCategoryService.insert(subjectCategory);
    }
}
