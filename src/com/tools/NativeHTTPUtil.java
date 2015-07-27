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
	 * ����HTTP_POST����
	 * 
	 * @see �����͵�<code>sendData</code>�к�������,�ǵð���˫��Լ�����ַ���������
	 *      <code>URLEncoder.encode(string,encodeCharset)</code>
	 * @see ������Ĭ�ϵ����ӺͶ�ȡ��ʱ��Ϊ30��
	 * @param reqURL
	 *            �����ַ
	 * @param sendData
	 *            ���͵�Զ����������������
	 * @return HTTP��Ӧ��`Զ��������Ӧ����,��<code>"200`SUCCESS"</code><br>
	 *         ��ͨ�Ź����з����쳣�򷵻�"HTTP��Ӧ��`Failed",��<code>"500`Failed"</code>
	 */
	public static String sendPostRequest(String reqURL, String sendData) {
		HttpURLConnection httpURLConnection = null;
		OutputStream out = null; // д
		InputStream in = null; // ��
		int responseCode = 0; // Զ��������Ӧ��HTTP״̬��
		try {
			URL sendUrl = new URL(reqURL);
			httpURLConnection = (HttpURLConnection) sendUrl.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setDoOutput(true); // ָʾӦ�ó���Ҫ������д��URL����,��ֵĬ��Ϊfalse
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setConnectTimeout(30000); // 30�����ӳ�ʱ
			httpURLConnection.setReadTimeout(30000); // 30���ȡ��ʱ

			out = httpURLConnection.getOutputStream();
			out.write(sendData.toString().getBytes());
			out.flush(); // ��ջ�����,��������

			// ʹ��httpURLConnection.getResponseMessage()���Ի�ȡ��[HTTP/1.0 200
			// OK]�е�[OK]
			responseCode = httpURLConnection.getResponseCode();

			//// �����ؽ��
			// BufferedReader br = new BufferedReader(new
			//// InputStreamReader(httpURLConnection.getInputStream()));
			// String row = null;
			// String respData = "";
			// if((row=br.readLine()) != null){
			//// //readLine()�����ڶ�������[\n]��س�[\r]ʱ,����Ϊ��������ֹ
			// respData = row; //HTTPЭ��POST��ʽ�����һ������Ϊ��������
			// }
			// br.close();
			in = httpURLConnection.getInputStream();
			byte[] byteDatas = new byte[in.available()];
			in.read(byteDatas);
			String s = new String(byteDatas, "UTF-8");
			return responseCode + "`" + new String(s);

		} catch (Exception e) {
			System.out.println("��[" + reqURL + "]ͨ���쳣,��ջ��ϢΪ");
			e.printStackTrace();
			return responseCode + "`" + "Failed`";
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
					System.out.println("�ر������ʱ�����쳣,��ջ��Ϣ����");
					e.printStackTrace();
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					System.out.println("�ر�������ʱ�����쳣,��ջ��Ϣ����");
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
	 * ����HTTP_POST����
	 * 
	 * @see �����͵�<code>params</code>�к�������,�ǵð���˫��Լ�����ַ���������
	 *      <code>URLEncoder.encode(string,encodeCharset)</code>
	 * @see ������Ĭ�ϵ����ӺͶ�ȡ��ʱʱ���Ϊ30��
	 * @param reqURL
	 *            �����ַ
	 * @param params
	 *            ���͵�Զ����������������
	 * @return HTTP��Ӧ��`Զ��������Ӧ����,��<code>"200`SUCCESS"</code><br>
	 *         ��ͨ�Ź����з����쳣�򷵻�"HTTP��Ӧ��`Failed",��<code>"500`Failed"</code>
	 */
	public static String sendPostRequest(String reqURL, Map<String, String> params) {
		StringBuilder sendData = new StringBuilder();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			sendData.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		if (sendData.length() > 0) {
			sendData.setLength(sendData.length() - 1); // ɾ�����һ��&����
		}
		return sendPostRequest(reqURL, sendData.toString());
	}

	/***
	 * 
	 * @param url
	 *            get�������������·����ַ
	 * @return String ����õ���inputStreamת��Ϊ�ַ���
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
			System.out.println("��[" + url + "]ͨ���쳣,��ջ��ϢΪ");
			e.printStackTrace();
			return responseCode + "`" + "Failed`";
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					System.out.println("�ر�������ʱ�����쳣,��ջ��Ϣ����");
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
	 * ����ʹ�÷���
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