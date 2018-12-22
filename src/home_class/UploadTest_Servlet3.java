//package home_class;
//
//import java.io.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
//import java.util.*;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.http.Part;
//
//@WebServlet("/uploadServlet3.do1")
//@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5  * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
//// ��ƾڶq�j��fileSizeThreshold�ȮɡA���e�N�Q�g�J�Ϻ�
//// �W�ǹL�{���L�׬O��Ӥ��W�LmaxFileSize�ȡA�Ϊ̤W�Ǫ��`�q�j��maxRequestSize �ȳ��|�ߥXIllegalStateException ���`
//public class UploadTest_Servlet3 extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	String saveDirectory = "/images_uploaded"; // �W���ɮת��ئa�ؿ�;
//											   // �N�ѩ��U����26~30��� java.io.File �� ContextPath ���U, �۰ʫإߥئa�ؿ�
//
//	public void doPost(HttpServletRequest req, HttpServletResponse res)
//			throws ServletException, IOException {
//
//		req.setCharacterEncoding("Big5"); // �B�z�����ɦW
//		res.setContentType("text/html; charset=Big5");
//		PrintWriter out = res.getWriter();
//		System.out.println("ContentType="+req.getContentType()); // ���ե�
//
//		String realPath = getServletContext().getRealPath(saveDirectory);
//		System.out.println("realPath="+realPath); // ���ե�
//		File fsaveDirectory = new File(realPath);
//		if (!fsaveDirectory.exists())
//			 fsaveDirectory.mkdirs(); // �� ContextPath ���U,�۰ʫإߥئa�ؿ�
//
//		Collection<Part> parts = req.getParts(); // Servlet3.0�s�W�FPart�����A���ڭ̤�K���i���ɮפW�ǳB�z
//		out.write("<h2> Total parts : " + parts.size() + "</h2>");
//
//		for (Part part : parts) {
////			out.println("part.getSubmittedFileName():"+part.getSubmittedFileName()); 3.1 API
//			if (getFileNameFromPart(part) != null && part.getContentType()!=null) {
//				out.println("<PRE>");
//				String name = part.getName();  //�u�OHTML���Ҫ�name
//				String filename = getFileNameFromPart(part);  //API�èS���]�p���o�ɮצW�١A�ҥH�����ۭq��k
//				String ContentType = part.getContentType();  //���o���ɦW�A���O���P�s�����|���S��k���o�u����ɦW�����D
//				long size = part.getSize();
//				File f = new File(fsaveDirectory, filename);   //�إ�file����b��51��i�H�B��
//
//				out.println("name: " + name);
//				out.println("filename: " + filename);
//				out.println("ContentType: " + ContentType);
//				out.println("size: " + size);
//				out.println("File: " + f);
//
//				// �Q��File����,�g�J�ئa�ؿ�,�W�Ǧ��\
////				System.out.println("f:" + f.toString());
//				part.write(f.toString());
//
//				// �B�~���� InputStream �P byte[] (���N��model��VO�w�@�ǳ�)
//				InputStream in = part.getInputStream();
//				byte[] buf = new byte[in.available()];
//				in.read(buf);
//				in.close();
//				out.println("buffer length: " + buf.length);
//				
//				// �B�~���ըq��
//				out.println("<br><img src=\""+req.getContextPath()+saveDirectory+"/"+filename+"\">");
//
//				out.println();
//				out.println("</PRE>");
//			}
//		}
//	}
//
//	// ���X�W�Ǫ��ɮצW�� (�]��API������method,�ҥH�����ۦ漶�g)
//	public String getFileNameFromPart(Part part) {
//		String header = part.getHeader("content-disposition");
//		System.out.println("header=" + header); // ���ե�
//		//��substring���o�r��q��  �����k�i�H�����নfile����]�i�H�A�u�O�୼file����������h���k
//		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		System.out.println("filename=" + filename); // ���ե�
//		if (filename.length() == 0) {
//			return null;
//		}
//		return filename;
//	}
//}