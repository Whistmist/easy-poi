package com.hand.app.service;

import java.util.List;

import com.hand.domain.entity.CountingLines;

import io.choerodon.core.domain.Page;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;

/**
 * 资产盘点清单应用服务
 *
 * @author heng.huang@hand-china.com 2019-12-26 14:56:29
 */
public interface CountingLinesService {

    /**
     * 获取资产盘点清单分页列表
     *
     * @param countingLines 资产盘点清单信息
     * @param pageRequest 分页信息
     * @return Page<CountingLines>
     */
    Page<CountingLines> list(CountingLines countingLines, PageRequest pageRequest);

    /**
     * 获取资产盘点清单明细
     *
     * @param linesId 资产盘点清单主键Id
     * @return CountingLines
     */
    CountingLines detail(Long linesId);

    /**
     * 资产盘点清单创建或更新
     *
     * @param countingLines 资产盘点清单信息
     * @return CountingLines
     *
     */
    CountingLines insertOrUpdate(CountingLines countingLines);

    /**
     * 资产盘点清单批量创建或更新
     *
     * @param countingLinesList 资产盘点清单信息集合
     * @return List<CountingLines>
     */
    List<CountingLines> batchInsertOrUpdate(List<CountingLines> countingLinesList);

    /**
     * 资产盘点清单删除
     *
     * @param countingLines 资产盘点清单信息
     * @return
     */
    void remove(CountingLines countingLines);

    /**
     * 资产盘点清单批量删除
     *
     * @param  countingLinesList 资产盘点清单信息集合
     * @return
     */
    void batchRemove(List<CountingLines> countingLinesList);
}
