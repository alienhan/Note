---
title: java处理网络协议
date:
categories:
- 网络协议
tags:
- java 处理http
- java 处理ssl
---

### java http 链接
code:
```java
	String urlStr = "www.baidu.com";
	URL url = new URL(urlStr);
	HttpURLConnection http = (HttpURLConnection) url.openConnection();
	http.setRequestMethod("POST");//get,post 请求方法
	http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	http.setDoOutput(true);//可以输出
	http.setDoInput(true);//可以输入
	http.connect();
	
	OutputStream os = http.getOutputStream();//Returns an output stream that writes to this connection.
	os.write(paramStr.getBytes(encode));// 传入参数，encode为参数在字节流中的编码
	os.flush();
	os.close();

	String message = null;
	InputStream is = http.getInputStream();//Returns an input stream that reads from this open connection.
	int size = is.available();
	byte[] jsonBytes = new byte[size];
	is.read(jsonBytes);
	message = new String(jsonBytes, encode);//jsonBytes为字节
```

### HttpClient 链接
code：
```java
	ca验证：
	// 指定读取证书格式为PKCS12
	KeyStore keyStore = KeyStore.getInstance("PKCS12");
	// 读取本机存放的PKCS12证书文件
	FileInputStream instream = new FileInputStream(new File("D:\\java\\lib\\weixin\\cert\\apiclient_cert.p12"));
	try {
		// 指定PKCS12的密码(商户ID)
		keyStore.load(instream, "1315688001".toCharArray());
	} finally {
		instream.close();
	}

	// Trust own CA and all self-signed certs
	SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, "1315688001".toCharArray()).build();
	// Allow TLSv1 protocol only 指定TLS版本
	SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
	CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
	// httpclient.getParams().setParameter(HttpEntity,"utf-8");
	try {

		HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack");
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");

		//System.out.println(params.getBytes().);
		//StringEntity s = new StringEntity(new String(params.getBytes("gb18030"),"utf-8"));
		ByteArrayEntity b = new ByteArrayEntity(params.getBytes("utf-8"));
		// s.setContentEncoding("UTF-8");
		httpPost.setEntity(b);
		// 设置httpclient的SSLSocketFactory,发送请求
		System.out.println("executing request" + httpPost.getRequestLine());

		System.out.println("executing request" + httpPost.getEntity().getContentEncoding());
		CloseableHttpResponse response = httpclient.execute(httpPost);
		try {
			HttpEntity entity = response.getEntity();

			System.out.println("----------------------------------------");
			System.out.println(response.getStatusLine());
			if (entity != null) {
				System.out.println("Response content length: " + entity.getContentLength());
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
				String text;
				while ((text = bufferedReader.readLine()) != null) {
					System.out.println(text);
				}

			}
			EntityUtils.consume(entity);
		} finally {
			response.close();
		}
	} finally {
		httpclient.close();
	}
```
