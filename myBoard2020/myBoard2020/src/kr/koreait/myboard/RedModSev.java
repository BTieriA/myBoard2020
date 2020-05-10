package kr.koreait.myboard;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.koreait.myboard.db.BoardDAO;
import kr.koreait.myboard.vo.BoardVO;
import kr.koreait.myboard.vo.UserVO;

/**
 * Servlet implementation class RedModSev
 */
@WebServlet("/regmod")
public class RedModSev extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/* 화면 띄우는 용도 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 세션에 값 세팅 */
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute("loginUser");
		if(loginUser == null) {
		response.sendRedirect("/login");
		return;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/regmod.jsp");
		rd.forward(request, response);
	}

	/* 작업 용도(insert, update) */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		UserVO loginUser =(UserVO)hs.getAttribute("loginUser");
		
		int i_user = loginUser.getI_user();
			// jsp에서 user값 가져오면 보안위험이 있다, 서버에서 user값을 불러오기 위해 서블릿에서 불러온다
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		System.out.println("i_user : " + i_user);
		System.out.println("title : " + title);
		System.out.println("content : " + content);
		
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setI_user(i_user);
	
		int result = BoardDAO.boardInsert(vo);
		
		response.sendRedirect("/boardList");
		
	}

}
