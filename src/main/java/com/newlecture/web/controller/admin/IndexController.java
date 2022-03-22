package com.newlecture.web.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.service.NoticeService;

@WebServlet("/admin/board/index")
public class IndexController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/view/admin/board/index.jsp");
		dispatcher.forward(request, response);
	}
	
}
