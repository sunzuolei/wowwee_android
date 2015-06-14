package com.example.wowwee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends Activity {

	HttpClient client;
	EditText ip;
	EditText post;
	ImageView imageView;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        client = new DefaultHttpClient();
        ip=(EditText) findViewById( R.id.ip);
        post=(EditText) findViewById( R.id.post);
        imageView = (ImageView)this.findViewById(R.id.imageView1);
        //前进
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
			
			
//			public boolean onTouch(View arg0) {
//				readNet("http://192.168.10.18:80/rev.cgi?Cmd=nav&action=18&drive=1&speed=5");
//			//"http://"+ip.getText().toString()+":"+post.getText().toString()+"
//				return false;
//			}

			
        public void onClick(View arg0) {
        	//根据规约传送URL地址
        	readNet("http://192.168.10.18:80/rev.cgi?Cmd=nav&action=18&drive=1&speed=5");
        	}
			
        });
        //后退
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
			
			@Override
		public void onClick(View arg0) {
				readNet("http://192.168.10.18:80/rev.cgi?Cmd=nav&action=18&drive=3&speed=5");
			}
			
        });
        //左前进
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
	
        	@Override
		public void onClick(View arg0) {
			readNet("http://192.168.10.18:80/rev.cgi?Cmd=nav&action=18&drive=4&speed=5");
		}
	
});
        //右前进
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
	
        	@Override
		public void onClick(View arg0) {
		readNet("http://192.168.10.18:80/rev.cgi?Cmd=nav&action=18&drive=2&speed=5");
		}
		
        });
        //摇臂起
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
	
        	@Override
        public void onClick(View arg0) {
        		readNet("http://192.168.10.18:80/rev.cgi?Cmd=nav&action=18&drive=11&speed=5");
        	}
	
        });
        //摇臂中等位置
        findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
        	
        	@Override
        public void onClick(View arg0) {
        		readNet("http://192.168.10.18:80/rev.cgi?Cmd=nav&action=18&drive=13&speed=5");
        	}
        	
        });
        //摇臂放下
        findViewById(R.id.button7).setOnClickListener(new View.OnClickListener() {
        	
        	@Override
        public void onClick(View arg0) {
        		readNet("http://192.168.10.18:80/rev.cgi?Cmd=nav&action=18&drive=12&speed=5");
        	}
        	
        });
        //图像获取（未成功）
        findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
        	
        	@Override
        public void onClick(View arg0) {
        	try{   			       	
        		byte[] data = HttpUtils.getImage("http://192.168.10.18:80/Jpeg/CamImg1234.jpg");
        		String d = new String(data);
//        		File file =new File("1.jpg");
//        		FileOutputStream outStream = new FileOutputStream(file);
//      		outStream.write(data);
//      		outStream.close();
        		int length = data.length;
        		Bitmap bitMap = BitmapFactory.decodeByteArray(data, 0, length);
        		imageView.setImageBitmap(bitMap);
        		} catch (Exception e){
        		
        		}
//        		byte[] data = HttpUtils.getImageViewArray();
//        		Bitmap bitmap =BitmapFactory.decodeByteArray(data, 0, data.length);
//        		imageView.setImageBitmap(bitmap);
//        		
//        		
        		}
//        	
        		});
//				VideoView videoView=(VideoView) findViewById(R.id.videoView1);
//				videoView.setVideoURI(Uri.parse("http://192.168.10.18/Jpeg/CamImg0001.jpg"));
//				MediaController mediaController =new MediaController(this);
//				videoView.setMediaController(mediaController);
//				videoView.start();
//				"http://192.168.10.18:80/Jpeg/CamImg0001.jpg"

}
   	
    //HTTP通信
	public void readNet(String url){
    	new AsyncTask<String,Void,Void>(){
			@Override
			protected Void doInBackground(String... arg0) {
				String urlString = arg0[0];
				HttpGet get = new HttpGet(urlString);				
				try {
					HttpResponse response =client.execute(get);
					String value = EntityUtils.toString(response.getEntity());					
					System.out.println(value);					
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}    		
    	}.execute(url);
    }
    
    
}