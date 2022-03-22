package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/calc")
public class Calc extends HttpServlet {
       

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int a = 0;
		int b = 0;
		int result = 0;
		String x = request.getParameter("x");
		String y = request.getParameter("y");
		String operater = request.getParameter("operater");
		
		if(!x.equals("")) {
			a = Integer.parseInt(x);
		}
		if(!y.equals("")) {
			b = Integer.parseInt(y);
		}
		if(operater.equals("µ¡¼À")) {result = a+b;}
		if(operater.equals("»¬¼À")) {result = a-b;}
		
		PrintWriter out = response.getWriter();
		out.println(result);
		
	}

}
