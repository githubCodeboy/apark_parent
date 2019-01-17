/*
 * 技术支持：广州博见信息科技有限公司
 */
package com.apark.common.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 验证码
 * 
 * @说明 JSP获取验证码及图片
 * @作者 黄瑞文
 * @版本 1.0 2009-09-29
 */
public class VerifyCode {
	private static Random random = new Random();
	private static String ssource = "0123456789";//"ABCDEFGHIJKLMNOPQRSTUVWXYZ"  + "abcdefghijklmnopqrstuvwxyz" +
	private static char[] chars = ssource.toCharArray();

	public static void main(String[] args) {
		String i= VerifyCode.runVerifyCode(4);
		System.out.println(i);
	}
	
	
	/**
	 * 产生随机字符串
	 * @param length 长度
	 * @return 字符串
	 */
	public static String runVerifyCode(int length) {
		char[] buf = new char[length];
		int rnd;
		for(int i=0; i<length; i++){
			rnd = Math.abs(random.nextInt()) % chars.length;
			
			buf[i] = chars[rnd];
		}
		return new String(buf);
	}
	
	/**
	 * 产生随机字符串
	 * @param length 长度
	 * @return 字符串
	 */
	public static String getVerifyCode(int length) {
		char[] buf = new char[length];
		int rnd;
		for(int i=0; i<length; i++){
			rnd = Math.abs(random.nextInt()) % chars.length;
			
			buf[i] = chars[rnd];
		}
		return new String(buf);
	}
	
	/**
	 * 给定范围获得随机颜色
	 */
	public static Color getRandColor(int fc,int bc)
	{
	   Random random = new Random();
	   if(fc>255) fc=255;
	   if(bc>255) bc=255;
	   int r=fc+random.nextInt(bc-fc);
	   int g=fc+random.nextInt(bc-fc);
	   int b=fc+random.nextInt(bc-fc);
	   return new Color(r,g,b);
   	}
  
	/**
	 * 生成图象
	 * @param sCode 传递验证码 w:图象宽度 h:图象高度
	 */
  	public static BufferedImage CreateImage(String sCode)
  	{
	  	try{	
	  		//字符的字体
			Font CodeFont = new Font("System",Font.BOLD,16);//new Font("Times New Roman",Font.PLAIN,18);//

			int iLength = sCode.length();					//得到验证码长度
			int CharWidth = 14;		//字符距左边宽度
			int width=(CharWidth+1)*iLength +2, height=20;				//图象宽度与高度
			int CharHeight = 16;    						//字符距上边高度
			
			// 在内存中创建图象
			BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
			
			// 获取图形上下文
			Graphics g = image.getGraphics();
			
			//生成随机类
			Random random = new Random();
			
			// 设定背景色
			g.setColor(getRandColor(200,240));
			g.fillRect(0, 0, width, height);
			
			//设定字体
			g.setFont(CodeFont);
			
			// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
			g.setColor(getRandColor(160,200));
			for (int i=0;i<200;i++)
			{
				  int x = random.nextInt(width);
				  int y = random.nextInt(height);
				  int xl = random.nextInt(12);
				  int yl = random.nextInt(12);
				  g.drawLine(x,y,x+xl,y+yl);
			}
			
			//画随机颜色的边框
			g.setColor(new Color(127,157,185));
			g.drawRect(0,0,width-1,height-1);
	
			for (int i=0;i<iLength;i++)
			{
				String rand = sCode.substring(i,i+1); 
				// 将认证码显示到图象中
				int cr = 20+random.nextInt(60);
				int cg = 20+random.nextInt(60);
				int cb = 20+random.nextInt(60);
				
				g.setColor(new Color(cr,cg,cb));
				g.drawString(rand,CharWidth*i+8,CharHeight);
			}
			
			// 图象生效
			g.dispose();
			return image;
		}catch(Exception e){
			//e.printStackTrace();	
			//System.out.println(e.getMessage());
			}
		return null;
	}
	
}
