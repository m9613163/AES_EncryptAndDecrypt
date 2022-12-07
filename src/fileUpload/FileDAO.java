package fileUpload;

import java.util.ArrayList;
import java.util.List;

import common.JDBConnect;

public class FileDAO extends JDBConnect{
	
	public void insertFile(FileDTO dto) {
		try {
			String sql = "insert into st20224015_file(idx, name, title, cate, ofile, sfile, purpose, postdate)";
			sql += "values ((select nvl(max(idx), 0) + 1 from st20224015_file), ? ,? ,? ,?, ?, ?, curdate())";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getCate());
			pstmt.setString(4, dto.getOfile());
			pstmt.setString(5, dto.getSfile());
			pstmt.setString(6, dto.getPurpose());
			pstmt.executeUpdate();
			
			System.out.println("insertFile() is finished");
		} catch (Exception e) {
			System.out.println("Exception[insertFile] : " + e.getMessage());
		}
	}
	
	public List<FileDTO> myFileList() {
		
		List<FileDTO> myList = new ArrayList<FileDTO>();
		
		try {
			String sql = "select * from st20224015_file";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				FileDTO dto = new FileDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setCate(rs.getString("cate"));
				dto.setOfile(rs.getString("ofile"));
				dto.setSfile(rs.getString("sfile"));
				dto.setPurpose(rs.getString("purpose"));
				dto.setPostdate(rs.getString("postdate"));
				
				myList.add(dto);
			}
		} catch (Exception e) {
			System.out.println("Exception[listFile] : " + e.getMessage());
		}
		
		return myList;
	}
}
