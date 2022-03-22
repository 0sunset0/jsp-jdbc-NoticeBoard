package com.newlecture.web.controller.admin.notice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;
import com.newlecture.web.service.NoticeService;
@WebServlet("/admin/board/notice/list")
public class ListController extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] openIds_ = request.getParameterValues("open-id");
		String[] delIds_ = request.getParameterValues("del-id");
		String cmd = request.getParameter("cmd");
		
		if(cmd.equals("老褒傍俺")) {
			
			int[] ids = new int[openIds_.length];
			
			for(int i=0; i<openIds_.length; i++) {
				ids[i] = Integer.parseInt(openIds_[i]);
			}
		
			NoticeService notice = new NoticeService();
			notice.pubNoticeAll(ids);
		}
		else if(cmd.equals("老褒昏力")) {
			int[] ids = new int[delIds_.length];
			
			for(int i=0; i<delIds_.length; i++) {
				ids[i] = Integer.parseInt(delIds_[i]);
			}
		
			NoticeService notice = new NoticeService();
			notice.deleteNoticeAll(ids);
			
		}
		
		response.sendRedirect("list"); //get夸没
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		String page_ = request.getParameter("p");
		
		String field = "title";
		if(field_!=null && !field_.equals("")) {
			field = field_;
		}
		
		String query = "";
		if(query_!=null && !query_.equals("")) {
			query = query_;
		}
		
		int page = 1;
		if(page_!=null && !page_.equals("")) {
			page = Integer.parseInt(page_);
		}
		
		
		
		NoticeService service = new NoticeService();
		
		
		List<NoticeView> list = service.getNoticeList(field, query, page);
		int count = service.getNoticeCount(field, query);
		
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/list.jsp");
		dispatcher.forward(request, response);
		
	}
	
	
		
	

}

