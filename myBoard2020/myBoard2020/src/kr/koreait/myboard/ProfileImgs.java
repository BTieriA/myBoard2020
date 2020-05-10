package kr.koreait.myboard;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.koreait.myboard.db.UserDAO;
import kr.koreait.myboard.vo.UserVO;


@WebServlet("/profileImgs")
public class ProfileImgs extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//view
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO) hs.getAttribute("loginUser");
		// loginSev서블릿에 loginUser를 불러온다
		if (loginUser == null) {
			response.sendRedirect("/login");
			return;
		}
		
		request.setAttribute("imgList", UserDAO.getProfileImgList(loginUser.getI_user()));
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/profileImgs.jsp");
		rd.forward(request, response);
	}

	// 삭제
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
