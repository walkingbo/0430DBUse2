package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.ItemDAO;
import dao.ItemDAOImpl;
import domain.Item;

public class ItemServiceImpl implements ItemService {

	//DAO 객체를 저장하기 위한 변수
	private ItemDAO itemDAO;
	
	//싱글톤 패턴
	private ItemServiceImpl() {
		//인스턴스 자신의 인스턴스변수의 데이터를 직접 생성하지 않고
		//외부에서 생성한 데이터를 대입하는 것을 DI(Dependency Injection)
		//라고 합니다.
		//만드는 것에 신경쓰지 않고 사용하는 부분에만 집중
		//비지니스 로직만 생성하면 
		itemDAO = ItemDAOImpl.getInstance();
	}
	//클라이언트나 다른 개발자들은 구현될 코드를 볼 필요가 없기 때문에
	//메소드 모양만 보면 되므로 변수는 인터페이스 타입으로 선언
	private static ItemService itemService;
	
	public static ItemService getInstance() {
		if(itemService == null) {
			itemService = new ItemServiceImpl();
		}
		return itemService;
	}
	
	@Override
	public List<Item> allItems(HttpServletRequest request) {
		List<Item> list = new ArrayList<Item>();
		
		//파라미터 읽기
		
		//파라미터를 DAO 메소드의 매개변수에 맞게 만들기
		
		//DAO의 메소드를 호출해서 결과를 저장
		list = itemDAO.allItems();
		
		//결과 리턴
		return list;
	}

	@Override
	public Integer codeCheck(HttpServletRequest request) {
		Integer result = null;
		
		//파라미터 읽기
		String code =request.getParameter("code");
		
		//파라미터를 가지고 DAO  매개변수 만들기
		int c = Integer.parseInt(code);
		
		//DAO 메소드를 호출해서 결과를 저장
		result = itemDAO.codeCheck(c);
		
		return result;
	}

	@Override
	public int insertItem(HttpServletRequest request) {
		int result = -1;
		//파라미터 읽기
		String code = request.getParameter("code");
		String title = request.getParameter("title");
		String category = request.getParameter("category");
		String description = request.getParameter("description");

		//DAO메소드의 매개변수를 만들기
		Item item = new Item();
		item.setCode(Integer.parseInt(code));
		item.setTitle(title);
		item.setCategory(category);
		item.setDescription(description);
		
		//DAO메소드를 호출
		result=itemDAO.insertItem(item);
		return result;
	}

	@Override
	public Item detailItem(HttpServletRequest request) {
		Item item = null;
		//파라미터 읽기
		String code = request.getParameter("code");
		//DAO 메소드의 파라미터로 만들기
		//숫자로 변경할 때 NumberFormatException이 발생하면
		//파라미터를 만들 때 공백이나 잘못된 이름을 입력했는지 확인
		int c = Integer.parseInt(code.trim());
		//DAO메소드를 호출
		item = itemDAO.detailItem(c);
		
		return item;
	}

	@Override
	public int updateItem(HttpServletRequest request) {
		int result = -1;
		//파라미터 읽기
		String code = request.getParameter("code");
		String title = request.getParameter("title");
		String category = request.getParameter("category");
		String description = request.getParameter("description");

		//DAO메소드의 매개변수를 만들기
		Item item = new Item();
		item.setCode(Integer.parseInt(code));
		item.setTitle(title);
		item.setCategory(category);
		item.setDescription(description);
		
		//DAO메소드를 호출
		//삽입은 0 보다 커야 성공이지만 수정과 삭제는 0보다 크거나 같으면 성공
		//수정과 삭제는 where 절이 있기 때문에 구문 에러가 엇어도 작업을 하지 않을 수 도 있다.
		
		result=itemDAO.updateItem(item);
		return result;
	}

	@Override
	public int deleteItem(HttpServletRequest request) {
		int result = -1;
		
		String code = request.getParameter("code");
		
		int c = Integer.parseInt(code);
		
		result=itemDAO.deleteItem(c);
		
		return result;
	}
}















