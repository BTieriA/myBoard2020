package kr.koreait.myboard;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.koreait.myboard.db.UserDAO;
import kr.koreait.myboard.vo.UserImgVO;
import kr.koreait.myboard.vo.UserVO;

/**
 * Servlet implementation class ProfileDetailSev
 */
@WebServlet("/profileDetail")
public class ProfileDetailSev extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 로그인 체크
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO) hs.getAttribute("loginUser");
		// loginSev서블릿에 loginUser를 불러온다
		if (loginUser == null) {
			response.sendRedirect("/login");
			return;
		}
		
		String img = UserDAO.getProfileImg(loginUser.getI_user());
		request.setAttribute("img", img);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/profileDetail.jsp");
		rd.forward(request, response);
	}
	
	// 프로필 이미지 업로드
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * String path = request.getSession().getServletContext().getRealPath("img");
		 * System.out.println("path : " + path);
		 * 
		 * HttpSession hs = request.getSession(); ServletContext sc =
		 * hs.getServletContext(); String path = sc.getRealPath("img");
		 * 
		 * 
		 * int size = 1024 * 1024 * 10; //10mb String fileNm = null; String
		 * originalFileNm = null;
		 * 
		 * try { MultipartRequest mr = new MultipartRequest(request, path, size,
		 * "UTF-8", new DefaultFileRenamePolicy()); Enumeration files =
		 * mr.getFileNames(); String str = (String)files.nextElement();
		 * 
		 * fileNm = mr.getFilesystemName(str); originalFileNm =
		 * mr.getOriginalFileName(str);
		 * 
		 * System.out.println("fileNm: " + fileNm);
		 * System.out.println("originalFileNm: " + originalFileNm);
		 * 
		 * } catch(Exception e) { e.printStackTrace(); }
		 */
		
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO) hs.getAttribute("loginUser");
		// i_user 얻어오기 위해 
		
		String filePath = String.valueOf(loginUser.getI_user());
		String fileNm = Utils.uploadfile(request, filePath);
		// i_user을 폴더로, utils에서 받은 파일명을 DB에 저장
		
		UserImgVO param = new UserImgVO();
		param.setI_user(loginUser.getI_user());
		param.setImg(fileNm);
		
		UserDAO.upUserImgAddSeq(param);
		int result = UserDAO.regUserImg(param);
		doGet(request,response);
		// doget에서 result 값이 1이면 통과 0이면 오류 메시지 작성
	}

}
