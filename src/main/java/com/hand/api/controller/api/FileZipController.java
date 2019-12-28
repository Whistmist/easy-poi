package com.hand.api.controller.api;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


import com.hand.api.controller.dto.ReportDTO;
import com.hand.domain.entity.CountingLines;
import com.hand.domain.repository.CountingLinesRepository;
import com.hand.infra.mapper.CountingLinesMapper;
import com.hand.util.FileZip;
import io.swagger.annotations.Api;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.usermodel.HeaderFooter;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description
 *
 * @author heng.huang@hand-china.com 2019/12/24
 */
@RestController
@RequestMapping("/sheet/report")
public class FileZipController {

    // 部门名称
    String orgNme ="";
    @Autowired
    private CountingLinesRepository countingLinesRepository;
    @Autowired
    @SuppressWarnings("all")
    private CountingLinesMapper countingLinesMapper;


    String fileName ;


    @RequestMapping()
    public void download(HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 文件名获取
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        fileName = "盘点汇总-" + format.format(date);
        setResponseHeader(response);
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            System.out.println("==============开始查询=============="+new Date());
            // 头信息
            List<ReportDTO> headList = countingLinesMapper.selectHeadList();
            // 行信息
            Long taskId = 5122L;
            List<ReportDTO> lineList = countingLinesRepository.getList(taskId);
            System.out.println("==============结束查询=============="+new Date());
            toExcel(headList,lineList,request,10000,fileName,out);
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Async
    public void toExcel(List<ReportDTO> headList,List<ReportDTO> lineList, HttpServletRequest request,
                        int length, String fileName, OutputStream out) throws IOException {
        if(CollectionUtils.isEmpty(headList)){
            return;
        }
        // 压缩文件
        File zip = new File(("C:\\Users\\50516\\Desktop\\12-18") + "/" + fileName + ".zip");
        List<String> fileNames = new ArrayList();
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet();
        // 设置打印参数
        setPrintSetup(sheet);
        // 设置头单元格格式
        CellStyle headCellStyle = book.createCellStyle();
        // 设置行单元格格式
        CellStyle lineCellStyle = book.createCellStyle();
        // 读取头信息
        System.out.println("==============开始存表=============="+new Date());
        // 遍历头信息
        // 生成excel
        System.out.println("==============生成excel=============="+new Date());
        for (int j = 0, n = lineList.size() / length + 1; j < n; j++) {
            String file = "C:\\Users\\50516\\Desktop\\12-18" + "/" + fileName + "-" + j
                    + ".xls";
            System.out.println("==============excel=============="+new Date());
            fileNames.add(file);
            FileOutputStream o = null;
            try {
                o = new FileOutputStream(file);
                //标题
                setTitle(headCellStyle,book,sheet);
                /// 设置头标题
                setRowHeadTitle(sheet);
                // 给头赋值
                ReportDTO headValue = headList.get(0);
                setHeadCellValue(lineCellStyle,sheet,headValue);
                Row line = sheet.createRow(3);
                // 设置行头标题
                setRowLineTitle(line);
                // 设置行单元格边框
                for (int i = 0; i <= 16; i++){
                    Cell cell = line.getCell((short) i);
                    cell.setCellStyle(setLineCellStyle(lineCellStyle));
                }
                // 给行赋值
                int m = 1;
                for (int i = 1, min = (lineList.size() - j * length + 1) > (length + 1) ? (length + 1)
                        : (lineList.size() - j * length + 1); i < min; i++) {
                    m++;
                    ReportDTO lineValue = lineList.get(length * (j) + i - 1);
                    line = sheet.createRow(i+3);
                    setLineCellValue(lineCellStyle,line,lineValue);
                }
                //固定前四行十七个列
                sheet.createFreezePane(17, 4);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                book.setSheetName(0, orgNme);
                sheet.getRow(0).getCell(0).setCellValue(orgNme+"盘点汇总报表");
                book.write(o);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                o.flush();
                o.close();
            }
        }
        File srcfile[] = new File[fileNames.size()];
        for (int i = 0, n = fileNames.size(); i < n; i++) {
            srcfile[i] = new File(fileNames.get(i));
        }
        System.out.println("==============FileZip=============="+new Date());
        FileZip.ZipFiles(srcfile, zip);
        FileInputStream inStream = new FileInputStream(zip);
        byte[] buf = new byte[4096];
        int readLength;
        while (((readLength = inStream.read(buf)) != -1)) {
            out.write(buf, 0, readLength);
        }
        System.out.println("==============FileZip=q============="+new Date());
        inStream.close();
    }

    @Async
    public void setHeadCellValue(CellStyle lineCellStyle, Sheet sheet, ReportDTO headValue) {
        //盘点人
        sheet.getRow(1).getCell(2).setCellValue(headValue.getColum5());
        //任务编号
        sheet.getRow(1).getCell(6).setCellValue(headValue.getColum3());
        //盘点部门
        sheet.getRow(1).getCell(10).setCellValue(orgNme);
        //开始盘点时间
        sheet.getRow(1).getCell(14).setCellValue(headValue.getColum6());
        //盘点类型
        sheet.getRow(2).getCell(2).setCellValue(headValue.getColum2());
        //盘点任务
        sheet.getRow(2).getCell(6).setCellValue(headValue.getColum4());
        //任务备注
        sheet.getRow(2).getCell(10).setCellValue(headValue.getColum8());
        //结束盘点时间
        sheet.getRow(2).getCell(14).setCellValue(headValue.getColum7());
        // 设置头单元格边框格式
        for (int i = 1; i <= 2; i++){
            Row row = sheet.getRow(i);
            for (int r = 0; r <= 16; r++){
                Cell cell = row.getCell((short) r);
                cell.setCellStyle(setLineCellStyle(lineCellStyle));
            }
        }

    }

    // 设置头信息
    @Async
    public void setRowHeadTitle(Sheet sheet) {
        Row head = sheet.createRow(1);
        head.setHeight((short)500);
        head.createCell(0).setCellValue("盘点人");
        head.createCell(1);
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 1));
        head.createCell(2);
        head.createCell(3);
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
        head.createCell(4).setCellValue("任务编号");
        head.createCell(5).setCellValue("");
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 4, 5));
        head.createCell(6);
        head.createCell(7);
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 6, 7));
        head.createCell(8).setCellValue("盘点部门");
        head.createCell(9);
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 8, 9));
        head.createCell(10);
        head.createCell(11);
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 10, 11));
        head.createCell(12).setCellValue("开始盘点时间");
        head.createCell(13);
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 12, 13));
        head.createCell(14);
        head.createCell(15);
        head.createCell(16);
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 14, 16));

        head = sheet.createRow(2);
        head.setHeight((short)500);
        head.createCell(0).setCellValue("盘点类型");
        head.createCell(1);
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 1));
        head.createCell(2);
        head.createCell(3);
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 2, 3));
        head.createCell(4).setCellValue("盘点任务");
        head.createCell(5).setCellValue("");
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 4, 5));
        head.createCell(6);
        head.createCell(7);
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 6, 7));
        head.createCell(8).setCellValue("任务备注");
        head.createCell(9);
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 8, 9));
        head.createCell(10);
        head.createCell(11);
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 10, 11));
        head.createCell(12).setCellValue("结束盘点时间");
        head.createCell(13);
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 12, 13));
        head.createCell(14);
        head.createCell(15);
        head.createCell(16);
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 14, 16));
    }

    // 设置主题
    @Async
    public void setTitle(CellStyle cellStyle,Workbook book,Sheet sheet) {
        Row row = sheet.createRow(0);
        Font ztFont = book.createFont();
        row.setHeightInPoints((short)40);
        Cell cell0 = row.createCell(0);
        cell0.setCellValue("盘点统计报表");
        cell0.setCellStyle(setHeadCellStyle(cellStyle,ztFont));
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 16));
    }

    // 给行单元格赋值
    @Async
    public void setLineCellValue(CellStyle cellStyle,Row row,ReportDTO countingLines) {
        row.setHeight((short)500);
        //清单编号
        row.createCell(0).setCellValue(countingLines.getColum1());
        //盘点状态
        row.createCell(1).setCellValue(countingLines.getColum2());
        //资产编码
        row.createCell(2).setCellValue(countingLines.getColum3());
        //标签号
        row.createCell(3).setCellValue(countingLines.getColum4());
        //固定编号
        row.createCell(4).setCellValue(countingLines.getColum5());
        //资产名称
        row.createCell(5).setCellValue(countingLines.getColum7());
        //品牌
        row.createCell(6).setCellValue(countingLines.getColum8());
        //型号
        row.createCell(7).setCellValue(countingLines.getColum9());
        //使用人
        row.createCell(8).setCellValue(countingLines.getColum10());
        //使用部门
        row.createCell(9).setCellValue(countingLines.getColum11());
        //所属部门
        row.createCell(10).setCellValue(countingLines.getColum12());
        //原位置
        row.createCell(11).setCellValue(countingLines.getColum13());
        //现位置
        row.createCell(12).setCellValue(countingLines.getColum14());
        //原状态
        row.createCell(13).setCellValue(countingLines.getColum15());
        //现状态
        row.createCell(14).setCellValue(countingLines.getColum16());
        //原性能
        row.createCell(15).setCellValue(countingLines.getColum17());
        //现性能
        row.createCell(16).setCellValue(countingLines.getColum18());
        for (int k = 0; k <= 16; k++){
            Cell cell = row.getCell((short) k);
            cell.setCellStyle(setLineCellStyle(cellStyle));
        }
        //获取标题名称  XXX盘点汇总报表
        orgNme = countingLines.getColum12();
    }

    // 设置头单元格边框
    @Async
    public CellStyle setHeadCellStyle(CellStyle cellStyle,Font ztFont) {
        // 设置字体
        if(Objects.nonNull(ztFont)){
            // 将字体设置为 默认颜色
            ztFont.setColor(Font.COLOR_NORMAL);
            ztFont.setBold(true);
            // 将字体大小设置为18px
            ztFont.setFontHeightInPoints((short)22);
            // 将“华文行楷”字体应用到当前单元格上
            ztFont.setFontName("宋体");
            cellStyle.setFont(ztFont);
        }
        cellStyle.setAlignment(HorizontalAlignment.CENTER);// 水平
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
        cellStyle.setWrapText(true); //文字换行
        return cellStyle;
    }

    // 设置行单元格边框
    @Async
    public CellStyle setLineCellStyle(CellStyle cellStyle) {
        cellStyle.setAlignment(HorizontalAlignment.CENTER);// 水平
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
        cellStyle.setWrapText(true); //文字换行
        cellStyle.setBorderBottom(BorderStyle.MEDIUM); //下边框
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);//左边框
        cellStyle.setBorderTop(BorderStyle.MEDIUM);//上边框
        cellStyle.setBorderRight(BorderStyle.MEDIUM);//右边框
        return cellStyle;
    }

    // 设置行头标题
    @Async
    public void setRowLineTitle(Row row) {
        row.setHeight((short)500);
        row.createCell(0).setCellValue("清单编号");
        row.createCell(1).setCellValue("盘点状态");
        row.createCell(2).setCellValue("资产编码");
        row.createCell(3).setCellValue("标签号");
        row.createCell(4).setCellValue("固定编号");
        row.createCell(5).setCellValue("资产名称");
        row.createCell(6).setCellValue("品牌");
        row.createCell(7).setCellValue("型号");
        row.createCell(8).setCellValue("使用人");
        row.createCell(9).setCellValue("使用部门");
        row.createCell(10).setCellValue("所属部门");
        row.createCell(11).setCellValue("原位置");
        row.createCell(12).setCellValue("现位置");
        row.createCell(13).setCellValue("原状态");
        row.createCell(14).setCellValue("现状态");
        row.createCell(15).setCellValue("原性能");
        row.createCell(16).setCellValue("现性能");
    }

    /**
     * 设置打印参数信息
     * @param sheet
     */
    @Async
    public void setPrintSetup(Sheet sheet) {
        PrintSetup ps = sheet.getPrintSetup();
        // 打印方向，true：横向，false：纵向(默认)
        ps.setLandscape(true);
        //打印质量
        ps.setVResolution((short)600);
        //纸张类型
        ps.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);
        // 页边距（上）
        sheet.setMargin(HSSFSheet.TopMargin,( double ) 0.1 );
        // 页边距（下）
        sheet.setMargin(HSSFSheet.BottomMargin,( double ) 0.2 );
        // 页边距（左）
        sheet.setMargin(HSSFSheet.LeftMargin,( double ) 0.1 );
        // 页边距（右）
        sheet.setMargin(HSSFSheet.RightMargin,( double ) 0.1 );
        //设置打印页面为水平居中
        sheet.setHorizontallyCenter(true);
        // 设置页脚
        sheet.getFooter().setCenter( "第" + HeaderFooter.page() + "页，共 " + HeaderFooter.numPages()+"页" );
        // 设置列宽
        sheet.setColumnWidth(1, 7*256);
        sheet.setColumnWidth(15, 7*256);
        sheet.setColumnWidth(16, 7*256);
    }

    /** 设置响应头 */
    @Async
    public void setResponseHeader(HttpServletResponse response) {
        try {
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + java.net.URLEncoder.encode(fileName, "UTF-8")
                    + ".xls");
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}