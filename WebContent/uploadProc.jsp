<%@page import="fileUpload.FileDAO"%>
<%@page import="fileUpload.FileDTO"%>
<%@page import="st20224015_proSecurity.CipherStreamBean"%>
<%@page import="javax.crypto.Cipher"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String saveDirectory = application.getRealPath("/Uploads");
int maxPostSize = 1024 * 1024 * 50;
String encoding = "utf-8";

// 1. multipartRequest 객체 생성
try {
	MultipartRequest mr = new MultipartRequest(request, saveDirectory, maxPostSize, encoding);

	// 2. form 값 받기
	String name = mr.getParameter("name");
	String title = mr.getParameter("title");
	String cate = mr.getParameter("cate");
	StringBuffer cateBuf = new StringBuffer();
	String type = mr.getParameter("type");
	String fileName = mr.getFilesystemName("attachedFile");
	String ext = fileName.substring(fileName.lastIndexOf("."));
	String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
	String newFileName = now + ext;

	String fileFullName = saveDirectory + File.separator + fileName;
	File file = new File(fileFullName);
	System.out.println("파일명 : " + fileFullName);
	System.out.println("type : " + type);

	// 3. 암복호화
	if (type.equals("encrypt")) {
		CipherStreamBean bean = CipherStreamBean.getInstance();
		bean.encryptFile(fileFullName);
	} else if (type.equals("decrypt")) {
		CipherStreamBean bean = CipherStreamBean.getInstance();
		bean.decryptFile(fileFullName);
	}

	// 4. DTO 생성
	FileDTO dto = new FileDTO();
	dto.setName(name);
	dto.setTitle(title);
	dto.setCate(cate);
	dto.setOfile(fileName);
	dto.setSfile(newFileName);
	dto.setPurpose(type);

	// 5. DAO 사용하여 CRUD
	FileDAO dao = new FileDAO();
	dao.insertFile(dto);
	dao.close();

	response.sendRedirect("myFileList.jsp");

} catch (Exception e) {

}
%>