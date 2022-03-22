package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/calc3")
public class Calc3 extends HttpServlet {
       

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//요청 파라미터 읽어오기
		String value = request.getParameter("value");
		String operator = request.getParameter("operator");
		String dot = request.getParameter("dot");
		
		//쿠키 읽어오기
		String exp = "";
		Cookie[] cookies = request.getCookies();
		if(cookies!=null) {
			for(Cookie c : cookies) {
				if(c.getName().equals("exp")) {
					exp = c.getValue();
					break;
				}
			}
		}
		
		//계산
		if(operator!=null && operator.equals("=")) {
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("graal.js");
			try {
				exp = String.valueOf(engine.eval(exp));
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//exp 쿠키에 value, operator, dot 값 추가
		else{ 
			exp += (value == null)?"":value;
			exp += (operator == null)?"":operator;
			exp += (dot == null)?"":dot;
		}
		
		
		//쿠키 저장
		Cookie expCookie = new Cookie("exp", exp);
		
		if(operator!=null && operator.equals("C")) {
			expCookie.setMaxAge(0); //쿠키 지우기
		}
		response.addCookie(expCookie);
		response.sendRedirect("calcpage");
		
		
	}

}
