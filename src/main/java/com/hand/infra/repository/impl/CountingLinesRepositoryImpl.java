package com.hand.infra.repository.impl;

import com.hand.api.controller.dto.ReportDTO;
import com.hand.infra.mapper.CountingLinesMapper;
import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import com.hand.domain.entity.CountingLines;
import com.hand.domain.repository.CountingLinesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 资产盘点清单 资源库实现
 *
 * @author heng.huang@hand-china.com 2019-12-26 14:56:29
 */
@Component
public class CountingLinesRepositoryImpl extends BaseRepositoryImpl<CountingLines> implements CountingLinesRepository {
    @Autowired
    @SuppressWarnings("all")
    private CountingLinesMapper countingLinesMapper;

    @Override
    public List<ReportDTO> getList(Long taskId) {
        return countingLinesMapper.selectLineList(taskId);
    }
}
