package com.example.nimap.PayrollTask.springboot.Dto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.web.multipart.MultipartFile;

import com.example.nimap.PayrollTask.springboot.Entities.Excel;


public class ExcelHelper {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "Id", "candidate", "recruiter", "job" };
	static String SHEET = "Sheet1";

	public static boolean hasExcelFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {

			return false;
		}

		return true;
	}

	public static ByteArrayInputStream tutorialsToExcel(List<Excel> tutorials) {

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet(SHEET);

			// Header
			Row headerRow = sheet.createRow(0);

			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
			}

			int rowIdx = 1;
			for (Excel tutorial : tutorials) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(tutorial.getId());
				row.createCell(1).setCellValue(tutorial.getCandidate());
				row.createCell(2).setCellValue(tutorial.getRecruiter());
				row.createCell(3).setCellValue(tutorial.getJob());
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		}
	}

	public static List<Excel> excelToTutorials(InputStream is) {

		try {
			System.out.println("asfhhjh");
			Workbook workbook = new XSSFWorkbook(is);

			Sheet sheet = workbook.getSheet(SHEET);
			Iterator<Row> rows = sheet.iterator();
			System.out.println("sdjkuh");
			List<Excel> tutorials = new ArrayList<Excel>();

			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();

				// skip header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}
				System.out.println("sjbbb");
				Iterator<Cell> cellsInRow = currentRow.iterator();

				Excel tutorial = new Excel();
				System.out.println("sjbbb1");
				int cellIdx = 0;

				while (cellsInRow.hasNext()) {
					System.out.println("sjbbb2");
					Cell currentCell = cellsInRow.next();
					System.out.println("sjbbb3");

					switch (cellIdx) {
					
					case 0:
						tutorial.setId((long) currentCell.getNumericCellValue());
						System.out.println("sjbbb4");
						break;

					case 1:
						tutorial.setCandidate(currentCell.getStringCellValue());
						System.out.println("sjbbb5");
						break;

					case 2:
						tutorial.setRecruiter(currentCell.getStringCellValue());
						break;

					case 3:
						tutorial.setJob(currentCell.getStringCellValue());
						break;

					default:
						break;
					}

					cellIdx++;
				}

				tutorials.add(tutorial);

			}

			workbook.close();

			return tutorials;

		} catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	}
}