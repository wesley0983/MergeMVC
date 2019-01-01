package home_class;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;

import javax.jws.WebService;
import javax.servlet.http.*;

public class HelloGet extends HttpServlet{
	
	public void doGet(HttpServletRequest req , HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("Big5");
		res.setContentType("text/html ; charset=Big5");
		PrintWriter out = res.getWriter();
		
		String[] name = req.getParameterValues("name");
		
		
//	    System.out.println(name.length() == 0);
//        System.out.println(name.trim().isEmpty());
//        System.out.println(name.trim().equals(""));
//        res.sendError(500);
//        res.sendError(res.SC_NOT_FOUND, "�ФĿ�checkbox");
		if(name == null) {
		     out.println("<h1>未輸入checkbox</h1>");	
		} else {
		    for(int i = 0 ; i < name.length ; i ++) {		 
				 out.println("<html>");
			     out.println("<h1>"+ name[i] +"<h1>");
			     out.println("<h4>Servlet_Test");
			     out.println("</html>");
	        }
		}
		
//		if(name == null) {
//			out.print("<h1>�ФĿ�checkbox</h1>");
//		} else {
//	        out.println("<html>");
//	        out.println("<h1>"+ name +"<h1>");
//	        out.println("</html>");
//		}
	}
	
	

}
