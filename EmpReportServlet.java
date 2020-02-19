package com.yash.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmpReportServlet
 */
@WebServlet("/ers")
public class EmpReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String driverClassName = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost/empdb";
	String userName = "root";
	String password = "root";
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// set the response type
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			Class c = Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, userName, password);
			String sql = "select * from emp";
			connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();

			out.println("<html>");
			out.println("<head><title>Employee Report</title></head>");
			out.println("<body>");
			out.println("<table border='1'>");
			out.println("<tr>");
			out.println("<td>ID</td>");
			out.println("<td>Name</td>");
			out.println("<td>Salary</td>");
			out.println("</tr>");
			while (rs != null && rs.next()) {
				out.println("<tr>");
				out.println("<td>" + rs.getInt("empId") + "</td>");
				out.println("<td>" + rs.getString("name") + "</td>");
				out.println("<td>" + rs.getFloat("salary") + "</td>");
				out.println("</tr>");
			}

			out.println("</table>");
			out.println("</body>");
			out.println("</html>");

		} catch (Exception e) {

		} finally {
			out.close();

		}
	}

}
