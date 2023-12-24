package com.auth;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url, uname, pass;
		
		ServletContext ctx = request.getServletContext();
		uname = ctx.getInitParameter("uname");
		pass = ctx.getInitParameter("pass");
		url = ctx.getInitParameter("url");
		
		try {
			Connection con = DriverManager.getConnection(url, uname, pass);
			
			String query = "insert into student values(?, ?, ?, ?, ?)";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, request.getParameter("uname"));
			st.setString(2, request.getParameter("pass"));
			st.setString(3, request.getParameter("email"));
			st.setString(4, request.getParameter("dob"));
			st.setString(5, request.getParameter("gender"));
			
			st.executeUpdate();
			
			con.close();
			
			response.sendRedirect("/MyWeb/login.html");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
