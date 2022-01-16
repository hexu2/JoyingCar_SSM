package com.hexu.joycar.util;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;


/**
 * 生成验证码图片
 * @author hexu
 *
 */
public class MakeCertPic {
	//定义验证码图片中可能出现的字符集，可根据需要修改
	private static char[] characters = {
			'a','b','c','d','e','f','g','h','i','j','k',
			'l','m','n','o','p','q','r','s','t','u',
			'v','w','x','y','z',
			'A','B','C','D','E','F','G','H','I','J','K',
			'L','M','N','O','P','Q','R','S','T','U','V',
			'W','X','Y','Z',
			'0','1','2','3','4','5','6','7','8','9'
	};

	/**
	 * 
	 * @param width 图片的宽度
	 * @param height 图片的高度
	 * @param os  输出的目的地
	 * @return  返回的是图片中对应的字符串
	 */
	public static String getCertPic(int width,int height,OutputStream os){

		if(width < 0){
			width = 60;
		}
		if(height < 0){
			height = 20;
		}
		
		//定义内存图像
		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		
		//绘制image图像
		
		//获得一个画笔
		Graphics g = image.getGraphics();
		//设置背景色
		//g.setColor(new Color(0xDCDCDC));
		//绘制矩形边框
		g.drawRect(0, 0, width, height);
		
		g.drawRect(0, 0, width - 1, height - 1);
		
		StringBuffer certPicStrBuffer = new StringBuffer();
		//随机生成产生的验证码
		
		for(int i = 0; i < 4; i++){
			certPicStrBuffer.append(characters[(int)(Math.random()*characters.length)]);
		}
		
		//将验证码显示到图像中
		g.setFont(new Font("Atlantic Inline",Font.PLAIN,18));
		
		String cerPicBufferStr = certPicStrBuffer.toString();
		g.drawString(cerPicBufferStr.substring(0,1), 8, 17);
		g.drawString(cerPicBufferStr.substring(1,2), 20, 15);
		g.drawString(cerPicBufferStr.substring(2,3), 35, 18);
		g.drawString(cerPicBufferStr.substring(3,4), 45, 15);
		
		Random random = new Random();
		
		//干扰线  防止机器人程序恶意扫码
		for(int i = 0; i < 20; i++){
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			g.drawOval(x, y, 1, 1);
		}
		g.dispose();
		
		try {
			ImageIO.write(image, "JPEG", os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return certPicStrBuffer.toString();
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		File f = new File("/opt/test.jpeg");
		FileOutputStream os = null;
		os = new FileOutputStream(f);
		MakeCertPic.getCertPic(60, 20, os);
	}
}