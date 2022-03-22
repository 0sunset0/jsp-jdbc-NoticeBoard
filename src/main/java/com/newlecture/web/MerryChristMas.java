package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/merryChristmas")
public class MerryChristMas extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		
		int a=0;
		
		for(int i=0; i<10; i++) {
			for(int j=0; j<20; j++) {
				if(j>=10-a&& j<=10+a) {
					out.print("*");
				}
				else {
					out.print(" ");
				}
			}
			a++;
			out.print("\n");
		}
		for(int k=0; k<2; k++) {
			out.print("        ****        \n");
			
		}
		out.print("\n  Merry ChristMas");
	}

}
