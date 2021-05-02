package app.server.file;

import java.io.IOException;
import java.util.Properties;

/**
 * 获取文件服务器地址的工具类
 * @author lumnca
 *
 */
public class FileServerAddr {

	private static String fileserver="";
	
	static{
		Properties p = new Properties();
		try {
			p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("fileserver.properties"));
			fileserver = p.getProperty("fileserver");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getFileserver() {
		return fileserver;
	}

	public static void setFileserver(String fileserver) {
		FileServerAddr.fileserver = fileserver;
	}
}
