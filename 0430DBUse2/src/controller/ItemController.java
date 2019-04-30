package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.Item;
import service.ItemService;
import service.ItemServiceImpl;

//서블릿은 객체를 웹 컨테이너가 만들고 관리 
//서블릿은 객체를 만들 때 웹 컨테이너가 싱글톤으로 만들기 때문에
//싱글톤으로 만드는 코드를 작성할 필요가 없습니다.
@WebServlet("*.do")
public class ItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Service 객체를 저장하기 위한 변수
	private ItemService itemService;
       
    public ItemController() {
        super();
        itemService = ItemServiceImpl.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청의 다른 부분만 가져오기 - 알고리즘 상 중요
		//프레임워크는 이 부분의 작업도 수행
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		//requestURI에서 contextPath를 제외한 부분 가져오기
		String command = 
			requestURI.substring(contextPath.length() + 1);
		//구별되는 요청을 가지고 분기문 만들기
		switch(command) {
		case "list.do":
			//서비스의 메소드 호출
			List<Item> list = itemService.allItems(request);
			//결과를 저장해서 결과 페이지로 이동
			//데이터를 조회할 때는 forwarding
			//그 이외의 작업일 때는 redirect
			request.setAttribute("list", list);
			//포워딩
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("list.jsp");
			dispatcher.forward(request, response);
			break;
			
		case "insert.do":
			//데이터 입력 페이지로 포워딩
			RequestDispatcher dispatcher1 = 
					request.getRequestDispatcher("insert.jsp");
			dispatcher1.forward(request, response);
			break;
			
		case "codecheck.do":
			//서비스의 메소드 호출 	
			Integer result = itemService.codeCheck(request);
			//JSON 객체를 만들어서 결과를 저장
			JSONObject object = new JSONObject();
			//데이터가 중복되지 않은 경우
			//result라는 키에 중복되지 않은 경우 success를 실패한 경우에는 fail을 저장
			if(result==null) {
				object.put("result", "success");
			}
			//데이터가 중복된 경우
			else {
				object.put("result", "fail");
			}
			//만들어낸 결과를 저장
			request.setAttribute("result", object);
			//결과를 출력할 페이지로 포워딩
			RequestDispatcher dispatcher2 =
					request.getRequestDispatcher("codecheck.jsp");
			dispatcher2.forward(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}









