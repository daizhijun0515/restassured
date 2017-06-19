package com.benson;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/19 0019.
 */
public class ExcelUtils {

    public static Object[][] getData(Object object, Method method) throws IOException {
        InputStream inputStream = object.getClass().getClassLoader().getResourceAsStream(object.getClass().getSimpleName() + ".xlsx");
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheet(method.getName());
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        Row row = sheet.getRow(0);
        String[] columnNames = new String[row.getLastCellNum()];
        for (int j = 0; j < row.getLastCellNum(); j++) {
            columnNames[j] = row.getCell(j).getStringCellValue();
        }
        Object[][] records = new Object[rowCount][];
        for (int i = 1; i < rowCount + 1; i++) {
            row = sheet.getRow(i);
            Map<String, String> map = new HashMap<>();
            for (int j = 0; j < row.getLastCellNum(); j++) {
                map.put(columnNames[j], row.getCell(j).getStringCellValue());
            }
            records[i -1] = new Object[]{map};
        }

        return records;
    }
}
