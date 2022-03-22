package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/add2")
public class Adder2 extends HttpServlet {
       

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		String[] num_ = request.getParameterValues("num");
		
		int result = 0 ;
		for(int i=0; i<num_.length; i++) {
			int num = 0;
			if(!num_[i].equals("")){
				num = Integer.parseInt(num_[i]);
			}
			result += num;
		}
		
		
		PrintWriter out = response.getWriter();
		out.println(result);
		
	}

}
