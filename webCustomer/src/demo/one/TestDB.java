package demo.one;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class TestDB
 */
@WebServlet("/TestDB")
public class TestDB extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		//set up a connection varivales
		String user = "angular22";
		String pass = "Angular22@";
		String url =  "jdbc:mysql://localhost:3306/customerdb?useLegacyDatetimeCode=false&serverTimezone=UTC";
		String driver = "com.mysql.jdbc.Driver";
		
		//get connection to DB;
		
		try {
			PrintWriter out = response.getWriter();
			out.println("Connection the the DB .. "+ url);
			Class.forName(driver);
			
			Connection con = DriverManager.getConnection(url, user, pass);
			out.println("Connection succesful..");
		}catch(Exception e) {
			e.getMessage();
			throw new ServletException(e);
		}
		
		
		
	}

}
