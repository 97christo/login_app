

import java.io.IOException;
import java.io.PrintWriter;

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
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");	
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<p>id: " + id + "</p>");
		out.println("<p>pw: " + pw + "</p>");
		out.println("<p>from tomcat server</p>");
		
//		=============================================		
//		아직 db와 연결되지 않아 부하 발생용 코드를 임시로 추가
		System.out.println("Generating load for test");
		for(int i = 0; i < 100; i++) {
			System.out.println((i * i - 1) / (i + 1));
		}
//		=============================================
	}

}
