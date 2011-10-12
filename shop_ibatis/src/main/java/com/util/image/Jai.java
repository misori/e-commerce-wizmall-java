package com.util.image;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;

public class Jai {
	/**
	 *
	 * @param in		- 원본파일명
	 * @param out		- 썸네일 파일명
	 * @param width		- 썸네일 파일 가로 크기
	 * @param height	- 썸네일 파일 세로 크기
	 * @param format	- 썸네일 파일 현태 (jpg or gif..)
	 * @return
	 */
	public static boolean convert(String in, String out, int width, int height, String format){

		File saveFile	= new File(out);

		RenderedOp rOp	= JAI.create("fileload", in);
		BufferedImage im	= rOp.getAsBufferedImage();

		// 원본 이미지의 비율에 맞게 썸네일 이미지 비율을 정하는 부분
		float cvtWidth	= 0.0f;
		float cvtHeight	= 0.0f;
		if(im.getWidth() > im.getHeight()){
			cvtWidth	= width;
			cvtHeight	= (float)height * ((float)im.getHeight() / (float)im.getWidth());
		}else if(im.getWidth() < im.getHeight()){
			cvtWidth	= (float)width * ((float)im.getWidth() / (float)im.getHeight());
			cvtHeight	= height;
		}else{
			cvtWidth	= width;
			cvtHeight	= height;
		}

		BufferedImage thumb	= new BufferedImage((int)cvtWidth, (int)cvtHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2	= thumb.createGraphics();

		g2.drawImage(im, 0, 0, (int)cvtWidth, (int)cvtHeight, null);

		try{
			return ImageIO.write(thumb, format, saveFile);
		}catch(IOException io){
			return false;
		}

	}
}
