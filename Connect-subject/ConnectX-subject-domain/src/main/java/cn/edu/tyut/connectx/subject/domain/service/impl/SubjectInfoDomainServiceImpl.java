package cn.edu.tyut.connectx.subject.domain.service.impl;

import cn.edu.tyut.connectx.subject.common.entity.PageResult;
import cn.edu.tyut.connectx.subject.common.enums.IsDeletedFlagEnum;
import cn.edu.tyut.connectx.subject.domain.convert.SubjectInfoConvert;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectInfoBO;
import cn.edu.tyut.connectx.subject.domain.entity.SubjectOptionBO;
import cn.edu.tyut.connectx.subject.domain.handler.subject.SubjectTypeHandler;
import cn.edu.tyut.connectx.subject.domain.handler.subject.SubjectTypeHandlerFactory;
import cn.edu.tyut.connectx.subject.domain.service.SubjectInfoDomainService;
import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectInfo;
import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectLabel;
import cn.edu.tyut.connectx.subject.infra.basic.entity.SubjectMapping;
import cn.edu.tyut.connectx.subject.infra.basic.service.SubjectInfoService;
import cn.edu.tyut.connectx.subject.infra.basic.service.impl.SubjectLabelServiceImpl;
import cn.edu.tyut.connectx.subject.infra.basic.service.impl.SubjectMappingServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author 吴庆涛
 * @DATE 2024/5/28
 */
@Slf4j
@Service
public class SubjectInfoDomainServiceImpl implements SubjectInfoDomainService {
    private SubjectInfoConvert subjectInfoConvert;
    private SubjectTypeHandlerFactory subjectTypeHandlerFactory;
    private SubjectInfoService subjectInfoService;
    private SubjectMappingServiceImpl subjectMappingService;
    private SubjectLabelServiceImpl subjectLabelService;

    @Autowired
    public void setSubjectMappingService(SubjectMappingServiceImpl subjectMappingService) {
        this.subjectMappingService = subjectMappingService;
    }

    @Autowired
    public void setSubjectInfoService(SubjectInfoService subjectInfoService) {
        this.subjectInfoService = subjectInfoService;
    }

    @Autowired
    public void setSubjectInfoConvert(SubjectInfoConvert subjectInfoConvert) {
        this.subjectInfoConvert = subjectInfoConvert;
    }

    @Autowired
    public void setSubjectTypeHandlerFactory(SubjectTypeHandlerFactory subjectTypeHandlerFactory) {
        this.subjectTypeHandlerFactory = subjectTypeHandlerFactory;
    }

    @Autowired
    public void setSubjectLabelService(SubjectLabelServiceImpl subjectLabelService) {
        this.subjectLabelService = subjectLabelService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean add(SubjectInfoBO subjectInfoBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectInfoDomainServiceImpl.add.subjectInfoBO:{}", subjectInfoBO);
        }
        // 向SubjectInfo表中插入数据
        SubjectInfo subjectInfo = subjectInfoConvert.convertSubjectInfoBoToSubjectInfo(subjectInfoBO);
        int insert = subjectInfoService.insert(subjectInfo);
        subjectInfoBO.setId(subjectInfo.getId());
        // 向题目答案表（Radio、Multiple、Judge、Brief表）中插入数据，使用的是工厂+策略模式
        Integer subjectType = subjectInfoBO.getSubjectType();
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectType);
        int add = handler.add(subjectInfoBO);
        List<SubjectMapping> subjectMappingList = getSubjectMappingList(subjectInfoBO);
        int add2 = subjectMappingService.batchInsert(subjectMappingList);
        return add > 0 && insert > 0 && add2 > 0;
    }

    /**
     * 分页查询
     *
     * @param subjectInfoBO 传入的Bo
     * @return 查询结果
     */
    @Override
    public PageResult<SubjectInfoBO> getSubjectPage(@NotNull SubjectInfoBO subjectInfoBO) {
        PageResult<SubjectInfoBO> pageResult = new PageResult<>();
        pageResult.setPageNo(subjectInfoBO.getPageNo());
        pageResult.setPageSize(subjectInfoBO.getPageSize());
        int start = (subjectInfoBO.getPageNo() - 1) * subjectInfoBO.getPageSize();
        SubjectInfo subjectInfo = subjectInfoConvert.convertSubjectInfoBoToSubjectInfo(subjectInfoBO);
        // TODO 这样做不要污染subjectInfo实体类，但是需要传入条件，条件太多就不行了，如果不想传入过多的条件
        //  ，便会污染SubjectInfo类，之后可以使用ES来解决！
        int count = subjectInfoService.countByCondition(subjectInfo, subjectInfoBO.getCategoryId(), subjectInfoBO.getLabelId());
        if (count == 0) {
            return pageResult;
        }
        List<SubjectInfo> subjectInfoList = subjectInfoService.queryPage(subjectInfo,
                subjectInfoBO.getCategoryId(), subjectInfoBO.getLabelId(), start, subjectInfoBO.getPageSize());
        List<SubjectInfoBO> subjectInfoBOList = subjectInfoConvert.convertSubjectInfoListToSubjectInfoBoList(subjectInfoList);
        pageResult.setTotal(count);
        pageResult.setResult(subjectInfoBOList);
        return pageResult;
    }

    /**
     * 查询题目详情
     *
     * @param subjectInfoBO 其中包含提供的信息
     * @return 查询到的题目信息
     */
    @Override
    public SubjectInfoBO querySubjectInfo(@NotNull SubjectInfoBO subjectInfoBO) {
        Long id = subjectInfoBO.getId();
        SubjectInfo subjectInfo = subjectInfoService.queryById(id);
        // 查询题目的答案并将题目答案放入subjectInfoBo里面
        Integer subjectType = subjectInfo.getSubjectType();
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectType);
        // handler.query方法是用来查询这个题目答案的，
        // 查询结果包括 题目答案正确的答案 和 答案的选项:一个列表，其中包括这个选项的标识，选项内容和该选项是否正确
        SubjectOptionBO optionBO = handler.query(subjectInfo.getId());
        SubjectInfoBO infoBO = subjectInfoConvert.convertSubjectOptionAndSubjecyInfoToSubjectInfoBO(optionBO, subjectInfo);
        // 转换，将labelId转换为labelName-->查mapping表
        // 从mapping表中根据subjectId查找到当前题目对应的标签ID
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setSubjectId(id);
        subjectMapping.setIsDeleted(IsDeletedFlagEnum.UNDELETED.getCode());
        List<SubjectMapping> subjectMappingList = subjectMappingService.queryLabelId(subjectMapping);
        // 根据查询到的标签ID查询到这些标签ID对应的标签名称
        List<Long> labelIdList = subjectMappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> subjectLabelList = subjectLabelService.batchQueryById(labelIdList);
        List<String> collect = subjectLabelList.stream().map(SubjectLabel::getLabelName).collect(Collectors.toList());
        infoBO.setLabelName(collect);
        return infoBO;
    }

    private @NotNull List<SubjectMapping> getSubjectMappingList(@NotNull SubjectInfoBO subjectInfoBO) {
        List<Long> categoryIdList = subjectInfoBO.getCategoryIds();
        List<Long> labelIdList = subjectInfoBO.getLabelIds();
        List<SubjectMapping> subjectMappingList = new ArrayList<>();
        categoryIdList.forEach(categoryId -> labelIdList.forEach(labelId -> {
            SubjectMapping subjectMapping = new SubjectMapping();
            subjectMapping.setSubjectId(subjectInfoBO.getId());
            subjectMapping.setCategoryId(categoryId);
            subjectMapping.setLabelId(labelId);
            subjectMappingList.add(subjectMapping);
        }));
        return subjectMappingList;
    }
}
