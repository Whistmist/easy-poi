package com.hand.api.controller.api;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import com.hand.app.service.CountingLinesService;
import org.hzero.core.util.Results;
import org.hzero.core.base.BaseController;
import com.hand.domain.entity.CountingLines;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.hzero.mybatis.helper.SecurityTokenHelper;

import io.choerodon.core.domain.Page;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.mybatis.pagehelper.annotation.SortDefault;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.mybatis.pagehelper.domain.Sort;
import io.choerodon.swagger.annotation.Permission;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 资产盘点清单 管理 API
 *
 * @author heng.huang@hand-china.com 2019-12-26 14:56:29
 */
        @RestController("countingLinesController.v1")
    @RequestMapping("/v1/{organizationId}/counting-liness")
    public class CountingLinesController extends BaseController {

    @Autowired
    private CountingLinesService countingLinesService;

    @ApiOperation(value = "获取资产盘点清单分页列表")
            @Permission(level = ResourceLevel.ORGANIZATION)
            @GetMapping
    public ResponseEntity<Page<CountingLines>> list(            @ApiParam(value = "租户ID", required = true) @PathVariable("organizationId") Long tenantId,
                CountingLines countingLines, @ApiIgnore @SortDefault(value = CountingLines.FIELD_LINES_ID,
            direction = Sort.Direction.DESC) PageRequest pageRequest) {
        Page<CountingLines> list = countingLinesService.list(countingLines, pageRequest);
        return Results.success(list);
    }

    @ApiOperation(value = "获取资产盘点清单明细")
            @Permission(level = ResourceLevel.ORGANIZATION)
            @GetMapping("/{linesId}")
    public ResponseEntity<CountingLines> detail(            @ApiParam(value = "租户ID", required = true) @PathVariable("organizationId") Long tenantId,
                @PathVariable Long linesId) {
        CountingLines countingLines = countingLinesService.detail(linesId);
        return Results.success(countingLines);
    }

    @ApiOperation(value = "资产盘点清单批量创建或更新")
            @Permission(level = ResourceLevel.ORGANIZATION)
            @PostMapping("batchInsertOrUpdate")
    public ResponseEntity<List<CountingLines>> batchInsertOrUpdate(            @ApiParam(value = "租户ID", required = true) @PathVariable("organizationId") Long tenantId,
                @RequestBody List<CountingLines> countingLinesList) {
        Assert.notNull(countingLinesList, "error.data_invalid");
        for (CountingLines countingLines : countingLinesList) {
                                countingLines.setTenantId(tenantId);
                        if (countingLines.getLinesId() == null) {
                this.validObject(countingLines, new Class[]{CountingLines.Insert.class});
            } else {
                this.validObject(countingLines, new Class[]{CountingLines.Update.class});
            }
        }
        List<CountingLines> list = countingLinesService.batchInsertOrUpdate(countingLinesList);
        return Results.success(list);
    }

    @ApiOperation(value = "资产盘点清单批量删除")
            @Permission(level = ResourceLevel.ORGANIZATION)
            @DeleteMapping("/batchRemove")
    public ResponseEntity<?> batchRemove(            @ApiParam(value = "租户ID", required = true) @PathVariable("organizationId") Long tenantId,
                @RequestBody List<CountingLines> countingLinesList) {
        for (CountingLines countingLines : countingLinesList) {
            SecurityTokenHelper.validToken(countingLines);
        }
            countingLinesService.batchRemove( countingLinesList);
        return Results.success();
    }
}
