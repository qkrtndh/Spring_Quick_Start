<%@page contentType="text/html; charset=EUC-KR" %>
<%
	//1. 브라우저와 연결된 세션 객체를 강제종료
	session.invalidate();

	//2. 세션 종료후 메인화면 이동
	response.sendRedirect("login.jsp");
	
%>