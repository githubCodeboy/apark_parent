package com.apark.common.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.processing.OperationManager;
import com.qiniu.processing.OperationStatus;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;

;


@Data
public class UploadImgUtils {

	private static final Logger logger = LoggerFactory.getLogger(UploadImgUtils.class);
	
	// 设置好账号的ACCESS_KEY和SECRET_KEY
	private static String ACCESS_KEY;
	private static String SECRET_KEY;
	// 要上传的空间
	private static String bucketname_admin;
	private static String bucketname_ios;
	private static String bucketname_android;

	public static String file_path ;

	public  static String qiniuPath ;
	
	
	// 密钥配置
	static Auth auth;
	static Zone zone;
	static Configuration config;
	// 创建上传对象
	static UploadManager uploadManager;
	
	static {
			ACCESS_KEY = CommPropertiesUtils.getProperty("ACCESS_KEY");
			SECRET_KEY = CommPropertiesUtils.getProperty("SECRET_KEY");
			bucketname_admin = CommPropertiesUtils.getProperty("bucketname_admin");
			bucketname_ios = CommPropertiesUtils.getProperty("bucketname_ios");
			bucketname_android = CommPropertiesUtils.getProperty("bucketname_android");
		    file_path = CommPropertiesUtils.getProperty("file_path");

			qiniuPath = CommPropertiesUtils.getProperty("QINIU_PATH");

			if(StringUtils.isEmpty(bucketname_admin)){
				logger.warn("bucketname_admin is null");
			}
			if(StringUtils.isEmpty(bucketname_ios)){
				logger.warn("bucketname_ios is null");
			}
			if(StringUtils.isEmpty(bucketname_android)){
				logger.warn("bucketname_android is null");
			}
			
			auth = Auth.create(ACCESS_KEY, SECRET_KEY);
			zone = Zone.huanan();
			config = new Configuration(zone);
			uploadManager = new UploadManager(config);
	}
	
	public static String getUpTokenByName(String name){
		if("ios".equals(name)){
			return auth.uploadToken(bucketname_ios);
		} else if("android".equals(name)){
			return auth.uploadToken(bucketname_android);
		} else {
			return auth.uploadToken(bucketname_admin);
		}
	}

	// 简单上传，使用默认策略，只需要设置上传的空间名就可以了
	public static String getUpToken() {
		return auth.uploadToken(bucketname_admin);
	}

	/**
	 * 上传文件
	 * @param filePath
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static Response upload(String filePath, String fileName)
			throws IOException {
		// 调用put方法上传
		return uploadManager.put(filePath, fileName, getUpToken());
	}
	/**
	 * 上传二进文件
	 * @param bytes
	 * @return
	 * @throws IOException
	 */
	public static Response upload(byte[] bytes,String fileName)
			throws IOException {

		// 调用put方法上传
		return uploadManager.put(bytes, fileName, getUpToken());
	}


	/**
	 * 修改图片
	 * 
	 * @param FilePath
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String update(String FilePath, String fileName)
			throws IOException {
		// 调用put方法上传
		Response res = uploadManager.put(FilePath, fileName, getUpToken(fileName));
		return res.bodyString();
	}
	/**
	 * 图片转码上传
	 *
	 * @param
	 * @param
	 * @return
	 * @throws IOException
	 */
	public static Response updateChangeCode(byte[]  bytes, String fileName, String token)
			throws IOException {

		// 调用put方法上传
		return   uploadManager.put(bytes, fileName, token);


	}

	public static void main(String[] args) throws IOException {
		/*String s=update("D:\\Koala.jpg","test.jpg");*/
		/*System.out.println(s);*/
		System.out.println(UploadImgUtils.getUpTokenByName("ios"));
	}
	
	// 覆盖上传
	public static String getUpToken(String fileName) {
		// <bucket>:<key>，表示只允许用户上传指定key的文件。在这种格式下文件默认允许“修改”，已存在同名资源则会被本次覆盖。
		// 如果希望只能上传指定key的文件，并且不允许修改，那么可以将下面的 insertOnly 属性值设为 1。
		// 第三个参数是token的过期时间
		return auth.uploadToken(bucketname_admin, fileName, 3600, null);
	}



	// 转码上传token
	public static String getUpTokenForChangeCode(String key   ,String savefileName ) throws Exception{
		// <bucket>:<key>，表示只允许用户上传指定key的文件。在这种格式下文件默认允许“修改”，已存在同名资源则会被本次覆盖。
		// 如果希望只能上传指定key的文件，并且不允许修改，那么可以将下面的 insertOnly 属性值设为 1。
		// 第三个参数是token的过期时间

		//String fops="avthumb/mp3/ab/320k/ar/44100/acodec/libmp3lame|saveas/" +bucketname_admin+":" + savefileName ;
		//String tt = bucketname_admin+":" + savefileName;
		String saveAs  = UrlSafeBase64.encodeToString(bucketname_admin+":" + savefileName);

		String fops="avthumb/mp3/ab/192k/ar/44100/acodec/libmp3lame|saveas/" + saveAs ;
		StringMap policy =new StringMap();
		policy.put("persistentOps" , fops);

		//后期换成我们自己的 账号处理队列 h5_wx_voice
		policy.put("persistentPipeline" , "h5_wx_voice");
		return auth.uploadToken(bucketname_admin, key, 3000, policy);
	}


	public  static void uploadVoice(String filename , String  savefileName)  throws Exception{

		/*String accessKey = "*****";
		String secretKey = "***";

		//待处理文件所在空间
		String bucket = "*****";
		//待处理文件名
		String key = "***";

		Auth auth  = Auth.create(accessKey, secretKey);*/
		//待处理文件所在空间
		String bucket =  bucketname_admin;


		//数据处理指令，支持多个指令
		//String saveMp4Entry = String.format("%s:" + savefileName , bucket);
		String saveAs  = UrlSafeBase64.encodeToString(bucketname_admin+":" + savefileName);
		String avthumbMp4Fop = String.format("avthumb/mp3/ab/160k/ar/44100/acodec/libmp3lame|saveas/%s", saveAs);


		//数据处理队列名称，必须
		String persistentPipeline = "";
		//数据处理完成结果通知地址
		//String persistentNotifyUrl = "http://echo.qiniuts.com/sm977ssm";

		//构造一个带指定Zone对象的配置类
		Configuration cfg = new Configuration(Zone.huanan());
		//...其他参数参考类注释

		//构建持久化数据处理对象
		OperationManager operationManager = new OperationManager(auth, cfg);
		try {

			String persistentId = operationManager.pfop(bucket, filename, avthumbMp4Fop, persistentPipeline, true);
			//可以根据该 persistentId 查询任务处理进度
			System.out.println(persistentId);

			OperationStatus operationStatus = operationManager.prefop(persistentId);
			//解析 operationStatus 的结果
		} catch (QiniuException e) {
			System.err.println(e.response.toString());
		}
	}
}
