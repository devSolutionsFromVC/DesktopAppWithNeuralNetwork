package com.solution.fromVC.fxconfig;

import com.solution.fromVC.model.Assets;
import com.solution.fromVC.model.Risk;
import com.solution.fromVC.model.Threat;
import com.solution.fromVC.model.Vulnerability;
import com.solution.fromVC.service.AssetServiceImp;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.*;
import java.util.List;

public class ExcelController {

    private static List<Assets> assetsList = new AssetServiceImp().getAsset();
    private static HSSFWorkbook workbook = new HSSFWorkbook();
    private static HSSFSheet sheet = workbook.createSheet("Risks statistic");
    private static HSSFCellStyle rowStyle;
    private static int rowNum = 0;
    private static int riskResultCol = 10;
    private static int riskValueCol = 9;
    private static int riskLossCol = 8;
    private static int riskLikelihoodCol = 7;
    private static int vulCol = 6;
    private static int threatCol = 5;
    private static int worthCol = 4;
    private static int accessibilityCol = 3;
    private static int integrityCol = 2;
    private static int confidentialityCol = 1;
    private static int assetName = 0;

    public static void main(String[] args) {
//        workbook = new HSSFWorkbook();
//        sheet = workbook.createSheet("Risks statistic");

//        List<ExcelData> dataList =
        try {
            fillData();
        }catch (IOException e){
            e.fillInStackTrace();
        }

        setSheetStyle();

//        for(ExcelData data : dataList){
//            createSheetHeader(sheet, ++rowNum, data);
//        }

        try(FileOutputStream fileOutputStream = new FileOutputStream(new File("G:\\Risk Statistic File.xls"))){
            workbook.write(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        setHeaderStyle("Risks statistic");
    }

    private static void setSheetStyle(){
        HSSFFont contentFont = createFont(HSSFColor.BLACK.index, (short) 10, false);

        rowStyle = createStyle(contentFont, HorizontalAlignment.CENTER,   HSSFColor.GREY_25_PERCENT.index, true, HSSFColor.GREY_80_PERCENT.index);

        rowCreate();
    }

    private static void rowCreate() {
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("Asset");
        row.createCell(1).setCellValue("Confidentiality");
        row.createCell(2).setCellValue("Integrity");
        row.createCell(3).setCellValue("Accessibility");
        row.createCell(4).setCellValue("Worth");
        row.createCell(5).setCellValue("Threat");
        row.createCell(6).setCellValue("Vulnerability");
        row.createCell(7).setCellValue("Likelihood");
        row.createCell(8).setCellValue("Loss");
        row.createCell(9).setCellValue("Value");
        row.createCell(10).setCellValue("Risk solution");
        row.createCell(11).setCellValue("Ways to reduce");
        row.setRowStyle(rowStyle);
    }

    private static void setHeaderStyle(String sheetName) {
        try (FileInputStream fileInputStream = new FileInputStream(new File("G:\\Risk Statistic File.xls"))){
            workbook = new HSSFWorkbook(fileInputStream);
            sheet = workbook.getSheet(sheetName);

            HSSFFont font = workbook.createFont();
            font.setBold(true);
            HSSFFont headerFont  = createFont(HSSFColor.WHITE.index, (short)12, true);
            HSSFCellStyle headerStyle  = createStyle(headerFont,  HorizontalAlignment.CENTER, HSSFColor.BLUE_GREY.index,true, HSSFColor.WHITE.index);

            columnsWidth();

            Row row = sheet.getRow(0);
            for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
                row.getCell(i).setCellStyle(headerStyle);
                row.setHeight((short) 600);
            }

            FileOutputStream out = new FileOutputStream(new File("G:\\Risk Statistic File.xls"));
            workbook.write(out);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void columnsWidth() {
        sheet.setColumnWidth(0, 5000);
        sheet.setColumnWidth(1, 5000);
        sheet.setColumnWidth(2, 5000);
        sheet.setColumnWidth(3, 5000);
        sheet.setColumnWidth(5, 5000);
        sheet.setColumnWidth(5, 5000);
        sheet.setColumnWidth(6, 10000);
        sheet.setColumnWidth(7, 5000);
        sheet.setColumnWidth(8, 3000);
        sheet.setColumnWidth(9, 3000);
        sheet.setColumnWidth(10, 5000);
        sheet.setColumnWidth(11, 10000);
    }

    private static HSSFFont createFont(short fontColor, short fontHeight, boolean fontBold) {
        HSSFFont font = workbook.createFont();
        font.setBold(fontBold);
        font.setColor(fontColor);
        font.setFontName("Arial");
        font.setFontHeightInPoints(fontHeight);

        return font;
    }

    private static HSSFCellStyle createStyle(HSSFFont font, HorizontalAlignment cellAlign, short cellColor, boolean cellBorder, short cellBorderColor) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setAlignment(cellAlign);
        style.setFillForegroundColor(cellColor);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        if (cellBorder) {
            style.setBorderTop(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
            style.setBorderBottom(BorderStyle.THIN);

            style.setTopBorderColor(cellBorderColor);
            style.setLeftBorderColor(cellBorderColor);
            style.setRightBorderColor(cellBorderColor);
            style.setBottomBorderColor(cellBorderColor);
        }

        return style;
    }

    private static void fillData() throws IOException{
//        List<ExcelData> dataList = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream(new File("G:\\Risk Statistic File.xls"));
        workbook = new HSSFWorkbook(fileInputStream);
        sheet = workbook.getSheetAt(rowNum);
        Row row = sheet.createRow(1);
        int firstRow = 1;
        int mergeCellsNumber;
        for(Assets asset : assetsList){
            List<Threat> threatList = asset.getThreatList();
            mergeCellsNumber = threatSetData(threatList);
            if(!(mergeCellsNumber < firstRow)){
                cellMerge(firstRow, mergeCellsNumber - 1, worthCol, worthCol);
                cellMerge(firstRow, mergeCellsNumber - 1, accessibilityCol, accessibilityCol);
                cellMerge(firstRow, mergeCellsNumber - 1, integrityCol, integrityCol);
                cellMerge(firstRow, mergeCellsNumber - 1, confidentialityCol, confidentialityCol);
            }
            row.createCell(assetName).setCellValue(asset.getName());
            row.createCell(worthCol).setCellValue(asset.getWorth());
            row.createCell(accessibilityCol).setCellValue(asset.getAccessibility());
            row.createCell(integrityCol).setCellValue(asset.getIntegrity());
            row.createCell(confidentialityCol).setCellValue(asset.getConfidentiality());
            sheet = workbook.createSheet("Risk Statistic " + rowNum++);
            row = sheet.createRow(1);
            setSheetStyle();
//            setHeaderStyle("Risk Statistic " + rowNum);
//            newSheetStyle();
        }

//
//        dataList.add(new ExcelData("asset", "-", "-", "+", "", "", "", "", "", "", "", ""));
//
//        return dataList;
    }

    private static void newSheetStyle() {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFFont headerFont  = createFont(HSSFColor.WHITE.index, (short)12, true);
        HSSFCellStyle headerStyle  = createStyle(headerFont,  HorizontalAlignment.CENTER, HSSFColor.BLUE_GREY.index,true, HSSFColor.WHITE.index);

        columnsWidth();

        Row row = sheet.getRow(0);
        for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
            row.getCell(i).setCellStyle(headerStyle);
            row.setHeight((short) 600);
        }

        try(FileOutputStream fileOutputStream = new FileOutputStream(new File("G:\\Risk Statistic File.xls"))){
            workbook.write(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int threatSetData(List<Threat> threatList) {
        Row row = sheet.createRow(1);
        int firstRowIndex = 1;
        int lastRowIndex = 0;
        int vulSize = 0;
        for (Threat threat: threatList){
            List<Vulnerability> vulnerability = threat.getVulnerability();
            int sizeForThreat = vulnerabilitySetData(vulnerability);
            lastRowIndex += sizeForThreat;
            cellMerge(firstRowIndex, lastRowIndex, threatCol, threatCol);
            row.createCell(threatCol).setCellValue(threat.getName());
            firstRowIndex += sizeForThreat;
            vulSize = vulnerability.size();
        }
        return vulSize;
    }

    private static int vulnerabilitySetData(List<Vulnerability> vulnerabilities) {
        Row row = sheet.createRow(1);
        int sizeForThreat = 0;
        for (Vulnerability vulnerability : vulnerabilities){
            String vulName = vulnerability.getName();
            row.createCell(vulCol).setCellValue(vulName);
            Risk risk = vulnerability.getRisk();
            row.createCell(riskLikelihoodCol).setCellValue(risk.getLikelihood());
            row.createCell(riskLossCol).setCellValue(risk.getLoss());
            row.createCell(riskValueCol).setCellValue(risk.getValue());
            row.createCell(riskResultCol).setCellValue(risk.getProcessing().getProcessName());
            sizeForThreat++;
        }
        return sizeForThreat;
    }

    private static void cellMerge(int firstRow, int lastRow, int firstCol, int lastCol) {
        sheet.addMergedRegion(new CellRangeAddress(
                firstRow, lastRow, firstCol, lastCol
        ));
    }


//    private static void createSheetHeader(HSSFSheet sheet, int rowNum, ExcelData data) {
//        Row row = sheet.createRow(rowNum);
//
//        row.createCell(0).setCellValue(data.getAsset());
//        row.createCell(1).setCellValue(data.getConfidentiality());
//        row.createCell(2).setCellValue(data.getIntegrity());
//        row.createCell(3).setCellValue(data.getAccessibility());
//        row.createCell(4).setCellValue(data.getAssetWorth());
//        row.createCell(5).setCellValue(data.getThreat());
//        row.createCell(6).setCellValue(data.getVulnerability());
//        row.createCell(7).setCellValue(data.getLikelihood());
//        row.createCell(8).setCellValue(data.getLoss());
//        row.createCell(9).setCellValue(data.getValue());
//        row.createCell(10).setCellValue(data.getRiskResult());
//        row.createCell(11).setCellValue(data.getWaysToReduce());
//    }
}
