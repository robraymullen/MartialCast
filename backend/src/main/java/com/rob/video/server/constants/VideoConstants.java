package com.rob.video.server.constants;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class VideoConstants {
	
	private static String VIDEO_STORE;
	
	private String VIDEO_PREVIEW_STORE;
	
	private String getProperty(String propertyName) throws IOException {
		InputStream inputStream = null;
		String property = "";
		try {
			Properties prop = new Properties();
			String propFileName = "application.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			property = prop.getProperty(propertyName);
 
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return property;
	}
	
	public String getVideoStore() {
		 try {
			 VIDEO_STORE =  getProperty("video.file.store");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return VIDEO_STORE;
	}
	
	public String getVideoPreviewStore() {
		try {
			VIDEO_PREVIEW_STORE = getProperty("video.file.preview.store");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return VIDEO_PREVIEW_STORE;
	}
	
	public String getVideoPath() {
		String path = "";
		try {
			path = getProperty("video.file.path");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	public String getVideoPreviewPath() {
		String path = "";
		try {
			path = getProperty("video.file.preview.path");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}
