package utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtils {

    public static Object[][] readExcelData(String rowName) {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testdata.xlsx";
        String sheetName = "data";
        Map<String, String> dataMap = new HashMap<>();

        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            DataFormatter formatter = new DataFormatter();

            // Find the row with the given row name
            Row headerRow = sheet.getRow(0); // Assumed to be the first row
            Row targetRow = null;

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row
                String currentRowName = formatter.formatCellValue(row.getCell(0)); // Assuming row name is in the first column
                if (currentRowName.equals(rowName)) {
                    targetRow = row;
                    break;
                }
            }

            if (targetRow == null) {
                throw new IllegalArgumentException("Row name not found: " + rowName);
            }

            // Iterate through the columns and store the values in the map
            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                String columnName = formatter.formatCellValue(headerRow.getCell(i));
                String cellValue = formatter.formatCellValue(targetRow.getCell(i));
                dataMap.put(columnName, cellValue);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        Object[][] data = new Object[1][1];
        data[0][0] = dataMap;
        return data;
    }
}
