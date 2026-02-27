package utils;

import org.apache.poi.ss.usermodel.*;
import java.io.InputStream;
import java.util.*;

public class ExcelUtils {

    private static Sheet sheet;

    static {
        loadExcel();
    }

    private static void loadExcel() {
        try {
            InputStream is = ExcelUtils.class
                    .getClassLoader()
                    .getResourceAsStream("testdata/TestData.xlsx");

            Workbook workbook = WorkbookFactory.create(is);
            sheet = workbook.getSheetAt(0);

        } catch (Exception e) {
            throw new RuntimeException("Excel loading failed", e);
        }
    }

    public static Map<String, String> getTestData(String testCaseName) {

        Map<String, String> data = new HashMap<>();
        Row headerRow = sheet.getRow(0);

        for (Row row : sheet) {

            if (row.getRowNum() == 0) continue;

            if (row.getCell(0).getStringCellValue()
                    .equalsIgnoreCase(testCaseName)) {

                for (int i = 0; i < headerRow.getLastCellNum(); i++) {

                    String key = headerRow.getCell(i)
                            .getStringCellValue();

                    String value = row.getCell(i) != null
                            ? row.getCell(i).toString()
                            : "";

                    data.put(key.trim().toLowerCase(),
                            value.trim());



                }
                break;
            }
        }

        return data;
    }
}
