package com.hand.app.service.impl;

import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import com.hand.app.service.CountingLinesService;
import com.hand.domain.entity.CountingLines;
import com.hand.domain.repository.CountingLinesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
/**
 * 资产盘点清单应用服务默认实现
 *
 * @author heng.huang@hand-china.com 2019-12-26 14:56:29
 */
@Service
public class CountingLinesServiceImpl implements CountingLinesService {
    @Autowired
    private CountingLinesRepository countingLinesRepository;

    /**
     * 获取资产盘点清单分页列表
     *
     * @param countingLines 资产盘点清单信息
     * @param pageRequest 分页信息
     * @return Page<CountingLines>
     */
    @Override
    public Page<CountingLines> list(CountingLines countingLines, PageRequest pageRequest) {
        Page<CountingLines> list = countingLinesRepository.pageAndSort(pageRequest, countingLines);
        return list;
    }

    /**
     * 获取资产盘点清单明细
     *
     * @param linesId 资产盘点清单主键Id
     * @return CountingLines
     */
    @Override
    public CountingLines detail(Long linesId) {
        CountingLines countingLines = countingLinesRepository.selectByPrimaryKey(linesId);
        return countingLines;
    }

    /**
     * 资产盘点清单批量创建或更新
     *
     * @param countingLinesList 资产盘点清单信息集合
     * @return List<CountingLines>
     */
    @Override
	@Transactional(rollbackFor = {Exception.class})
    public List<CountingLines> batchInsertOrUpdate(List<CountingLines> countingLinesList){
        if (CollectionUtils.isEmpty(countingLinesList)) {
            return countingLinesList;
        } else {
            for (CountingLines countingLines : countingLinesList) {
                this.insertOrUpdate(countingLines);
            }
        }
        return countingLinesList;
    }

    /**
     * 资产盘点清单创建或更新
     *
     * @param countingLines 资产盘点清单信息
     * @return CountingLines
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public CountingLines insertOrUpdate(CountingLines countingLines) {
        if (countingLines == null) {
            return null;
        } else {
            if (countingLines.getLinesId() == null) {
                    countingLinesRepository.insertSelective(countingLines);
            } else {
                CountingLines countingLinesDb = countingLinesRepository.selectByPrimaryKey(countingLines);
                Assert.notNull(countingLinesDb, "error.data_not_exists");
                                    Assert.isTrue(Objects.equals(countingLinesDb.getTenantId(), countingLines.getTenantId()), "error.data_invalid");
                                    countingLinesRepository.updateByPrimaryKey(countingLines);
            }
            return countingLines;
        }
    }

    /**
     * 资产盘点清单删除
     *
     * @param countingLines 资产盘点清单信息
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void remove(CountingLines countingLines) {
            countingLinesRepository.deleteByPrimaryKey(countingLines);
    }

    /**
     * 资产盘点清单批量删除
     *
     * @param  countingLinesList 资产盘点清单信息集合
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void batchRemove(List<CountingLines> countingLinesList) {
            countingLinesRepository.batchDeleteByPrimaryKey(countingLinesList);

    }
}
