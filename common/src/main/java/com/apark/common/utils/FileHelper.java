package com.apark.common.utils;

import java.io.File;

public class FileHelper {

	public static boolean createDir(String dirName){
		if(!dirName.endsWith(File.separator)){
			dirName = dirName + File.separator;
		}
		
		File file = new File(dirName);
		if(file.exists()){
			return true;
		}
		return file.mkdirs();
	}

	/**
	 * 删除本地文件
	 * @param filePath
	 * @return
	 */
	public static boolean deleteLocalFile(String filePath){
		File file = new File(filePath);
		if(file.exists()){
			return file.delete();
		}
		return true;
	}
}
