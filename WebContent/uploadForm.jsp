<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>파일 업로드(암호화/복호화)</h2>
	<form action="uploadProc.jsp" enctype="multipart/form-data" method="post">
		작성자 : <input type="text" name="name"> <br>
		제목 : <input type="text" name="title"> <br>
		카테고리 : <input type="radio" name="cate" checked value="사진">사진
				<input type="radio" name="cate" value="문서">문서
				<input type="radio" name="cate" value="음원">음원 
				<input type="radio" name="cate" value="기타">기타 <br>
		유형 : <input type="radio" name="type" checked value="encrypt"> 암호화
		<input type="radio" name="type" value="decrypt"> 복호화 <br>
		업로드 : <input type="file" name="attachedFile"> <br>
		<input type="submit" value="업로드">
	</form>
</body>
</html>