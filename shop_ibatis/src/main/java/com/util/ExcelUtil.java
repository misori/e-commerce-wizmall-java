package com.util;

import java.io.File;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WriteException;

public class ExcelUtil {
	/****************************************************************************
	 * 제목셀의 속성을 정의함.
	 * @param column
	 * @param record
	 * @param name
	 * @param size
	 * @param sheet
	 * @param color
	 * @param bgcolor
	 * @return
	 ****************************************************************************/
	public static WritableSheet getFormatTitle(int column, int record, String name, int size, WritableSheet sheet, Colour color, Colour bgcolor){

		// 제목컬럼 폰트, 색상설정 : 폰트, 사이즈, 글씨굵기, 이택릭체, 언더라인, 색상
		WritableFont font = new WritableFont(WritableFont.createFont("맑은 고딕"), 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, color!=null ? color : Colour.WHITE);

		// 제목컬럼 생성
		WritableCellFormat cell = new WritableCellFormat(font);

		// 라벨생성
		Label label = null;

		try{

			// 가로정렬지정(기본값:가운데정렬)
			cell.setAlignment(Alignment.CENTRE);

			// 세로정렬지정(가운데정렬)
			cell.setVerticalAlignment(VerticalAlignment.CENTRE);

			// 테두리지정
			cell.setBorder(Border.ALL, BorderLineStyle.THIN);

			// 배경색상(기본값:WHITE)
			cell.setBackground(bgcolor!=null ? bgcolor : Colour.GRAY_50);

			// 제목으로 사용할 라벨을 생성함.
			label = new Label(column, record, name, cell);

			// 컬럼사이즈 조정 (0번째)의 넓이 설정
			sheet.setColumnView(column, size);

			// 컬럼에 라벨을 설정함.
			sheet.addCell(label);

		 }catch(WriteException e){
			 e.printStackTrace();
		}
		return sheet;
	}

	/****************************************************************************
	 * 데이터셀의 속성을 정의함.
	 * @param column 	: 컬럼순번
	 * @param record	: 레코드순번
	 * @param data		: 데이터
	 * @param alignment: 가로정렬
	 * @param bgcolor	: 배경색상
	 * @param wrap		: 개행문자 표시여부
	 * @return
	 ****************************************************************************/
	public static Label getFormatCell(int column, int record, String data, Alignment alignment, Colour bgcolor, boolean wrap){

		// 제목컬럼 폰트, 색상설정 : 폰트, 사이즈, 글씨굵기, 이택릭체, 언더라인, 색상
		WritableFont font = new WritableFont(WritableFont.createFont("맑은 고딕"), 9, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.GREY_80_PERCENT);
		// 제목컬럼 생성
		WritableCellFormat cell = new WritableCellFormat(font);
		// 라벨생성
		Label label = null;

		try{


			// 가로정렬지정(기본값:가운데정렬)
			cell.setAlignment(alignment!=null?alignment:Alignment.CENTRE);

			// 세로정렬지정(가운데정렬)
			cell.setVerticalAlignment(VerticalAlignment.CENTRE);

			// 배경색상(기본값:WHITE)
			cell.setBackground(bgcolor!=null?bgcolor:Colour.WHITE);

			// 테두리지정
			cell.setBorder(Border.ALL, BorderLineStyle.THIN);

			// 개행문자처리
			cell.setWrap(wrap);

			// 데이터를 라벨을 생성함.
			label = new Label(column, record, data, cell);

		 }catch(WriteException e){
			 e.printStackTrace();
		}
		return label;
	}

	/****************************************************************************
	 * 엑셀시트의 전체타이틀을 설정한다.
	 * @param sheet
	 * @return
	 ****************************************************************************/
	public static WritableSheet getSheetTitle(WritableSheet sheet, String titleStr){

		// 제목컬럼 폰트, 색상설정 : 폰트, 사이즈, 글씨굵기, 이택릭체, 언더라인, 색상
		WritableFont font = new WritableFont(WritableFont.createFont("맑은 고딕"), 16, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.GREY_80_PERCENT);

		// 제목컬럼 생성
		WritableCellFormat cell = new WritableCellFormat(font);

		// 라벨생성
		Label label = null;

		try{

			// 가로정렬지정(기본값:가운데정렬)
			cell.setAlignment(Alignment.LEFT);

			// 세로정렬지정(가운데정렬)
			cell.setVerticalAlignment(VerticalAlignment.CENTRE);

			// 테두리지정
			cell.setBorder(Border.BOTTOM, BorderLineStyle.THIN);

			// 배경색상(기본값:WHITE)
			cell.setBackground(Colour.WHITE);

			// 제목으로 사용할 라벨을 생성함.
			label = new Label(0, 0, titleStr , cell);

			// 컬럼사이즈 조정 (0번째)의 넓이 설정
			// sheet.setColumnView(column, size);

			// 컬럼에 라벨을 설정함.
			sheet.addCell(label);

			// 타이틀셀병합
			sheet.mergeCells(0,0,8,0);

		 }catch(WriteException e){
			 e.printStackTrace();
		}
		return sheet;
	}

	/****************************************************************************
	 * 해당파일을 삭제함.
	 * @param file
	 * @throws Exception
	 ****************************************************************************/
	public static void deleteFile(String file){
		try{
			File f = new File(file);
			if(f.isFile()) f.delete();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/****************************************************************************
	 * 엑셀시트의 전체타이틀을 설정한다.
	 * @param hap - 0 부터 시작해서 병합할 셀의 갯수
	 * @param sheet
	 * @return
	 ****************************************************************************/
	public static WritableSheet getSheetTitlehap(WritableSheet sheet, String titleStr, int hap){

		// 제목컬럼 폰트, 색상설정 : 폰트, 사이즈, 글씨굵기, 이택릭체, 언더라인, 색상
		WritableFont font = new WritableFont(WritableFont.createFont("맑은 고딕"), 16, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.GREY_80_PERCENT);

		// 제목컬럼 생성
		WritableCellFormat cell = new WritableCellFormat(font);

		// 라벨생성
		Label label = null;

		try{

			// 가로정렬지정(기본값:가운데정렬)
			cell.setAlignment(Alignment.CENTRE);

			// 세로정렬지정(가운데정렬)
			cell.setVerticalAlignment(VerticalAlignment.CENTRE);

			// 테두리지정
			cell.setBorder(Border.BOTTOM, BorderLineStyle.THIN);

			// 배경색상(기본값:WHITE)
			cell.setBackground(Colour.WHITE);

			// 제목으로 사용할 라벨을 생성함.
			label = new Label(0, 0, titleStr , cell);

			// 컬럼사이즈 조정 (0번째)의 넓이 설정
			// sheet.setColumnView(column, size);

			// 컬럼에 라벨을 설정함.
			sheet.addCell(label);

			// 타이틀셀병합
			sheet.mergeCells(0,0,hap,0);

		 }catch(WriteException e){
			 e.printStackTrace();
		}
		return sheet;
	}


	/****************************************************************************
	 * CellFormat Number
	 * @param column 	: 컬럼순번
	 * @param record	: 레코드순번
	 * @param data		: 데이터 ( INT )
	 * @param alignment : 가로정렬
	 * @param bgcolor	: 배경색상
	 * @param wrap		: 개행문자 표시여부
	 * @return
	 ****************************************************************************/
	public static Number getFormatCellInt(int column, int record, int data, Alignment alignment, Colour bgcolor, boolean wrap){

		// Cell Date put
		Number num = new Number(column, record,  data);

		try{

			// basic font format (폰트, 색상설정 : 폰트, 사이즈, 글씨굵기, 이택릭체, 언더라인, 색상)
			WritableFont font = new WritableFont(WritableFont.createFont("맑은 고딕"), 9, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.GREY_80_PERCENT);
			// Cell number format
			NumberFormats exp4 = new NumberFormats();

			@SuppressWarnings("static-access")
			WritableCellFormat cell = new WritableCellFormat(exp4.FORMAT1);
			cell.setFont(font);

			// 가로정렬지정(기본값:가운데정렬)
			cell.setAlignment(alignment!=null?alignment:Alignment.CENTRE);

			// 세로정렬지정(가운데정렬)
			cell.setVerticalAlignment(VerticalAlignment.CENTRE);

			// 배경색상(기본값:WHITE)
			cell.setBackground(bgcolor!=null?bgcolor:Colour.WHITE);

			// 테두리지정
			cell.setBorder(Border.ALL, BorderLineStyle.THIN);

			// 개행문자처리
			cell.setWrap(wrap);

			// Cell Format insert
			num.setCellFormat(cell);
		 }catch(WriteException e){
			 e.printStackTrace();
		}
		return num;
	}
}
