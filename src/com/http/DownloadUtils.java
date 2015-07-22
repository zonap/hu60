package com.http;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *  http���ظ�����
 * @author HuangChao
 *
 */
public abstract class DownloadUtils
{
    public static final int BUFFER_SIZE = 1024;
    
   
    
    /**
     * ���ط���
     * @param srcUrl ��Դurl
     * @param filePath �ļ�����ڱ��صľ���·���Լ��ļ���

     * @throws IOException [����˵��]
     * 
     * @return void [��������˵��]
     * @exception throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
     */
    public static void download(String srcUrl, String filePath)
            throws IOException
    {
        long startPos = 0; //��ʼ����λ��

        //ת���ո�
        srcUrl = srcUrl.replace(" ", "%20");
        
        byte[] buf = new byte[BUFFER_SIZE];
        int size = -1;
        
        //���Զ����Դ�ͱ�����Դһ��������

        isFinished(srcUrl, filePath);
        
        //        if (isFinished(srcUrl, filePath))
        //        {
        //            //System.out.println("finished.");
        //            return;
        //        }
        
        BufferedInputStream bis = null;
        RandomAccessFile raf = null;
        HttpURLConnection httpUrl = null;
        
        try
        {
            //��������
            URL url = new URL(srcUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            
            //����URL��ȡ�ļ�·��
            File fileName = new File(filePath);
            startPos = fileName.length();
            
            if (fileName.exists())
            {
                //�ж��Ƿ���Ҫ����

                
                if (isContinue(srcUrl, filePath))
                {
                    //��ȡ��ȡ�ļ������ز��ֳ���

                    
                    startPos = fileName.length();
                    //��������ͷ��Ϣ

                    
                    httpUrl.setRequestProperty("RANGE", "bytes=" + startPos
                            + "-");
                    
                    //System.out.println("isContinue");
                }
            }
            
            //����ָ������Դ

            
            httpUrl.connect();
            
            //��ȡ����������

            
            bis = new BufferedInputStream(httpUrl.getInputStream());
            //�����ļ�
            raf = new RandomAccessFile(fileName, "rw");
            raf.seek(startPos);
            
            //�����ļ�
            while ((size = bis.read(buf)) != -1)
            {
                raf.write(buf, 0, size);
            }
            
        }
        finally
        {
            if (null != bis)
            {
                bis.close();
            }
            if (null != raf)
            {
                raf.close();
            }
            if (null != httpUrl)
            {
                httpUrl.disconnect();
            }
        }
    }
    
    /**
     * �ж��Ƿ��Ѿ����ع���
     * @param srcUrl
     * @param fileName
     * @return
     * @throws IOException [����˵��]
     */
    private static boolean isFinished(String srcUrl, String fileName)
            throws IOException
    {
        
        //��������
//        URL url = new URL(srcUrl);
        File file = new File(fileName);
        if (!file.exists())
        {
            return false;
        }
        else
        {
        	file.delete();
        	return true;
        }
        
//        HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
//        if (httpUrl.getContentLength() == file.length()
//                && (file.lastModified() / 1000 >= httpUrl.getLastModified() / 1000))
//        {
//            httpUrl.disconnect();
//            file.delete();
//            return true;
//        }
//        
//        httpUrl.disconnect();
    }
    
    /**
     * �ж��Ƿ���Ҫ��Ҫ�ϵ�����

     * @param srcUrl
     * @param fileName
     * @return
     * @throws IOException [����˵��]
     */
    private static boolean isContinue(String srcUrl, String fileName)
            throws IOException
    {
        URL url = new URL(srcUrl);
        File file = new File(fileName);
        
        HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
        if (file.exists())
        {
            if ((file.lastModified() / 1000 >= httpUrl.getLastModified() / 1000))
            {
                httpUrl.disconnect();
                return true;
            }
            else
            {
                file.delete();
            }
        }
        
        httpUrl.disconnect();
        return false;
    }
    
    public static void main(String[] args) throws IOException
    {
        String url = "http://dlsw.baidu.com/sw-search-sp/soft/78/15699/putty_V0.63.0.0.43510830.exe";
        String path = "d:\\rma0_a_b.exe";
        
        DownloadUtils.download(url, path);
    }
}
