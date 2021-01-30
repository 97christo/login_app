
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class LoginServlet
 */
@SuppressWarnings("serial")
@WebServlet("/login")
public class LoginServlet extends GenericServlet {

	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		String input_id = request.getParameter("id");
		String input_pw = request.getParameter("pw");

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String db_url = "jdbc:mysql://db-master.cjq318fitl2e.ap-northeast-2.rds.amazonaws.com:3306";
		String db_user = "admin";
		String db_pwd = "qwer1234";
		String db_name = "/testDB";
		String tbl_name = "test_tbl";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println(e.getMessage());
		}

		try {
			Connection conn = DriverManager.getConnection(db_url + db_name, db_user, db_pwd);

			String query = "SELECT * FROM " + tbl_name + " WHERE id='" + input_id + "'";
			System.out.println("query: " + query);

			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			boolean isEmpty = true;


			if (rs.next()) {
				isEmpty = false;

				String result_id = rs.getString("id");
				String result_pw = rs.getString("pw");

				if (result_pw.equals(input_pw)) {
					out.println("<p>로그인 성공! (id: " + result_id + ")</p>");
				} else {
					out.println("<p>로그인 실패. (pw 틀림)</p>");
				}
			}

			if (isEmpty) {
				out.println("<p>존재하지 않는 id</p>");
			}

			rs.close();
			ps.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
