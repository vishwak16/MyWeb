package com.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/dashboardServlet")

public class dashboardServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String uname;
		
		uname = request.getParameter("uname");
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "Vishwak@1234");
			String query = "select * from student where (username = '"+uname+"' );";
			Statement st = con.createStatement();
			ResultSet result = st.executeQuery(query);
			
			while(result.next()) {
				out.print("<h1> Hello " + result.getString(1) + "</h1> ");
				out.print("<h3> Your email is " + result.getString(3) + "</h3> ");
				out.print("<h4> DOB " + result.getString(4) + "</h4> ");
				out.print("<p> Gender " + result.getString(5) + "</p> ");
		
			}
			con.close(); 
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
