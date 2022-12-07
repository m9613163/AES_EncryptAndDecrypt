<%@page import="java.net.URLEncoder"%>
<%@page import="fileUpload.FileDTO"%>
<%@page import="java.util.List"%>
<%@page import="fileUpload.FileDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		FileDAO dao = new FileDAO();
		List<FileDTO> myFileList = dao.myFileList();
	%>
	
	<a href="uploadForm.jsp">파일 작성하기</a>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>카테고리</th>
			<th>원본파일명</th>
			<th>저장파일명</th>
			<th>암호화/복호화 여부</th>
			<th>작성일</th>
		</tr>
		<%
			for(FileDTO dto : myFileList) {
		%>
		<tr>
			<td><%=dto.getIdx() %></td>
			<td><%=dto.getName() %></td>
			<td><%=dto.getTitle() %></td>
			<td><%=dto.getCate() %></td>
			<td><%=dto.getOfile() %></td>
			<td><a href="download.jsp?oName=<%=URLEncoder.encode(dto.getOfile(), "utf-8") %>&sName=<%=URLEncoder.encode(dto.getSfile(), "utf-8") %>"><%=dto.getSfile() %></a></td>
			<td><%=dto.getPurpose() %></td>
			<td><%=dto.getPostdate() %></td>
		</tr>
		<% } %>
	</table>
</body>
</html>