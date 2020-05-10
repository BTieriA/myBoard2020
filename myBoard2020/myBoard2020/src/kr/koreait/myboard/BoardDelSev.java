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
 * Servlet implementation class BoardDelSev
 */
@WebServlet("/boardDel")
public class BoardDelSev extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute("loginUser");
		// 다른 유저들은 관여 못하게하기 위해
		// session 로그인을 유지하면서 서버에서 키값을 가져온다
		if(loginUser == null) {
			response.sendRedirect("/login");
			return;
		}

		BoardVO param = new BoardVO();
		
		String i_board = request.getParameter("i_board");
		int intBoard = Utils.parseStringtoInt(i_board, 0);
		// 문자가 섞여 있다면 0을 넣겠다 -> 해커가 문자 넣어서 주소 방해하는 것을 막기 위해
		System.out.println("intBoard : " + intBoard);
		if(intBoard == 0) { // 문제 발생 (i_board에 문자가 섞여 있을 때)
			return;
		}
		
		param.setI_board(intBoard);
		param.setI_user(loginUser.getI_user());
		
		int result = BoardDAO.boardDel(param);
		
		if(result == 0 ) { // 문제발생 (내가쓴글이 아닐때)
			return;
		}
		
		response.sendRedirect("/boardList");
	}


}
