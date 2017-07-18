package com.readExcell.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi11.hssf.usermodel.HSSFWorkbook;
import org.apache.poi11.ss.usermodel.Cell;
import org.apache.poi11.ss.usermodel.Row;
import org.apache.poi11.ss.usermodel.Sheet;
import org.apache.poi11.ss.usermodel.Workbook;
import org.apache.poi11.xssf.usermodel.XSSFWorkbook;

public class readExcell {
	private Workbook wb;
	private Sheet sheet;
	private int rowNum;
	private int colNum;
	private static int EXCELL_2003 = 2003;
	private static int EXCELL_2007 = 2007;

	public void readExcell(String path) throws IOException {
		try {
			InputStream in = new FileInputStream(new File(path));
			if (getExcellVersion(path) == EXCELL_2003) {
				this.wb = new HSSFWorkbook(in);
			} else if (getExcellVersion(path) == EXCELL_2007) {
				this.wb = new XSSFWorkbook(in);
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.sheet = wb.getSheetAt(0);
		this.rowNum = sheet.getLastRowNum();
		this.colNum = sheet.getRow(0).getPhysicalNumberOfCells();

	}

	/**
	 * 功能：获取excell版本
	 */
	public static int getExcellVersion(String fileName) {
		if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
			return EXCELL_2007;
		}
		if (fileName.matches("^.+\\.(?i)(xls)$")) {
			return EXCELL_2003;
		}
		return 0;
	}

	/**
	 * 读取表头数据
	 * 
	 * @return
	 */
	public String[] readExcelTitle() {
		Row row = sheet.getRow(0);
		// 标题总列数
		int colNum = row.getPhysicalNumberOfCells();
		System.out.println("colNum:" + colNum);
		String[] title = new String[colNum];
		for (int i = 0; i < colNum; i++) {
			title[i] = getStringCellValue(row.getCell((short) i));
			// title[i] = getCellFormatValue(row.getCell((short) i));
		}
		return title;
	}

	/**
	 * 获取单元格数据内容为字符串类型的数据
	 * 
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 */
	private String getStringCellValue(Cell cell) {
		String strCell = "";
		if (cell == null) {
			return "";
		}
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			strCell = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			strCell = String.valueOf(cell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		default:
			strCell = "";
			break;
		}
		if ("".equals(strCell) || strCell == null) {
			return "";
		}

		return strCell;
	}

	/**
	 * 读取某一行数据
	 * 
	 * @param rowIndex
	 *            行数
	 * @return
	 */
	public ArrayList<String> getRowDate(int rowIndex) {
		ArrayList<String> rowData = new ArrayList<String>();
		Row row = sheet.getRow(rowIndex);
		int j = 0;
		while (j < colNum) {
			String value = getStringCellValue(row.getCell((short) j)).trim();
			if ("".equals(value)) {
				value = null;
			}
			rowData.add(value);
			j++;
		}
		return rowData;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public int getColNum() {
		return colNum;
	}

	public void setColNum(int colNum) {
		this.colNum = colNum;
	}

	public static void main(String[] args) {
		try {
			// 对读取Excel表格标题
			Test excelReader = new Test();
			excelReader.readExcell("21.xlsx");
			String[] title = excelReader.readExcelTitle();
			for (int i = 0; i < title.length; i++) {
				System.out.print(title[i]);
			}
			/*
			 * String[] title = excelReader.readExcelTitle();
			 * System.out.println("获得Excel表格的标题:"); for (String s : title) {
			 * System.out.print(s + " "); }
			 */

			int rowcount = excelReader.getRowNum();
			int columncount = excelReader.getColNum();
			System.out.println("行数：" + rowcount);
			System.out.println("列数：" + columncount);
			// 循环读取数据
			for (int i = 0; i <= rowcount; i++) {
				ArrayList<String> datas = excelReader.getRowDate(i);

				String rowstring = "";
				for (int j = 0; j < datas.size(); j++) {
					System.out.println(datas.get(j));
					rowstring += datas.get(j) + " ";
				}
				System.out.println(rowstring);
			}

		} catch (FileNotFoundException e) {
			System.out.println("未找到指定路径的文件!");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
