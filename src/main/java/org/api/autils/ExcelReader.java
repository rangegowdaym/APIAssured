package org.api.autils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Utility class for reading data from Excel files.
 * Provides methods to extract specific row data as a map based on a given row name.
 */
public class ExcelReader {

    /**
     * Reads a specific row from an Excel sheet and returns the data as a map.
     * The first row of the sheet is treated as the header, and the specified row is matched by its first cell value.
     *
     * @param filePath  Path to the Excel file.
     * @param sheetName Name of the sheet to read from.
     * @param rowName   Name of the row to find (matched against the first cell in each row).
     * @return A map where the keys are the column headers and the values are the corresponding cell values in the row.
     */
    public static Map<String, String> getRowDataAsMap(String filePath, String sheetName, String rowName) {
        Map<String, String> rowDataMap = new LinkedHashMap<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) return rowDataMap;

            Iterator<Row> rows = sheet.iterator();
            if (!rows.hasNext()) return rowDataMap;

            List<String> headers = getHeaders(rows.next());
            while (rows.hasNext()) {
                Row row = rows.next();
                if (isMatchingRow(row, rowName)) {
                    populateRowData(rowDataMap, headers, row);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rowDataMap;
    }

    /**
     * Extracts the headers from the first row of the sheet.
     *
     * @param headerRow The first row of the sheet, treated as the header row.
     * @return A list of column headers.
     */
    private static List<String> getHeaders(Row headerRow) {
        List<String> headers = new ArrayList<>();
        for (Cell cell : headerRow) {
            headers.add(cell.getStringCellValue());
        }
        return headers;
    }

    /**
     * Checks if a given row matches the specified row name.
     *
     * @param row     The row to check.
     * @param rowName The name of the row to match.
     * @return True if the row matches the specified name, false otherwise.
     */
    private static boolean isMatchingRow(Row row, String rowName) {
        Cell firstCell = row.getCell(0);
        return firstCell != null && firstCell.getCellType() == CellType.STRING &&
                firstCell.getStringCellValue().equalsIgnoreCase(rowName);
    }

    /**
     * Populates the map with data from the specified row.
     *
     * @param rowDataMap The map to populate with row data.
     * @param headers    The list of column headers.
     * @param row        The row containing the data.
     */
    private static void populateRowData(Map<String, String> rowDataMap, List<String> headers, Row row) {
        for (int i = 0; i < headers.size(); i++) {
            Cell cell = row.getCell(i);
            rowDataMap.put(headers.get(i), getCellValue(cell));
        }
    }

    /**
     * Retrieves the value of a cell as a string.
     *
     * @param cell The cell to retrieve the value from.
     * @return The string representation of the cell value.
     */
    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue();
            case NUMERIC: return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            case FORMULA: return cell.getCellFormula();
            default: return "";
        }
    }

    /**
     * Main method for testing the ExcelReader utility.
     * Reads a specific row from a test Excel file and prints the data to the console.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        String filePath = System.getProperty("user.dir") + "/src/test/resources/testdata/file.xlsx";
        String sheetName = "Sheet1";
        String rowName = "LoginTest";

        Map<String, String> rowData = getRowDataAsMap(filePath, sheetName, rowName);
        if (rowData.isEmpty()) {
            System.out.println("Row with name '" + rowName + "' not found.");
        } else {
            rowData.forEach((k, v) -> System.out.println(k + ": " + v));
        }
    }
}