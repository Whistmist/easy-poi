package com.hand.infra.mapper;

import com.hand.api.controller.dto.ReportDTO;
import com.hand.domain.entity.CountingLines;
import io.choerodon.mybatis.common.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 资产盘点清单Mapper
 *
 * @author heng.huang@hand-china.com 2019-12-26 14:56:29
 */
public interface CountingLinesMapper extends BaseMapper<CountingLines> {

    List<ReportDTO> selectHeadList();

    List<ReportDTO> selectLineList(@Param("taskId") Long taskId);
}
