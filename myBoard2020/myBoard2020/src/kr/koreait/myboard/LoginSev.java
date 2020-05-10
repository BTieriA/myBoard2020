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

@WebServlet("/login")
public class LoginSev extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		이 페이지를 로그인 시도 없이 왔는지
    	
//    	로그인 시도했다가 오류가 떠서 왔는지 ()
    	String error = request.getParameter("error");
//    		get은 쿼리스트링(xx?key=value&key=value)만 받을 수 있다. 키값이 오류가 없고 값이 없으면 null
    		// post에서 error 가져온다
    	if(error != null) { // 로그인 실패 했음
    		String errorMsg = "";
    		switch(error) {
    		case "0":
    			errorMsg = "알 수 없는 에러 발생";
    			break;
    		case "2":
    			errorMsg = "아이디를 확인해 주세요";
    			break;
    		case "3":
    			errorMsg = "비밀번호를 확인해 주세요";
    			break;
    		}
    		request.setAttribute("msg", errorMsg);
//    			errorMsg를 키값(msg)에 저장
    	}
    	
    	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		rd.forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String u_id = request.getParameter("u_id");
		String u_pw = request.getParameter("u_pw");
		
		System.out.println("u_id : " + u_id);
		System.out.println("u_pw : " + u_pw);
		
		UserVO param = new UserVO();
		// param에 주소값 입력
		param.setU_id(u_id);
		param.setU_pw(u_pw);
		
		int result = UserDAO.doLogin(param);
		if(result == 1) {
//			세션에 값세팅
			HttpSession hs = request.getSession();
			hs.setAttribute("loginUser", param);
			// session에 param의 값을 서버 저장
			
			response.sendRedirect("/boardList");
			return;
				// return을 넣어야 sendRedirect가 2번 실행 되지 않는다.
		}
		response.sendRedirect("/login?error=" + result);
//		sendRedirect 는 무조건 get방식으로 날려준다. 
		
		System.out.println("result : " + result);
	}

}
