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
 * Servlet implementation class BoardListSev
 */
@WebServlet("/boardList")
public class BoardListSev extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
//			로그인 상태 유지하기 위해 session 객체만들어서 사용
		UserVO loginUser = (UserVO)hs.getAttribute("loginUser");
		if(loginUser == null) {
			response.sendRedirect("/login");
			// 로그인 안됐으면 login으로 가라
			return;
		}
		
		String search = request.getParameter("search");
			//getParameter : 유저가 search 값을 요청
		
		int page = 1;
		String strPage = request.getParameter("page");
		if (strPage != null) {
			page = Integer.parseInt(strPage);
		}
		int cnt = 10; //content view count 
		int sIdx = (page - 1) * cnt;
		
		BoardVO param = new BoardVO();
		param.setsIdx(sIdx);
		param.setRowCnt(cnt);
		param.setSearch(search);
		
		// DB로 부터 리스트를 가져온다
		
		request.setAttribute("page", page);
		request.setAttribute("totalPageCnt", BoardDAO.getTotalPageCnt(param));
		request.setAttribute("list", BoardDAO.getBoardList(param));	
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/boardlist.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

}
