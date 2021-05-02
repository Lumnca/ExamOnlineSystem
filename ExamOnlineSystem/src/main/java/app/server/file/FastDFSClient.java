package app.server.file;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.IOException;
import java.net.URLDecoder;

/**
 * FastDFS的工具类
 * 
 * @author zw
 *
 */
public class FastDFSClient {
	
	private TrackerClient trackerClient = null;
	private TrackerServer trackerServer = null;
	private StorageServer storageServer = null;
	private StorageClient1 storageClient = null;

	/**
	 * 调用本类时，需要在resources中增加fdfs_client.properties配置文件
	 * 
	 * @throws Exception
	 */
	public FastDFSClient() throws Exception {
		String conf = this.getClass().getResource("/fdfs_client.properties").getPath();
		if (conf.contains("classpath:")) {
			String path = URLDecoder.decode(getClass().getProtectionDomain().getCodeSource().getLocation().toString(),
					"UTF-8");
			path = path.substring(6);
			conf = conf.replace("classpath:", URLDecoder.decode(path, "UTF-8"));
		}
		ClientGlobal.init(conf);
		trackerClient = new TrackerClient();
		trackerServer = trackerClient.getConnection();
		storageServer = null;
		storageClient = new StorageClient1(trackerServer, storageServer);
	}

	/**
	 * 上传文件方法
	 * <p>
	 * Title: uploadFile
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param fileName
	 *            文件全路径
	 * @param extName
	 *            文件扩展名，不包含（.）
	 * @param metas
	 *            文件扩展信息
	 * @return
	 * @throws Exception
	 */
	public String uploadFile(String fileName, String extName, NameValuePair[] metas) {
		String result = null;
		try {
			result = storageClient.upload_file1(fileName, extName, metas);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MyException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 上传文件,传fileName
	 * 
	 * @param fileName
	 *            文件的磁盘路径名称 如：D:/image/aaa.jpg
	 * @return null为失败
	 */
	public String uploadFile(String fileName) {
		return uploadFile(fileName, null, null);
	}

	/**
	 *
	 * @param fileName
	 *            文件的磁盘路径名称 如：D:/image/aaa.jpg
	 * @param extName
	 *            文件的扩展名 如 txt jpg等
	 * @return null为失败
	 */
	public String uploadFile(String fileName, String extName) {
		return uploadFile(fileName, extName, null);
	}

	/**
	 * 上传文件方法
	 * <p>
	 * Title: uploadFile
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param fileContent
	 *            文件的内容，字节数组
	 * @param extName
	 *            文件扩展名
	 * @param metas
	 *            文件扩展信息
	 * @return
	 * @throws Exception
	 */
	public String uploadFile(byte[] fileContent, String extName, NameValuePair[] metas) {
		String result = null;
		try {
			result = storageClient.upload_file1(fileContent, extName, metas);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MyException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 上传文件
	 * 
	 * @param fileContent
	 *            文件的字节数组
	 * @return null为失败
	 * @throws Exception
	 */
	public String uploadFile(byte[] fileContent) throws Exception {
		return uploadFile(fileContent, null, null);
	}

	/**
	 * 上传文件
	 * 
	 * @param fileContent
	 *            文件的字节数组
	 * @param extName
	 *            文件的扩展名 如 txt jpg png 等
	 * @return null为失败
	 */
	public String uploadFile(byte[] fileContent, String extName) {
		return uploadFile(fileContent, extName, null);
	}
}
