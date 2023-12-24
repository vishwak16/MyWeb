package com.auth;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String uname = request.getParameter("uname");
		String pass = request.getParameter("pass");
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "Vishwak@1234");
			String query = "select password from student where (username = '"+uname+"' );";
			Statement st = con.createStatement();
			ResultSet result = st.executeQuery(query);
			String password;
			if(result.next()){
				password = result.getString(1);
			}
			else {
				password = null;
			}
			con.close(); 
			
			if(password!=null && password.equals(pass)){
				RequestDispatcher rd = request.getRequestDispatcher("/dashboardServlet");
				rd.forward(request, response);
			}
			else if (password.equals(null)){
				out.println("<h1>" + "No User with this username found" + "</h1>");
			}	
			else{
				out.println("<h1>" + "Wrong Credentials" + "</h1>");
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
