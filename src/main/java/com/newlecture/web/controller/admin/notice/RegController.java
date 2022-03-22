package com.newlecture.web.controller.admin.notice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;

@MultipartConfig(
		fileSizeThreshold = 1024*1024,
		maxFileSize = 1024*1024*50,
		maxRequestSize = 1024*1024*50*5
)
@WebServlet("/admin/board/notice/reg")
public class RegController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/reg.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//처리(파라미터 받기, 글 추가)
		String title =request.getParameter("title");
		String content=request.getParameter("content");
		String isOpen = request.getParameter("open");
		
		Collection<Part> parts = request.getParts(); //여러 개가 올 수 있음
		StringBuilder builder = new StringBuilder();
		for(Part p : parts) {
			
			if(!p.getName().equals("file")) {
				continue;
			} //file이 아니면 pass
			if(p.getSize() == 0) {
				continue;
			}
			
			Part filePart = p;
			String fileName = filePart.getSubmittedFileName(); //파일 이름 구하기
			builder.append(fileName);
			builder.append(",");
			InputStream fis = filePart.getInputStream();
			
			//upload 폴더의 절대 경로 
			String realPath = request.getServletContext().getRealPath("/upload");
			
			File path1 = new File(realPath);
			if(!path1.exists()) {
				path1.mkdirs();
			}
			
			
			String filePath = realPath + File.separator + fileName; //파일 경로 구하기
			
			FileOutputStream fos = new FileOutputStream(filePath); //출력(파일저장)을 위한 스트림
			
			byte[] buf = new byte[1024];
			int size = 0;
			while((size=fis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}
			
			fos.close();
			fis.close();
		}
		
		if(builder.length() != 0)
			builder.delete(builder.length()-1, builder.length()); //마지막 쉼표 제거
		
		boolean pub = false;
		if(isOpen!=null) {
			pub = true;
		}
		
		
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setContent(content);
		notice.setPub(pub);
		notice.setWriterId("Noh Eul");
		notice.setFiles(builder.toString());
		
		NoticeService service = new NoticeService();
		service.insertNotice(notice);
		
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text.html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(title+" "+content+" "+isOpen);
		
		
		//list로 redirect
		response.sendRedirect("list");
		
	}
}
