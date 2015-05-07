<%@page import="java.net.*"%>
<%@page import="java.io.*"%>
<%

String url = (String)request.getParameter("url");

URL imgUrl;
BufferedReader in = null;
InputStream is = null;
try {
	imgUrl = new URL(url);

	URLConnection con = imgUrl.openConnection();
	con.setConnectTimeout(5 * 1000);
	is = con.getInputStream();
	
	byte[] bs = new byte[1024];
	int len;
	OutputStream os = response.getOutputStream();
	while ((len = is.read(bs)) != -1) {
		os.write(bs, 0, len);
	}
	
	os.flush();
	os.close();
} catch (MalformedURLException e) {
	e.printStackTrace();
} catch (IOException e) {
	e.printStackTrace();
} finally {
	try {
		if(in != null) {
			in.close();
		}
		if(is != null) {
			is.close();
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
}
%>