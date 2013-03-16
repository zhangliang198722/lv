/**
 * 
 */
package com.smart.util;

import java.io.IOException;

import javax.net.ssl.SSLHandshakeException;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpVersion;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;

/**
 * @Project: jietu
 * @author Zhang Xiao Dong
 * @Date: 2012-6-14
 * 
 */
public class HttpConnectionManager {

	private static PoolingClientConnectionManager	cm	= null;
	static {
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
		schemeRegistry.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));
		cm = new PoolingClientConnectionManager(schemeRegistry);
		cm.setMaxTotal(200);
		// 每条通道的并发连接数设置（连接池）
		cm.setDefaultMaxPerRoute(20);
	}

	public static HttpClient getHttpClient() {
		HttpParams params = new BasicHttpParams();
		params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000); // 3000ms
		params.setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);// 数据传输时间60s
		params.setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET,"UTF-8");
		params.setParameter(CoreProtocolPNames.HTTP_ELEMENT_CHARSET,"UTF-8");
		params.setParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE,Boolean.FALSE);
		params.setParameter(CoreProtocolPNames.WAIT_FOR_CONTINUE,3000);
		DefaultHttpClient httpclient = new DefaultHttpClient(cm, params);
		HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {
			public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
				if (executionCount >= 5) {
					// 如果超过最大重试次数，那么就不要继续了
					return false;
				}
				if (exception instanceof NoHttpResponseException) {
					// 如果服务器丢掉了连接，那么就重试
					return true;
				}
				if (exception instanceof SSLHandshakeException) {
					// 不要重试SSL握手异常
					return false;
				}
				HttpRequest request = (HttpRequest) context.getAttribute(ExecutionContext.HTTP_REQUEST);
				boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
				if (idempotent) {
					// 如果请求被认为是幂等的，那么就重试
					return true;
				}
				return false;
			}
		};
		httpclient.setHttpRequestRetryHandler(myRetryHandler);
		return httpclient;
	}

	public static void release() {
		if (cm != null) {
			cm.shutdown();
		}
	}
}
