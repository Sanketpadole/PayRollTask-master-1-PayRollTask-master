package com.springboot.nimap.PayrollTask.springboot.Util;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.nimap.PayrollTask.springboot.Entities.JobEntity;

public class ExcelExportUtils {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<JobEntity> jobList;

	public ExcelExportUtils(XSSFWorkbook workbook, XSSFSheet sheet, List<JobEntity> jobList) {
		super();
		this.workbook = workbook;
		this.sheet = sheet;
		this.jobList = jobList;
	}

	public ExcelExportUtils(List<JobEntity> jobList) {
		super();
		this.jobList = jobList;

		workbook = new XSSFWorkbook();
	}

	public ExcelExportUtils() {
		super();

	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Double) {
			cell.setCellValue((Double) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else if (value instanceof Long) {
			cell.setCellValue((Long) value);
		} else if (value instanceof Date) {
			cell.setCellValue((Date) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

	private void createHeaderRow() {
		sheet = workbook.createSheet("Customer Information");

		Row row = sheet.createRow(0);
		CellStyle style = workbook.createCellStyle();

		XSSFFont font = workbook.createFont();
		font.setBold(true);

		font.setFontHeight(20);
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);

		createCell(row, 0, "Customer Information", style);

		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));

		font.setFontHeightInPoints((short) 10);

		row = sheet.createRow(1);
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, "ID", style);

		createCell(row, 1, "jobname", style);

		createCell(row, 2, " description", style);

	}

	private void writeCustomerData() {

		int rowCount = 2;

		CellStyle style = workbook.createCellStyle();

		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		for (JobEntity jobsList : jobList) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			createCell(row, columnCount++, jobsList.getId(), style);

			createCell(row, columnCount++, jobsList.getJobName(), style);

			createCell(row, columnCount++, jobsList.getDescription(), style);
		}
	}

	public void exportDataToExcel(HttpServletResponse response) throws IOException {
		createHeaderRow();
		writeCustomerData();
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}

}
