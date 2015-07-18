<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.BufferedReader"%>
<%@ page import="java.io.InputStreamReader"%>
<%@ page import="java.io.IOException"%>
<%@ page import="java.io.InputStream"%>
<%@ page import="java.net.URL"%>
<%@ page import="java.net.*"%>

<%
	String term = "";

	if (request.getParameter("q") != null && !"".equals(request.getParameter("q")) && !"null".equalsIgnoreCase(term)) {
		term = URLEncoder.encode(new String(request.getParameter("q").getBytes("ISO-8859-1"), "UTF-8"), "utf-8");
	}

	String pageNum = request.getParameter("n");

	if ("null".equals(pageNum) || pageNum == null) {
		pageNum = "0";
	}

	if (!"".equals(term)) {
		String command = "python /Users/Vurtnec/Dev/Tomcat7.0/webapps/ROOT/google.py " + term + " " + pageNum;

		String str;
		try {
			Process pr = Runtime.getRuntime().exec(command);
			BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				out.println(line);
			}
			in.close();
			pr.waitFor();
		} catch (IOException e) {
			System.out.println(e);
		}

	} else {
		out.print("error");
	}
%>

