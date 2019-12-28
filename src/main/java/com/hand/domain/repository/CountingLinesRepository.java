package com.hand.domain.repository;

import com.hand.api.controller.dto.ReportDTO;
import org.apache.ibatis.annotations.Param;
import org.hzero.mybatis.base.BaseRepository;
import com.hand.domain.entity.CountingLines;

import java.util.List;

/**
 * 资产盘点清单资源库
 *
 * @author heng.huang@hand-china.com 2019-12-26 14:56:29
 */
public interface CountingLinesRepository extends BaseRepository<CountingLines> {

    List<ReportDTO> getList(Long taskId);
}
