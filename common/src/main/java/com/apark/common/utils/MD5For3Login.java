package com.apark.common.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Slf4j
@Component("Md5")
@Scope("singleton")
@Configuration
@PropertySource("classpath:common.properties")
@Data
public class MD5For3Login {


	@Value("${third_login_secretKey}")
	private  String   secretKey;




	/**
	 * MD5方法
	 *
	 * @param text 明文
	 * @param key 密钥
	 * @return 密文
	 * @throws Exception
	 */
	public  String md5(String text, String key ) throws Exception {

		//加密后的字符串
		String encodeStr= DigestUtils.md5Hex(text + key);
		//System.out.println("MD5加密后的字符串为:encodeStr="+encodeStr);
		return encodeStr;
	}

	/**
	 * MD5验证方法
	 *
	 * @param text 明文
	 * @param key 密钥
	 * @param md5 密文
	 * @return true/false
	 * @throws Exception
	 */
	public  boolean verify(String text, String key, String md5) throws Exception {
		//根据传入的密钥进行验证
		String md5Text = this.md5(text, key);
		if(md5Text.equalsIgnoreCase(md5))
		{
			//System.out.println("MD5验证通过");
			return true;
		}

		return false;
	}

	public static void main(String[] args) {
		try {
			String  str =  new MD5For3Login().md5(  "13877778888" ,"n5IkyErvsG0eUhvCQfxtgDQ2IMWeIKSRUm0sVgFpzKmiu8vur43H6OFn3Y1j7mF6d57TonC0cVZOpsLIN9EB4RGa1oUqsF8M");
			System.out.printf(str  );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
