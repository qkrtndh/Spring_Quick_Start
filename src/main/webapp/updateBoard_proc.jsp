<%@page contentType="text/html; charset=EUC-KR" %>
<%@page import="com.springbook.biz.board.impl.BoardDAO" %>
<%@page import="com.springbook.biz.board.BoardVO" %>
<%
	//1. ����� �Է� ���� ����
	request.setCharacterEncoding("EUC-KR");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String seq = request.getParameter("seq");
	
	//2. DB ���� ó��
	BoardVO vo = new BoardVO();
	vo.setTitle(title);
	vo.setSeq(Integer.parseInt(seq));
	vo.setContent(content);
	
	BoardDAO boardDAO= new BoardDAO();
	boardDAO.updateBoard(vo);
	
	//3. ȭ�� �׺���̼�
	response.sendRedirect("getBoardList.jsp");
%>