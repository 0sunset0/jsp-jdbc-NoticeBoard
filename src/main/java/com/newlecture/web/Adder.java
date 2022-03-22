package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/add")
public class Adder extends HttpServlet {
       

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int a = 0;
		int b = 0;
		String x = request.getParameter("x");
		String y = request.getParameter("y");
		
		if(!x.equals("")) {
			a = Integer.parseInt(x);
		}
		if(!y.equals("")) {
			b = Integer.parseInt(y);
		}
		int sum = a+b;
		
		PrintWriter out = response.getWriter();
		out.println(sum);
		
	}

}
