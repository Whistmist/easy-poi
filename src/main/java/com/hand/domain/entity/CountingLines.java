package com.hand.domain.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotBlank;
import io.choerodon.mybatis.domain.AuditDomain;
import io.choerodon.mybatis.annotation.ModifyAudit;
import io.choerodon.mybatis.annotation.VersionAudit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 资产盘点清单
 *
 * @author heng.huang@hand-china.com 2019-12-26 14:56:29
 */
@ApiModel("资产盘点清单")
@VersionAudit
@ModifyAudit
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Table(name = "aatn_counting_lines")
public class CountingLines extends AuditDomain{

        public static final String FIELD_LINES_ID ="linesId";
            public static final String FIELD_LINES_NAME ="linesName";
            public static final String FIELD_LINES_NUMBER ="linesNumber";
            public static final String FIELD_LINES_TRACKING_NUMBER ="linesTrackingNumber";
            public static final String FIELD_TASKS_ID ="tasksId";
            public static final String FIELD_BATCHS_ID ="batchsId";
            public static final String FIELD_COUNTING_PERSON_ID ="countingPersonId";
            public static final String FIELD_ASSET_ID ="assetId";
            public static final String FIELD_COUNTING_STATUS ="countingStatus";
            public static final String FIELD_ADJUST_POSTED_FLAG ="adjustPostedFlag";
            public static final String FIELD_ADJUSTMENT_METHOD ="adjustmentMethod";
            public static final String FIELD_TREATMENT_PROCESS ="treatmentProcess";
            public static final String FIELD_DESCRIPTION ="description";
            public static final String FIELD_ATTRIBUTE1 ="attribute1";
            public static final String FIELD_ATTRIBUTE2 ="attribute2";
            public static final String FIELD_ATTRIBUTE3 ="attribute3";
            public static final String FIELD_ATTRIBUTE4 ="attribute4";
            public static final String FIELD_ATTRIBUTE5 ="attribute5";
            public static final String FIELD_ATTRIBUTE6 ="attribute6";
            public static final String FIELD_ATTRIBUTE7 ="attribute7";
            public static final String FIELD_ATTRIBUTE8 ="attribute8";
            public static final String FIELD_ATTRIBUTE9 ="attribute9";
            public static final String FIELD_ATTRIBUTE10 ="attribute10";
            public static final String FIELD_ATTRIBUTE11 ="attribute11";
            public static final String FIELD_ATTRIBUTE12 ="attribute12";
            public static final String FIELD_ATTRIBUTE13 ="attribute13";
            public static final String FIELD_ATTRIBUTE14 ="attribute14";
            public static final String FIELD_ATTRIBUTE15 ="attribute15";
            public static final String FIELD_TENANT_ID ="tenantId";
            public static final String FIELD_PROFIT_LOSS_DIFF_TYPE ="profitLossDiffType";
            public static final String FIELD_PROCESS_NUM ="processNum";
            public static final String FIELD_COMPARISON_RESULT ="comparisonResult";
            public static final String FIELD_CORRESPONDENCE_ORG_ID ="correspondenceOrgId";
            public static final String FIELD_PROCESS_STATUS ="processStatus";
            public static final String FIELD_SOURCE_CODE ="sourceCode";
            public static final String FIELD_ATTRIBUTE16 ="attribute16";
            public static final String FIELD_ATTRIBUTE17 ="attribute17";
            public static final String FIELD_ATTRIBUTE18 ="attribute18";
            public static final String FIELD_ATTRIBUTE19 ="attribute19";
            public static final String FIELD_ATTRIBUTE20 ="attribute20";
            public static final String FIELD_ASSET_TAG_NAME ="assetTagName";
    
//
// 业务方法(按public protected private顺序排列)
// ------------------------------------------------------------------------------

//
// 数据库字段
// ------------------------------------------------------------------------------


                @ApiModelProperty("表ID，主键，供其他表做外键")
    @Id
    @GeneratedValue
            private Long linesId;
	
                            	    @ApiModelProperty(value = "盘点清单名称")
                            private String linesName;
	
                            	    @ApiModelProperty(value = "盘点清单编号")
                            private String linesNumber;
	
                            	    @ApiModelProperty(value = "盘点清单跟踪编号")
                            private String linesTrackingNumber;
	
                            	    @ApiModelProperty(value = "盘点任务ID")
                            private Long tasksId;
	
                            	    @ApiModelProperty(value = "盘点批ID")
                            private Long batchsId;
	
                            	    @ApiModelProperty(value = "盘点人员ID")
                            private Long countingPersonId;
	
                            	    @ApiModelProperty(value = "资产ID")
                            private Long assetId;
	
                                    @ApiModelProperty(value = "盘点状态, 值集：AATN.COUNTING_LINES_STATUS")
	                    @NotBlank
                                        private String countingStatus;
	
                            	    @ApiModelProperty(value = "是否已经完成调整")
                            private String adjustPostedFlag;
	
                            	    @ApiModelProperty(value = "处理方法")
                            private String adjustmentMethod;
	
                            	    @ApiModelProperty(value = "处理过程")
                            private String treatmentProcess;
	
                            	    @ApiModelProperty(value = "盘点说明")
                            private String description;
	
                            	    @ApiModelProperty(value = "attribute1")
                            private String attribute1;
	
                            	    @ApiModelProperty(value = "attribute2")
                            private String attribute2;
	
                            	    @ApiModelProperty(value = "attribute3")
                            private String attribute3;
	
                            	    @ApiModelProperty(value = "attribute4")
                            private String attribute4;
	
                            	    @ApiModelProperty(value = "attribute5")
                            private String attribute5;
	
                            	    @ApiModelProperty(value = "attribute6")
                            private String attribute6;
	
                            	    @ApiModelProperty(value = "attribute7")
                            private String attribute7;
	
                            	    @ApiModelProperty(value = "attribute8")
                            private String attribute8;
	
                            	    @ApiModelProperty(value = "attribute9")
                            private String attribute9;
	
                            	    @ApiModelProperty(value = "attribute10")
                            private String attribute10;
	
                            	    @ApiModelProperty(value = "attribute11")
                            private String attribute11;
	
                            	    @ApiModelProperty(value = "attribute12")
                            private String attribute12;
	
                            	    @ApiModelProperty(value = "attribute13")
                            private String attribute13;
	
                            	    @ApiModelProperty(value = "attribute14")
                            private String attribute14;
	
                            	    @ApiModelProperty(value = "attribute15")
                            private String attribute15;
	
                                    @ApiModelProperty(value = "租户ID")
	                    @NotNull
                                        private Long tenantId;
	
                                                	    @ApiModelProperty(value = "盈亏差异类型")
                            private String profitLossDiffType;
	
                            	    @ApiModelProperty(value = "流程单号")
                            private String processNum;
	
                            	    @ApiModelProperty(value = "比对结果 值集：AATN.COUNT_COMPARA_RESULT")
                            private String comparisonResult;
	
                            	    @ApiModelProperty(value = "对应部门")
                            private Long correspondenceOrgId;
	
                            	    @ApiModelProperty(value = "处理状态,值集：AATN.COUNT_PROCESSING_STATUS")
                            private String processStatus;
	
                            	    @ApiModelProperty(value = "来源")
                            private String sourceCode;
	
                            	    @ApiModelProperty(value = "attribute16")
                            private String attribute16;
	
                            	    @ApiModelProperty(value = "attribute17")
                            private String attribute17;
	
                            	    @ApiModelProperty(value = "attribute18")
                            private String attribute18;
	
                            	    @ApiModelProperty(value = "attribute19")
                            private String attribute19;
	
                            	    @ApiModelProperty(value = "attribute20")
                            private String attribute20;
	
                            	    @ApiModelProperty(value = "资产可视标签/铭牌")
                            private String assetTagName;
	
    
//
// 非数据库字段
// ------------------------------------------------------------------------------

//
// getter/setter
// ------------------------------------------------------------------------------

            /**
     * @return 表ID，主键，供其他表做外键
     */
        public Long getLinesId(){
            return linesId;
    }

    public void setLinesId(Long linesId){
            this.linesId = linesId;
    }
                /**
     * @return 盘点清单名称
     */
        public String getLinesName(){
            return linesName;
    }

    public void setLinesName(String linesName){
            this.linesName = linesName;
    }
                /**
     * @return 盘点清单编号
     */
        public String getLinesNumber(){
            return linesNumber;
    }

    public void setLinesNumber(String linesNumber){
            this.linesNumber = linesNumber;
    }
                /**
     * @return 盘点清单跟踪编号
     */
        public String getLinesTrackingNumber(){
            return linesTrackingNumber;
    }

    public void setLinesTrackingNumber(String linesTrackingNumber){
            this.linesTrackingNumber = linesTrackingNumber;
    }
                /**
     * @return 盘点任务ID
     */
        public Long getTasksId(){
            return tasksId;
    }

    public void setTasksId(Long tasksId){
            this.tasksId = tasksId;
    }
                /**
     * @return 盘点批ID
     */
        public Long getBatchsId(){
            return batchsId;
    }

    public void setBatchsId(Long batchsId){
            this.batchsId = batchsId;
    }
                /**
     * @return 盘点人员ID
     */
        public Long getCountingPersonId(){
            return countingPersonId;
    }

    public void setCountingPersonId(Long countingPersonId){
            this.countingPersonId = countingPersonId;
    }
                /**
     * @return 资产ID
     */
        public Long getAssetId(){
            return assetId;
    }

    public void setAssetId(Long assetId){
            this.assetId = assetId;
    }
                /**
     * @return 盘点状态, 值集：AATN.COUNTING_LINES_STATUS
     */
        public String getCountingStatus(){
            return countingStatus;
    }

    public void setCountingStatus(String countingStatus){
            this.countingStatus = countingStatus;
    }
                /**
     * @return 是否已经完成调整
     */
        public String getAdjustPostedFlag(){
            return adjustPostedFlag;
    }

    public void setAdjustPostedFlag(String adjustPostedFlag){
            this.adjustPostedFlag = adjustPostedFlag;
    }
                /**
     * @return 处理方法
     */
        public String getAdjustmentMethod(){
            return adjustmentMethod;
    }

    public void setAdjustmentMethod(String adjustmentMethod){
            this.adjustmentMethod = adjustmentMethod;
    }
                /**
     * @return 处理过程
     */
        public String getTreatmentProcess(){
            return treatmentProcess;
    }

    public void setTreatmentProcess(String treatmentProcess){
            this.treatmentProcess = treatmentProcess;
    }
                /**
     * @return 盘点说明
     */
        public String getDescription(){
            return description;
    }

    public void setDescription(String description){
            this.description = description;
    }
                /**
     * @return attribute1
     */
        public String getAttribute1(){
            return attribute1;
    }

    public void setAttribute1(String attribute1){
            this.attribute1 = attribute1;
    }
                /**
     * @return attribute2
     */
        public String getAttribute2(){
            return attribute2;
    }

    public void setAttribute2(String attribute2){
            this.attribute2 = attribute2;
    }
                /**
     * @return attribute3
     */
        public String getAttribute3(){
            return attribute3;
    }

    public void setAttribute3(String attribute3){
            this.attribute3 = attribute3;
    }
                /**
     * @return attribute4
     */
        public String getAttribute4(){
            return attribute4;
    }

    public void setAttribute4(String attribute4){
            this.attribute4 = attribute4;
    }
                /**
     * @return attribute5
     */
        public String getAttribute5(){
            return attribute5;
    }

    public void setAttribute5(String attribute5){
            this.attribute5 = attribute5;
    }
                /**
     * @return attribute6
     */
        public String getAttribute6(){
            return attribute6;
    }

    public void setAttribute6(String attribute6){
            this.attribute6 = attribute6;
    }
                /**
     * @return attribute7
     */
        public String getAttribute7(){
            return attribute7;
    }

    public void setAttribute7(String attribute7){
            this.attribute7 = attribute7;
    }
                /**
     * @return attribute8
     */
        public String getAttribute8(){
            return attribute8;
    }

    public void setAttribute8(String attribute8){
            this.attribute8 = attribute8;
    }
                /**
     * @return attribute9
     */
        public String getAttribute9(){
            return attribute9;
    }

    public void setAttribute9(String attribute9){
            this.attribute9 = attribute9;
    }
                /**
     * @return attribute10
     */
        public String getAttribute10(){
            return attribute10;
    }

    public void setAttribute10(String attribute10){
            this.attribute10 = attribute10;
    }
                /**
     * @return attribute11
     */
        public String getAttribute11(){
            return attribute11;
    }

    public void setAttribute11(String attribute11){
            this.attribute11 = attribute11;
    }
                /**
     * @return attribute12
     */
        public String getAttribute12(){
            return attribute12;
    }

    public void setAttribute12(String attribute12){
            this.attribute12 = attribute12;
    }
                /**
     * @return attribute13
     */
        public String getAttribute13(){
            return attribute13;
    }

    public void setAttribute13(String attribute13){
            this.attribute13 = attribute13;
    }
                /**
     * @return attribute14
     */
        public String getAttribute14(){
            return attribute14;
    }

    public void setAttribute14(String attribute14){
            this.attribute14 = attribute14;
    }
                /**
     * @return attribute15
     */
        public String getAttribute15(){
            return attribute15;
    }

    public void setAttribute15(String attribute15){
            this.attribute15 = attribute15;
    }
                /**
     * @return 租户ID
     */
        public Long getTenantId(){
            return tenantId;
    }

    public void setTenantId(Long tenantId){
            this.tenantId = tenantId;
    }
                                    /**
     * @return 盈亏差异类型
     */
        public String getProfitLossDiffType(){
            return profitLossDiffType;
    }

    public void setProfitLossDiffType(String profitLossDiffType){
            this.profitLossDiffType = profitLossDiffType;
    }
                /**
     * @return 流程单号
     */
        public String getProcessNum(){
            return processNum;
    }

    public void setProcessNum(String processNum){
            this.processNum = processNum;
    }
                /**
     * @return 比对结果 值集：AATN.COUNT_COMPARA_RESULT
     */
        public String getComparisonResult(){
            return comparisonResult;
    }

    public void setComparisonResult(String comparisonResult){
            this.comparisonResult = comparisonResult;
    }
                /**
     * @return 对应部门
     */
        public Long getCorrespondenceOrgId(){
            return correspondenceOrgId;
    }

    public void setCorrespondenceOrgId(Long correspondenceOrgId){
            this.correspondenceOrgId = correspondenceOrgId;
    }
                /**
     * @return 处理状态,值集：AATN.COUNT_PROCESSING_STATUS
     */
        public String getProcessStatus(){
            return processStatus;
    }

    public void setProcessStatus(String processStatus){
            this.processStatus = processStatus;
    }
                /**
     * @return 来源
     */
        public String getSourceCode(){
            return sourceCode;
    }

    public void setSourceCode(String sourceCode){
            this.sourceCode = sourceCode;
    }
                /**
     * @return attribute16
     */
        public String getAttribute16(){
            return attribute16;
    }

    public void setAttribute16(String attribute16){
            this.attribute16 = attribute16;
    }
                /**
     * @return attribute17
     */
        public String getAttribute17(){
            return attribute17;
    }

    public void setAttribute17(String attribute17){
            this.attribute17 = attribute17;
    }
                /**
     * @return attribute18
     */
        public String getAttribute18(){
            return attribute18;
    }

    public void setAttribute18(String attribute18){
            this.attribute18 = attribute18;
    }
                /**
     * @return attribute19
     */
        public String getAttribute19(){
            return attribute19;
    }

    public void setAttribute19(String attribute19){
            this.attribute19 = attribute19;
    }
                /**
     * @return attribute20
     */
        public String getAttribute20(){
            return attribute20;
    }

    public void setAttribute20(String attribute20){
            this.attribute20 = attribute20;
    }
                /**
     * @return 资产可视标签/铭牌
     */
        public String getAssetTagName(){
            return assetTagName;
    }

    public void setAssetTagName(String assetTagName){
            this.assetTagName = assetTagName;
    }
    
    public interface Update {
    }

    public interface Insert {
    }
}
