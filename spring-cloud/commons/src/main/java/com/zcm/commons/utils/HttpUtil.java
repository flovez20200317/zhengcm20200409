package com.zcm.commons.utils;

import com.alibaba.fastjson.*;
import lombok.extern.slf4j.*;
import org.apache.http.*;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.*;
import org.apache.http.client.entity.*;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.*;
import org.apache.http.entity.*;
import org.apache.http.impl.client.*;
import org.apache.http.message.*;
import org.apache.http.util.*;
import org.springframework.util.*;

import java.io.*;
import java.util.*;

/**
 * @ClassName: HttpUtil
 * @Author: FJW
 * @Date: 2021/4/28 15:34
 * @Version: 1.0.0
 * @Description: HTTP帮助类
 */
@Slf4j
public class HttpUtil {
    /**
     * 无参
     *
     * @param url
     * @return
     * @Author sfh
     * @Date 2020/4/14 15:56
     */
    public static String doGet(String url) {
        return doGet(url, null, null);
    }

    /**
     * 固定参数
     *
     * @param url
     * @param param
     * @return
     * @Author sfh
     * @Date 2020/4/14 15:56
     */
    public static String doGet(String url, String param) {
        return doGet(url, param, null);
    }

    /**
     * 传入MAP 参数，自动拼装请求
     *
     * @param url
     * @return
     * @Author sfh
     * @Date 2020/6/28 14:43
     * @version:
     */
    public static String doGet(String url, Map<String, String> head) {
        return doGet(url, null, head);
    }

    /**
     * get请求底层
     *
     * @param url   请求地址
     * @param param 参数
     * @param head  请求头
     * @return
     * @Author sfh
     * @Date 2020/4/14 15:56
     */
    public static String doGet(String url, String param, Map<String, String> head) {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            String urlNameString = url;

            if (!StringUtils.isEmpty(param)) {
                urlNameString = urlNameString + "?" + param;
            }

            HttpGet httpGet = new HttpGet(urlNameString);
            log.info(urlNameString);
            if (head != null) {
                Set<Map.Entry<String, String>> entries = head.entrySet();
                Iterator<Map.Entry<String, String>> iterator = entries.iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> next = iterator.next();
                    String key = next.getKey();
                    String value = next.getValue();
                    httpGet.setHeader(key, value);
                }
            }

            httpGet.setHeader("accept", "*/*");
            httpGet.setHeader("connection", "Keep-Alive");
            httpGet.setHeader("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            CloseableHttpResponse execute = httpClient.execute(httpGet);
            HttpEntity entity = execute.getEntity();

            //判断entity
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
            }
            if (result == null) {
                return "";
            }
            return result;
        } catch (Exception e) {
            log.error("get error", e);
            return null;
        } finally {
            // 使用finally块来关闭输入流
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (Exception e) {
                log.error("get error", e);
            }
        }
    }

    /**
     * 获取返回数据流
     *
     * @param url   请求地址
     * @param param 参数
     * @param head  请求头
     * @return
     * @Author sfh
     * @Date 2020/7/28 14:38
     * @version:
     */
    public static CloseableHttpResponse doGetReturnEntity(String url, String param, Map<String, String> head) {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            String urlNameString = url;

            if (!StringUtils.isEmpty(param)) {
                urlNameString = urlNameString + "?" + param;
            }

            HttpGet httpGet = new HttpGet(urlNameString);

            if (head != null) {
                Set<Map.Entry<String, String>> entries = head.entrySet();
                Iterator<Map.Entry<String, String>> iterator = entries.iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> next = iterator.next();
                    String key = next.getKey();
                    String value = next.getValue();
                    httpGet.setHeader(key, value);
                }
            }

            httpGet.setHeader("accept", "*/*");
            httpGet.setHeader("connection", "Keep-Alive");
            httpGet.setHeader("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            CloseableHttpResponse execute = httpClient.execute(httpGet);
            return execute;
        } catch (Exception e) {
            log.error("get error", e);
        }
        return null;
    }

    public static String doGetByMap(String url, HashMap<String, String> params) {

        //客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //封装请求参数
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        //响应结果
        String responStr = "";
        CloseableHttpResponse response = null;

        try {
            //封装uri及参数
            URIBuilder builder = new URIBuilder(url);
            builder.setParameters(pairs);

            //get请求对象
            HttpGet httpGet = new HttpGet(builder.build());
            response = httpClient.execute(httpGet);

            //判断返回结果
            if (response != null && response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                responStr = EntityUtils.toString(entity);
            }
            return responStr;
        } catch (Exception e) {
            log.error("HttpUtil", e);
        } finally {
            // 使用finally块来关闭输入流
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (Exception e) {
                log.error("get error", e);
            }
        }
        return responStr;
    }
    /************************* do post **********************************/


    /**
     * @param url   请求地址
     * @param param 参数
     * @return
     * @Author sfh
     * @Date 2020/6/28 14:57
     * @version:
     */
    public static String doPost(String url, String param) {
        return doJsonPost(url, param, null);
    }

    /**
     * @param url   请求地址
     * @param param 参数
     * @return
     * @Author sfh
     * @Date 2020/6/28 14:57
     * @version:
     */
    public static String doJsonPost(String url, Object param) {
        return doJsonPost(url, param, null);
    }


    /**
     * post请求最底层
     *
     * @param url   请求地址
     * @param param 参数
     * @param head  请求头
     * @return
     * @Author sfh
     * @Date 2020/6/28 14:52
     * @version:
     */
    public static String doJsonPost(String url, Object param, Map<String, String> head) {
        //创建默认httpclient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            //创建post请求实例
            HttpPost httpPost = new HttpPost(url);
            //创建接受对象
            String result = null;

            httpPost.setHeader("content-type", "application/json");

            if (head != null) {
                Set<Map.Entry<String, String>> entries = head.entrySet();
                Iterator<Map.Entry<String, String>> iterator = entries.iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> next = iterator.next();
                    String key = next.getKey();
                    String value = next.getValue();
                    httpPost.setHeader(key, value);
                }
            }

            String info;
            if (param instanceof String) {
                info = param.toString();
            } else {
                info = JSON.toJSONString(param);
            }

            //设置请求头
            //创建请求实体(json),讲字符串形式的json数据转换为stringentity
            StringEntity stringEntity = new StringEntity(info, "utf-8");
            //将post请求设置请求实体
            httpPost.setEntity(stringEntity);
            //执行请求
            //接收响应
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            //处理响应结果,从响应结果中获取httpentity
            HttpEntity entity = httpResponse.getEntity();
            //判断entity
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
            }
            return result;
        } catch (IOException e) {
            log.error("doPostForm", e);
            return null;
        } finally {
            //释放资源
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * post请求 图搜请求处理
     *
     * @param url   请求地址
     * @param param 参数
     * @return
     * @Author jinyang
     * @Date 2020/12/24
     * @version:
     */
    public static String keepAlivePost(String url, Object param) {
//        ConnectionKeepAliveStrategy connectionKeepAliveStrategy = new ConnectionKeepAliveStrategy() {
//            @Override
//            public long getKeepAliveDuration(org.apache.http.HttpResponse httpResponse, HttpContext httpContext) {
//                return 10 * 1000; //Tomcat 默认 keep-alive 超时是 20s
//            }
//
//        };
//         CloseableHttpClient httpClient = HttpClients.custom().setKeepAliveStrategy(connectionKeepAliveStrategy).create().build();
        //创建httpclient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            //创建post请求实例
            HttpPost httpPost = new HttpPost(url);

            RequestConfig config = RequestConfig.custom()
                    .setConnectionRequestTimeout(60000) // 设置请求超时时间  毫秒
                    .setConnectTimeout(60000)           // 设置连接超时时间  毫秒
                    .setSocketTimeout(60000)            // 设置读取超时时间  毫秒
                    .build();
            httpPost.setConfig(config);
            //创建接受对象
            String result = null;

            httpPost.setHeader("content-type", "application/json");

            String info;
            if (param instanceof String) {
                info = param.toString();
            } else {
                info = JSON.toJSONString(param);
            }

            //设置请求头
            //创建请求实体(json),讲字符串形式的json数据转换为stringentity
            StringEntity stringEntity = new StringEntity(info, "utf-8");
            //将post请求设置请求实体
            httpPost.setEntity(stringEntity);
            //执行请求
            //接收响应
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            //处理响应结果,从响应结果中获取httpentity
            HttpEntity entity = httpResponse.getEntity();
            //判断entity
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
            }
            return result;
        } catch (IOException e) {
//            log.error("doPostForm", e);
            return null;
        } finally {
            //释放资源
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 表单提交
     *
     * @param url 访问地址
     * @param map 参数
     * @return
     * @Author sfh
     * @Date 2020/6/28 15:00
     * @version:
     */
    public static String doPostForm(String url, Map<String, Object> map) {
        return doPostForm(url, map, null);
    }

    /**
     * 表单提交
     *
     * @param url  访问地址
     * @param map  参数
     * @param head 请求头
     * @return
     * @Author sfh
     * @Date 2020/6/28 14:58
     * @version:
     */
    public static String doPostForm(String url, Map<String, Object> map, Map<String, String> head) {
        //创建默认httpclient实例2
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            //创建post请求实例
            HttpPost httpPost = new HttpPost(url);
            //创建接受对象
            String result = null;
            //设置请求头
            httpPost.setHeader("content-type", "application/x-www-form-urlencoded");


            if (head != null) {
                Set<Map.Entry<String, String>> entries = head.entrySet();
                Iterator<Map.Entry<String, String>> iterator = entries.iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> next = iterator.next();
                    String key = next.getKey();
                    String value = next.getValue();
                    httpPost.setHeader(key, value);
                }
            }


            //设置请求参数
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
            if (map != null) {
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    nameValuePairs.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
                }
            }
            //将post请求设置请求实体
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "utf-8"));
            //执行请求
            //接收响应
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

            //处理响应结果,从响应结果中获取httpentity
            HttpEntity entity = httpResponse.getEntity();

            //判断entity
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
            }
            return result;
        } catch (IOException e) {
            log.error("doPostForm", e);
            return null;
        } finally {
            //释放资源
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void closeClient(CloseableHttpClient httpClient, CloseableHttpResponse response) {
        try {
            httpClient.close();
            if (response != null) {
                response.close();
            }
        } catch (IOException e) {
            log.error("HttpUtil", e);
        }
    }
}
