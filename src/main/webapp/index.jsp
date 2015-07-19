
<%
	String serverName = request.getServerName();

	if ("google.vurtnec.com".equals(serverName)) {
%>
<jsp:forward page="search.jsp" />
<%		//response.sendRedirect("search.jsp");
	} else if ("blog.vurtnec.com".equals(serverName) || "localhost".equals(serverName)) {
%>
<jsp:forward page="home" />
<%		//response.sendRedirect("home");
	}
%>