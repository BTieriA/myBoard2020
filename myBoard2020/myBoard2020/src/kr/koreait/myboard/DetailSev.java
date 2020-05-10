package kr.koreait.myboard;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.koreait.myboard.db.BoardDAO;
import kr.koreait.myboard.vo.BoardVO;

/**
 * Servlet implementation class DetailSev
 */
@WebServlet("/boardDetail")
public class DetailSev extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		String i_board =  request.getParameter("i_board");
 		int intBoard = Integer.parseInt(i_board);
 		 
 		BoardVO param = new BoardVO();
		param.setI_board(intBoard);
					
		BoardDAO.updateBoard(param);
		request.setAttribute("detail", BoardDAO.getBoard(param));

 		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/detail.jsp");
 		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
