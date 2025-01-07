package cn.qdd.foods.utils;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HttpClient工具类
 * <p>
 * Author: chengen
 * Date: 2014/7/4
 * Time: 11:40
 */
public class HttpClientUtil {
    private static final Logger logger = LoggerFactory.getLogger("httpInvoker");
    private static final NameValuePair[] EMPTY_NAMEVALUE_PAIRS = new NameValuePair[]{};
    private static final String DEFAULT_CHARET = "UTF-8";

    private static MultiThreadedHttpConnectionManager connectionManager;
    private static HttpClient httpClient;

    static {
        connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.getParams().setConnectionTimeout(120000);//30000
        connectionManager.getParams().setSoTimeout(300000);//120000
        connectionManager.getParams().setDefaultMaxConnectionsPerHost(5);
        httpClient = new HttpClient(connectionManager);
    }

    private static HttpClient httpClientLong;
    private static MultiThreadedHttpConnectionManager connectionManagerLong;

    static {
        connectionManagerLong = new MultiThreadedHttpConnectionManager();
        connectionManagerLong.getParams().setConnectionTimeout(120000);//30000
        connectionManagerLong.getParams().setSoTimeout(600000);//600000
        connectionManagerLong.getParams().setDefaultMaxConnectionsPerHost(5);
        httpClientLong = new HttpClient(connectionManagerLong);
    }


    public static String getQuietly(String url) {
        try {
            return get(url);
        } catch (Exception ex) {
            // 捕获异常，但不返回给调用方
        }
        return null;
    }

    public static String get(String url) throws HttpInvokeException {
        return executeMethod(new GetMethod(url), false);
    }

    public static String getLong(String url, Boolean waitLong) throws HttpInvokeException {
        return executeMethod(new GetMethod(url), waitLong);
    }

    public static String get(String url, Map<String, String> parameters) throws HttpInvokeException {
        GetMethod getMethod = new GetMethod(url);
        getMethod.setQueryString(buildNameValuePair(parameters));
        return executeMethod(getMethod, false);
    }

    public static String postQuietly(String url, Map<String, String> parameters) {
        try {
            return post(url, parameters, null, null, null);
        } catch (Exception ex) {
            // ignore exception
        }
        return null;
    }

    public static String postQuietly(String url, Map<String, String> parameters, String contentType, String charset,
                                     String requestBody) {
        try {
            return post(url, parameters, contentType, charset, requestBody);
        } catch (Exception ex) {
            // ignore exception
        }
        return null;
    }

    public static String post(String url, Map<String, String> parameters) throws HttpInvokeException {
        return post(url, parameters, null, null, null);
    }

    public static String post(String url, Map<String, String> parameters, String contentType, String charset,
                              String requestBody) throws HttpInvokeException {
        PostMethod post = new PostMethod(url);
        if (requestBody != null) {
            post.setQueryString(buildNameValuePair(parameters));
            try {
                post.setRequestEntity(new StringRequestEntity(requestBody, contentType, charset == null ? DEFAULT_CHARET : charset));
            } catch (UnsupportedEncodingException ex) {
                logger.error("", ex);
                throw new HttpInvokeException(ex);
            }
        } else {
            post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, DEFAULT_CHARET);
            post.setRequestBody(buildNameValuePair(parameters));
        }
        return executeMethod(post, false);
    }

    /**
     * 文件上传
     *
     * @param url        上传接口url
     * @param file       上传文件
     * @param fileName   上传文件name
     * @param parameters 上传参数
     * @return 上传接口返回结果
     */
    public static String uploadFile(String url, File file, String fileName, Map<String, String> parameters) throws HttpInvokeException {
        PostMethod post = new PostMethod(url);
        Part[] parts = buildFileAndValuePart(file, fileName, parameters);
        MultipartRequestEntity mre = new MultipartRequestEntity(parts, post.getParams());
        post.setRequestEntity(mre);
        return executeMethod(post, false);
    }

    private static String executeMethod(HttpMethod method, Boolean waitLong) throws HttpInvokeException {
        if (method == null) {
            throw new IllegalArgumentException("method is required");
        }

        long startTime = System.currentTimeMillis();
        int statusCode = HttpStatus.SC_OK;
        long elapsedTime = 0;

        try {
            method.setRequestHeader("Connection", "close");
            if (waitLong) {
                //如果waitLong=true,调用等待时间长的http请求配置
                statusCode = httpClientLong.executeMethod(method);
            } else {
                statusCode = httpClient.executeMethod(method);
            }
            logger.info("请求返回码："+statusCode);
            elapsedTime = System.currentTimeMillis() - startTime;

            if (statusCode != HttpStatus.SC_OK) {
                logger.error("调用http请求失败: " + method.getURI() + ",耗时：" + elapsedTime + "ms, 响应码: " + statusCode);
                throw new HttpInvokeException(statusCode, "调用http服务返回响应错误, url: " + method.getURI() + ",响应码："
                        + statusCode);
            } else {
                logger.info("调用http请求成功: " + method.getURI() + ",耗时：" + elapsedTime + "ms, 响应码: " + statusCode);
            }
            return IOUtils.toString(method.getResponseBodyAsStream(), "UTF-8");
        } catch (Exception ex) {
            statusCode = 499;
            try {
                logger.info("调用http请求异常: " + method.getURI() + ",耗时：" + elapsedTime + "ms, exception:"
                        + ex.getMessage());
            } catch (URIException uriex) {
                // ignore this exception
            }
            if (ex instanceof HttpInvokeException) {
                throw (HttpInvokeException) ex;
            } else {
                throw new HttpInvokeException(statusCode, ex);
            }
        } finally {
            method.releaseConnection();
        }
    }

    private static NameValuePair[] buildNameValuePair(Map<String, String> parameters) {
        if (parameters == null || parameters.isEmpty()) {
            return EMPTY_NAMEVALUE_PAIRS;
        }
        NameValuePair[] nameValuePairs = new NameValuePair[parameters.size()];

        List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>(parameters.size());
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            nameValuePairList.add(new NameValuePair(entry.getKey(), entry.getValue()));
        }
        nameValuePairList.toArray(nameValuePairs);
        return nameValuePairs;
    }

    private static Part[] buildFileAndValuePart(File file, String fileName, Map<String, String> parameters) {
        Part filePart = null;
        try {
            filePart = new FilePart(fileName, file);
        } catch (FileNotFoundException ex) {
            logger.error("", ex);
            throw new HttpInvokeException(ex);
        }
        if (parameters == null || parameters.isEmpty()) {
            return new Part[]{filePart};
        }
        Part[] parts = new Part[parameters.size() + 1];
        List<Part> partList = new ArrayList<Part>(parameters.size() + 1);
        partList.add(filePart);
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            partList.add(new StringPart(entry.getKey(), entry.getValue()));
        }
        partList.toArray(parts);
        return parts;
    }

    public static void main(String[] args) {
//        System.out.println(get("http://localhost:8080/test/data?id=1"));
//        System.out.println(get("http://localhost:8080/test/data?id=1"));
//        Map<String, String> param = new HashMap<>();
//        param.put("id", "2");
//        System.out.println(post("http://localhost:8080/test/data", param,"text/html",DEFAULT_CHARET,"id=2"));
//        MyThread thread1 = new MyThread();
//        MyThread thread2 = new MyThread();
//        MyThread thread3 = new MyThread();
//        thread1.start();
//        thread2.start();
//        thread3.start();

//        try{
//            executeMethod(null);
//        }catch (Exception ex){
//
//        }

//        Map<String, String> param = new HashMap<String, String>();
//        param.put("username", "sms");
//        param.put("md5str", "3b08c11ee0c73eb74530d29ca3f3d414");
//        param.put("channel", "sms");
//
//        String url = "http://upload.letvcdn.com:8000/zip_img_upload_tool.php";
//        File file = new File("d:\\p2668.tar.gz");
//
//        System.out.println(uploadFile(url, file, "uploadfile", param));
        //System.out.println(getJumpUrl("http://videos.fjhps.com/20180202/C4IdYEWG/index.m3u8"));
        for (int i=0; i<100;i++) {
            System.out.println(getJumpUrl("http://videos.fjhps.com/20180202/C4IdYEWG/index.m3u8"));
        }
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            long time = System.currentTimeMillis();
            for (int i = 0; i < 100; i++) {
                System.out.println(HttpClientUtil.get("http://localhost:8080/test/data?id=1"));
            }
            System.out.println(Thread.currentThread());
            System.out.println(System.currentTimeMillis() - time);
        }
    }

    public static String getJumpUrl(String url) {
        try {
            URL serverUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) serverUrl
                    .openConnection();
            conn.setRequestMethod("GET");
            // 必须设置false，否则会自动redirect到Location的地址
            conn.setInstanceFollowRedirects(false);
            conn.addRequestProperty("Accept-Charset", "UTF-8;");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.connect();
            String location = conn.getHeaderField("Location");
            if (location == null) {
                return openUrlTest(url);
            }
            return location;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String openUrlTest(String getUrl) {

        try {
            if (!getUrl.contains("https://") && getUrl.contains("http://")) {
                String resultUrl = getUrl.replace("http://", "https://");
                URL url = new URL(resultUrl.trim());
                //打开连接
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                if(200 == urlConnection.getResponseCode()){
                    return resultUrl;
                } else {
                    return getUrl;
                }
            }

        } catch (IOException e) {

        }
        return getUrl;
    }

}
