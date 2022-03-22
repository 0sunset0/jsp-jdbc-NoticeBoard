package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/calc2")
public class Calc2 extends HttpServlet {
       

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ServletContext application = request.getServletContext();
		//HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		int v = 0;
		int result = 0;
		String v_ = request.getParameter("v");
		String operater = request.getParameter("operater");
		
		if(!v_.equals("")) {
			v = Integer.parseInt(v_);
		}
		//계산
		if(operater.equals("=")) {
			//int x = Integer.valueOf((String)application.getAttribute("value"));
			//int x = Integer.valueOf((String)session.getAttribute("value"));
			int x = 0;
			for(Cookie c : cookies) {
				if(c.getName().equals("value")) {
					x = Integer.parseInt(c.getValue());
					break;
				}
			}
			
			int y = v;
			
			String op = "";
			//String op = String.valueOf(application.getAttribute("operater"));
			//String op = String.valueOf(session.getAttribute("operater"));
			for(Cookie c : cookies) {
				if(c.getName().equals("operater")) {
					op = c.getValue();
					break;
				}
			}
			
			if(op.equals("+")) result = x+y;
			else result = x-y;
			PrintWriter out = response.getWriter();
			out.println(result);
		}
		//저장
		else {
			//application.setAttribute("value", v_);
			//application.setAttribute("operater", operater);
			//session.setAttribute("value", v_);
			//session.setAttribute("operater", operater);
			
			Cookie valueCookie = new Cookie("value", v_); //쿠키 만들기
			Cookie opCookie = new Cookie("operater", operater);
			valueCookie.setPath("/calc2");
			valueCookie.setMaxAge(24*60*60);
			opCookie.setPath("/calc2");
			response.addCookie(valueCookie); //클라이언트에게 전달(response header로)
			response.addCookie(opCookie);
			
			response.sendRedirect("calc2.html");
		}
		
		
		
		
	}

}
