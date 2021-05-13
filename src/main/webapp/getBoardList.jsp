<%@page contentType="text/html; charset=EUC-KR" %>
<%@page import="java.util.List" %>
<%@page import="com.springbook.biz.board.impl.BoardDAO"%>
<%@page import="com.springbook.biz.board.BoardVO"%>

<%
	//세션에 저장된 글 목록을 꺼낸다.
	List<BoardVO> boardList =(List)session.getAttribute("boardList");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>글 목록</title>
</head>
<body>
<center>
<H1>글 목록</H1>
<H3>테스트님 환영합니다. <a href="logout_proc.jsp">Log-out</a></H3>
<!-- 검색 시작 -->
<form action="getBoardList.jsp" method="post"> 
<table border="1" cellpadding="0" cellspacing="0" width="700">
<tr>
<td align="right">
	<select name="searchCondition">
	<option value="TITLE">제목
	<option value="CONTENT">내용
	</select>
	<input name="searchkeyword" type="text"/>
	<input type="submit" value="검색" />
	</td>
	</tr>
</table>
</form>
<!-- 검색종료 -->
<table border="1" cellpadding="0" cellspacing="0" width="700">
<tr>
<th bgcolor="orange" width="100">번호</th>
<th bgcolor="orange" width="200">제목</th>
<th bgcolor="orange" width="150">작성자</th>
<th bgcolor="orange" width="150">등록일</th>
<th bgcolor="orange" width="100">조회수</th>
</tr>
<% for(BoardVO board : boardList){ %>
<tr>
	<td><%= board.getSeq() %></td>
	<td align="left"><a href="getBoard.do?seq=<%= board.getSeq() %>">
	<%= board.getTitle() %></a></td>
	<td><%= board.getWriter() %></td>
	<td><%= board.getRegDate() %></td>
	<td><%= board.getCnt() %></td>
</tr>
<%} %>
</table>
<br>
<a href="insertBoard.jsp">새글 등록</a>
</center>
</body>
</html>