package framework.fileutils;

import framework.conditioncheck.PreConditionCheck;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EXCELReader {
    private String workbookName;
    private String sheetName;

    public EXCELReader(String workbookName, String sheetName) {
        this.workbookName = workbookName;
        this.sheetName = sheetName;
    }

    //This method convert all cell values into String format
    public static List<String> getRowDataFromExcelSheet(String workbookName, String sheetName) throws IOException {
        PreConditionCheck.checkNotNullNotBlankOrEmpty(workbookName, "Excel File Name can not be null/empty");
        PreConditionCheck.checkNotNullNotBlankOrEmpty(sheetName, "SheetName can not be null/empty");
        List<String> rowValues = new ArrayList<>();
        File excelFile = new File(workbookName);
        if (!excelFile.isFile()) {
            throw new FileNotFoundException(workbookName + " File is not available. Please check and run again");
        }
        FileInputStream fileInput = new FileInputStream(excelFile);
        Workbook workbook = new XSSFWorkbook(fileInput);
        Sheet sheet = workbook.getSheet(sheetName);
        Iterator<Row> rows = sheet.iterator();
        while (rows.hasNext()) {
            Row currentRow = rows.next();
            Iterator<Cell> cells = currentRow.cellIterator();
            while(cells.hasNext()){
              Cell cell = cells.next();
              cell.setCellType(1);
              if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                  rowValues.add(cell.getStringCellValue());
              } else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                  rowValues.add(Double.valueOf(cell.getNumericCellValue()).toString());
              } else if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){
                  rowValues.add(Boolean.valueOf(cell.getBooleanCellValue()).toString());
              } else if(DateUtil.isCellDateFormatted(cell)){
                  rowValues.add(cell.getDateCellValue().toString());
              } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK){
                  rowValues.add("");
              }
            }
        }
        return rowValues;
    }

    public static void main(String[] args) throws IOException {
           String fileNameWithPath = "E:\\Corridor\\Corridor\\ApplicationUnderTest\\src\\test\\java\\com\\corridor\\testdata\\CorridorTestData.xlsx";
           String sheetName = "myprofile";
           List<String> excelInput = getRowDataFromExcelSheet(fileNameWithPath,sheetName);
           for(String s: excelInput){
               System.out.println(s);
           }
    }
}
