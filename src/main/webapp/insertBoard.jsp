<%@page contentType="text/html; charset=EUC-KR" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>���۵��</title>
</head>
<body>
<center>
<h1>�� ���</h1>
<a href="logout_proc.jsp">Log-out</a>
<hr>
<form action="insertBoard.do" method="post">
	<table border="1" cellpadding="0" cellspacing="0">
		<tr>
			<td bgcolor="orange" width="70">����</td>
			<td align="left"><input type="text" name="title" /></td>
		</tr>
		<tr>
			<td bgcolor="orange">�ۼ���</td>
			<td align="left"><input type="text" name="writer" size="10" /></td>
		</tr>
		<tr>
			<td bgcolor="orange">����</td>
			<td align="left"><textarea name="content" col="40" rows="10" ></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="���۵��" />
			</td>
		</tr>
	</table>
</form>
<hr>
<a href="getBoardList.jsp">�� ��� ����</a>&nbsp;&nbsp;&nbsp;
</center>
</body>
</html>