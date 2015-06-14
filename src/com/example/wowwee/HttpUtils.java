package com.example.wowwee;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {

//	private final static String URL_PATH="http://192.168.10.18:80/Jpeg/CamImg1234.jpg";
	
	public HttpUtils() {
		// TODO Auto-generated constructor stub
	}
	
	//整段代码都是在尝试获取图像，以下是以字节返回的一种方式
	
	
	
	/**
	 * 从网络中获取图像 以字节返回
	 * @return
	 */
	//获取URL的数据
	public static byte[] getImage(String path) throws Exception{
			URL url =new URL(path);			
			HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod("GET");
			httpURLConnection.setConnectTimeout(6*1000);
			
			httpURLConnection.setDoInput(true);
			InputStream in =null; 
			byte[] b =new byte[1024];
			int len = -1;
			if(httpURLConnection.getResponseCode() == 200){
				in= httpURLConnection.getInputStream();
				byte[] result = readStream(in);
				in.close();
				return result;
				
			}
		return null;
	}
	//输出URL的数据
	private static byte[] readStream(InputStream in) throws Exception{
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = -1;
			while((len = in.read(buffer)) != -1){
				outputStream.write(buffer, 0, len);
			}
			outputStream.close();
			in.close();
			return outputStream.toByteArray();
	}	
}
	