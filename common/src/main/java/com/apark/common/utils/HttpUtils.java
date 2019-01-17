package com.apark.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.Map;


public class HttpUtils {  
	
	
	
	
	 // 请求超时时间
     public static final int SEND_REQUEST_TIME_OUT = 20000;

     // 将读超时时间
     public static final int READ_TIME_OUT = 20000;
	 
     // HTTP内容类型。如果未指定ContentType，默认为TEXT/HTML
     public static final String CONTENT_TYPE_TEXT_HTML = "text/xml";
     
     // get请求
     public static final String HTTP_GET = "GET";
     
     // HTTP内容类型。相当于form表单的形式，提交暑假
     public static final String CONTENT_TYPE_FORM_URL = "application/x-www-form-urlencoded";
    /** 
     * 发送GET请求 
     *  
     * @param url 
     *            目的地址 
     * @param parameters 
     *            请求参数，Map类型。 
     * @return 远程响应结果 
     */  
    public static String sendGet(String url, Map<String, String> parameters) { 
        StringBuffer result= new StringBuffer("") ;
        BufferedReader in = null;// 读取响应输入流  
        StringBuffer sb = new StringBuffer();// 存储参数  
        String params = "";// 编码之后的参数
        
      
        try {
            // 编码请求参数  
            if( parameters.size()==1){
                for(String name:parameters.keySet()){
                    sb.append(name).append("=").append(
                            java.net.URLEncoder.encode(parameters.get(name),  
                            "UTF-8"));
                }
                params=sb.toString();
            }else{
                for (String name : parameters.keySet()) { 
                	System.out.println(name +"="+ parameters.get(name));
                    sb.append(name).append("=").append(  
                            java.net.URLEncoder.encode(parameters.get(name),  
                                    "UTF-8")).append("&");  
                }  
                String temp_params = sb.toString();  
                params = temp_params.substring(0, temp_params.length() - 1);  
            }
            String full_url = url + "?" + params; 
            System.out.println(full_url); 
            // 创建URL对象  
            java.net.URL connURL = new java.net.URL(full_url);  
            // 打开URL连接  
            HttpURLConnection httpConn = (HttpURLConnection) connURL
                    .openConnection();  
            // 设置通用属性  
            httpConn.setRequestProperty("Accept", "*/*");  
            httpConn.setRequestProperty("Connection", "Keep-Alive");  
            httpConn.setRequestProperty("User-Agent",  
                    "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)"); 
            // 设置一个指定的超时值（以毫秒为单位）
            httpConn.setConnectTimeout(SEND_REQUEST_TIME_OUT);
            // 将读超时设置为指定的超时，以毫秒为单位。
            httpConn.setReadTimeout(READ_TIME_OUT);
            // Post 请求不能使用缓存
            httpConn.setUseCaches(false);
            // 设置内容类型
            httpConn.setRequestProperty("Content-Type", CONTENT_TYPE_FORM_URL);
             // 设定请求的方法，默认是GET
            httpConn.setRequestMethod("GET");

            
            // 建立实际的连接  
            httpConn.connect();  
            // 响应头部获取  
			if (httpConn.getResponseCode() >= 300) {
                throw new Exception(
                         "HTTP Request is not success, Response code is " + httpConn.getResponseCode());
            }
   
			if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				// 定义BufferedReader输入流来读取URL的响应,并设置编码方式  
	            in = new BufferedReader(new InputStreamReader(httpConn  
	                    .getInputStream(), "UTF-8"));  
	            String line;  
	            // 读取返回的内容  
	            while ((line = in.readLine()) != null) {  
	                result.append(line) ;  
	            }  
            }

    
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {  
                if (in != null) {  
                    in.close();  
                }  
            } catch (IOException ex) {  
                ex.printStackTrace();  
            }  
        }
        return result.toString() ;
    }  
    
    /** 
     * 返回结果转换成 json
     *  
     * @param args 
     */
   public   static JSONObject sendGetRetJSON(String url, Map<String, String> parameters){
    	
	   	String  result = "{}";
	   	//System.err.println(parameters);
	   	result = sendGet( url,  parameters);
    	JSONObject jsonObject = JSON.parseObject(result);
         
         return jsonObject;  
    }
    
  
    /** 
     * 发送POST请求 
     *  
     * @param url 
     *            目的地址 
     * @param parameters 
     *            请求参数，Map类型。 
     * @return 远程响应结果 
     */  
   
    public static String  sendPost(String url, String parameters) {  
        StringBuffer result= new StringBuffer() ; 
        BufferedReader in = null;// 读取响应输入流  
        OutputStream out = null;  
        StringBuffer sb = new StringBuffer();// 处理请求参数  
        //String params = "";// 编码之后的参数  
        try {  
            // 编码请求参数  
          
            // 创建URL对象  
            java.net.URL connURL = new java.net.URL(url);  
            // 打开URL连接  
            HttpURLConnection httpConn = (HttpURLConnection) connURL
                    .openConnection();  
            // 设置通用属性  
            httpConn.setRequestProperty("Accept", "*/*");  
            httpConn.setRequestProperty("Connection", "Keep-Alive");  
            httpConn.setRequestProperty("User-Agent",  
                    "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            
            // 设置POST方式  
            httpConn.setDoInput(true);  
            httpConn.setDoOutput(true);  
            // 设置一个指定的超时值（以毫秒为单位）
            httpConn.setConnectTimeout(SEND_REQUEST_TIME_OUT);
            // 将读超时设置为指定的超时，以毫秒为单位。
            httpConn.setReadTimeout(READ_TIME_OUT);
            // 设定请求的方法，默认是GET
            httpConn.setRequestMethod("POST");
            // Post 请求不能使用缓存
            //httpConn.setUseCaches(false);
            // 获取HttpURLConnection对象对应的输出流  
            	//out = new PrintWriter(httpConn.getOutputStream());
            out= httpConn.getOutputStream(); 
            // 发送请求参数  
            out.write(parameters.getBytes("UTF-8"));  
            // flush输出流的缓冲  
            out.flush();  
            out.close();
            
            // 响应头部获取  
			if (httpConn.getResponseCode() >= 300) {
                throw new Exception(
                         "HTTP Request is not success, Response code is " + httpConn.getResponseCode());
            }
   
			if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				// 定义BufferedReader输入流来读取URL的响应,并设置编码方式  
	            in = new BufferedReader(new InputStreamReader(httpConn  
	                    .getInputStream(), "UTF-8"));  
	            String line;  
	            // 读取返回的内容  
	            while ((line = in.readLine()) != null) {  
	                result.append(line) ;  
	            }  
            }
			
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (out != null) {  
                    out.close();  
                }  
                if (in != null) {  
                    in.close();  
                }  
            } catch (IOException ex) {  
                ex.printStackTrace();  
            }  
        } 
        
        return  result.toString();
    }  
    
    /** 
     * 返回结果转换成 json
     *  
     * @param args 
     */
   public   static JSONObject sendPostRetJSON(String url, String parameters){
    	
	   	String  result = "{}";
	   	System.err.println(parameters);
	   	result = sendPost( url,  parameters);
    	JSONObject jsonObject = JSON.parseObject(result);
         
         return jsonObject;  
    }
   
   
   /** 
    * 发送GET请求    测试医院接口是否  网络连通
    *  
    * @param url 
    *            目的地址 
    * @param parameters 
    *            请求参数，Map类型。 
    * @return 远程响应结果 
 * @throws MalformedURLException 
 * @throws ProtocolException 
    */
   public static int sendGetTestHospital(String url, Map<String, String> parameters) throws Exception, ProtocolException { 
       int result =  0 ;
       BufferedReader in = null;// 读取响应输入流  
       StringBuffer sb = new StringBuffer();// 存储参数  
       String params = "";// 编码之后的参数
       
     
     
      
          
           String full_url = url  ; 
           System.out.println(full_url); 
           // 创建URL对象  
           java.net.URL connURL = new java.net.URL(full_url);  
           // 打开URL连接  
           HttpURLConnection httpConn = (HttpURLConnection) connURL
                   .openConnection();  
           // 设置通用属性  
           httpConn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");  
           httpConn.setRequestProperty("Connection", "Keep-Alive");  
           httpConn.setRequestProperty("User-Agent",  
                   "Mozilla/5.0  (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3278.0 Safari/537.36"); 
           // 设置一个指定的超时值（以毫秒为单位）
           httpConn.setConnectTimeout(SEND_REQUEST_TIME_OUT);
           // 将读超时设置为指定的超时，以毫秒为单位。
           httpConn.setReadTimeout(READ_TIME_OUT);
           // Post 请求不能使用缓存
           httpConn.setUseCaches(false);
           httpConn.setDoOutput(true);  
           httpConn.setDoInput(true);  
           // 设置内容类型
           httpConn.setRequestProperty("Content-Type", CONTENT_TYPE_FORM_URL);
            // 设定请求的方法，默认是GET
           httpConn.setRequestMethod("POST");

           
           // 建立实际的连接  
           httpConn.connect();  
           // 响应头部获取  
			if (httpConn.getResponseCode() >= 300) {
              
           }
			//获取相应 code
			result =   httpConn.getResponseCode();
			
			/*if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				// 定义BufferedReader输入流来读取URL的响应,并设置编码方式  
	            in = new BufferedReader(new InputStreamReader(httpConn  
	                    .getInputStream(), "UTF-8"));  
	            String line;  
	            // 读取返回的内容  
	            while ((line = in.readLine()) != null) {  
	                result.append(line) ;  
	            }  
           }*/

   
           
     
       
	           if (in != null) {  
	               try {
					in.close();
				} catch (IOException e) {
					
				e.printStackTrace();
			}  
	       }  
           
       
       return result ;
   }  
   
   
  
    /** 
     * 主函数，测试请求 
     *  
     * @param args 
     */  
    public static void main(String[] args) {  
//        Map<String, String> parameters = (Map<String, String>) new HashMap<String, String>();  
//        parameters.put("name", "sarin");  
//        String result =sendGet("http://www.baidu.com", parameters);
//        System.out.println(result); 
    }  
}  