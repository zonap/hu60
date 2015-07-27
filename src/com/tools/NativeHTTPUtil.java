package com.tools;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class NativeHTTPUtil {

	/**
	 * 发送HTTP_POST请求
	 * 
	 * @see 若发送的<code>sendData</code>中含有中文,记得按照双方约定的字符集将中文
	 *      <code>URLEncoder.encode(string,encodeCharset)</code>
	 * @see 本方法默认的连接和读取超时均为30秒
	 * @param reqURL
	 *            请求地址
	 * @param sendData
	 *            发送到远程主机的正文数据
	 * @return HTTP响应码`远程主机响应正文,如<code>"200`SUCCESS"</code><br>
	 *         若通信过程中发生异常则返回"HTTP响应码`Failed",如<code>"500`Failed"</code>
	 */
	public static String sendPostRequest(String reqURL, String sendData) {
		HttpURLConnection httpURLConnection = null;
		OutputStream out = null; // 写
		InputStream in = null; // 读
		int responseCode = 0; // 远程主机响应的HTTP状态码
		try {
			URL sendUrl = new URL(reqURL);
			httpURLConnection = (HttpURLConnection) sendUrl.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setDoOutput(true); // 指示应用程序要将数据写入URL连接,其值默认为false
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setConnectTimeout(30000); // 30秒连接超时
			httpURLConnection.setReadTimeout(30000); // 30秒读取超时

			out = httpURLConnection.getOutputStream();
			out.write(sendData.toString().getBytes());
			out.flush(); // 清空缓冲区,发送数据

			// 使用httpURLConnection.getResponseMessage()可以获取到[HTTP/1.0 200
			// OK]中的[OK]
			responseCode = httpURLConnection.getResponseCode();

			//// 处理返回结果
			// BufferedReader br = new BufferedReader(new
			//// InputStreamReader(httpURLConnection.getInputStream()));
			// String row = null;
			// String respData = "";
			// if((row=br.readLine()) != null){
			//// //readLine()方法在读到换行[\n]或回车[\r]时,即认为该行已终止
			// respData = row; //HTTP协议POST方式的最后一行数据为正文数据
			// }
			// br.close();
			in = httpURLConnection.getInputStream();
			byte[] byteDatas = new byte[in.available()];
			in.read(byteDatas);
			String s = new String(byteDatas, "UTF-8");
			return responseCode + "`" + new String(s);

		} catch (Exception e) {
			System.out.println("与[" + reqURL + "]通信异常,堆栈信息为");
			e.printStackTrace();
			return responseCode + "`" + "Failed`";
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
					System.out.println("关闭输出流时发生异常,堆栈信息如下");
					e.printStackTrace();
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					System.out.println("关闭输入流时发生异常,堆栈信息如下");
					e.printStackTrace();
				}
			}
			if (httpURLConnection != null) {
				httpURLConnection.disconnect();
				httpURLConnection = null;
			}
		}
	}

	/**
	 * 发送HTTP_POST请求
	 * 
	 * @see 若发送的<code>params</code>中含有中文,记得按照双方约定的字符集将中文
	 *      <code>URLEncoder.encode(string,encodeCharset)</code>
	 * @see 本方法默认的连接和读取超时时间均为30秒
	 * @param reqURL
	 *            请求地址
	 * @param params
	 *            发送到远程主机的正文数据
	 * @return HTTP响应码`远程主机响应正文,如<code>"200`SUCCESS"</code><br>
	 *         若通信过程中发生异常则返回"HTTP响应码`Failed",如<code>"500`Failed"</code>
	 */
	public static String sendPostRequest(String reqURL, Map<String, String> params) {
		StringBuilder sendData = new StringBuilder();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			sendData.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		if (sendData.length() > 0) {
			sendData.setLength(sendData.length() - 1); // 删除最后一个&符号
		}
		return sendPostRequest(reqURL, sendData.toString());
	}

	/***
	 * 
	 * @param url
	 *            get请求的完整网络路径地址
	 * @return String 请求得到的inputStream转化为字符串
	 */
	public static String sendGetRequest(String url) {
		HttpURLConnection httpURLConnection = null;
		InputStream in = null;
		int responseCode = 0;
		try {
			URL sendURL = new URL(url);
			httpURLConnection = (HttpURLConnection) sendURL.openConnection();
			httpURLConnection.setRequestMethod("GET");
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setConnectTimeout(30000);
			httpURLConnection.setReadTimeout(30000);

			httpURLConnection.connect();
			responseCode = httpURLConnection.getResponseCode();
			in = httpURLConnection.getInputStream();
			byte[] byteDatas = new byte[in.available()];
			in.read(byteDatas);
			String s = new String(byteDatas, "UTF-8");
			return responseCode + "`" + new String(s);

		} catch (Exception e) {
			System.out.println("与[" + url + "]通信异常,堆栈信息为");
			e.printStackTrace();
			return responseCode + "`" + "Failed`";
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					System.out.println("关闭输入流时发生异常,堆栈信息如下");
					e.printStackTrace();
				}
			}
			if (httpURLConnection != null) {
				httpURLConnection.disconnect();
				httpURLConnection = null;
			}
		}

	}
	/**
	 * 方法使用范例
	 */
	// public static void main(String[] args) throws
	// UnsupportedEncodingException {
	// Map<String, String> params = new HashMap<String, String>();
	// tzid=74737&bkid=27&sid=NXYFGMuXMIvFQQf6khheu6ciMAAA&nr=eeee&go=%E5%BF%AB%E9%80%9F%E5%9B%9E%E5%A4%8D
	// params.put("tzid", "74737");
	// params.put("bkid", "27");
	// params.put("sid", "NXYFGMuXMIvFQQf6khheu6ciMAAA");
	// params.put("nr", "eeee");
	// params.put("go", "%E5%BF%AB%E9%80%9F%E5%9B%9E%E5%A4%8D");
	// System.out.println(sendPostRequest("http://133.130.53.62/wap/read.php?id=bbs_xiehf",
	// params));
	// }
	// public static void main(String args[]){
	// String
	// url="http://133.130.53.62/wap/0wap/m.php/api.chat.json?type=chat&name=%E5%85%AC%E5%85%B1%E8%81%8A%E5%A4%A9%E5%AE%A4";
	// System.out.println(sendGetRequest(url));
	// }
}