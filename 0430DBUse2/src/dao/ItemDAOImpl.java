package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.Item;
import util.GlobalApplication;

public class ItemDAOImpl implements ItemDAO {
	//데이터베이스 연결 객체 변수 - 데이터베이스 프레임워크 변수로 변경
	private Connection con;
	
	//싱글톤 패턴
	private ItemDAOImpl() {
		//인스턴스 자신의 인스턴스변수의 데이터를 직접 생성하지 않고
		//외부에서 생성한 데이터를 대입하는 것을 DI(Dependency Injection)
		//라고 합니다.
		//만드는 것에 신경쓰지 않고 사용하는 부분에만 집중
		//비지니스 로직만 생성하면 
		con = GlobalApplication.shardInstance().con;
	}
	//클라이언트나 다른 개발자들은 구현될 코드를 볼 필요가 없기 때문에
	//메소드 모양만 보면 되므로 변수는 인터페이스 타입으로 선언
	private static ItemDAO itemDAO;
	
	public static ItemDAO getInstance() {
		if(itemDAO == null) {
			itemDAO = new ItemDAOImpl();
		}
		return itemDAO;
	}

	//전체 데이터 가져오기
	@Override
	public List<Item> allItems() {
		List<Item> list = new ArrayList<Item>();
		//SQL 실행 객체를 저장할 변수
		PreparedStatement pstmt = null;
		//Select 구문의 결과를 저장할 변수
		ResultSet rs = null;
		try {
			//item 테이블의 전체 데이터를 가져오는 SQL 실행
			pstmt = con.prepareStatement(
				"select code, title, category, description from item");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Item item = new Item();
				item.setCode(rs.getInt("code"));
				item.setTitle(rs.getString("title"));
				item.setCategory(rs.getString("category"));
				item.setDescription(rs.getString("description"));
				list.add(item);
			}
		}catch(Exception e) {
			//예외 처리를 할 때는 메시지 출력
			//학습을 위해서 라면 예외를 파일에 기록
			System.out.println("DAO 전체 가져오기:" +e.getMessage());
			//예외를 역 추적
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
			}catch(Exception e) {}
		}
		return list;
	}
	
	//1개를 리턴해야 하는 경우는 리턴할 데이터를 null로 초기화 해서 리턴
	@Override
	public Integer codeCheck(int code) {
		Integer result = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//매개변수로 받은 데이터의 존재 여부를 확인한기 위한 sql 실행
			pstmt = con.prepareStatement("select code from item where code=?");
			pstmt.setInt(1, code);
			rs=pstmt.executeQuery();
			//code 가 있으면 그 값을 result에 저장
			if(rs.next()) {
				result = rs.getInt("code");
			}
		}catch(Exception e) {
			System.out.println("중복검사 예외:"+e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
			}catch(Exception e) {
				
			}
		}
		return result;
	}

	@Override
	public int insertItem(Item item) {
		int result = -1;
		PreparedStatement pstmt = null;
	
		try {
			//입력된 값을 대입할 때는 ?로 설정한다.
			pstmt=con.prepareStatement("insert into item(code,title,category,description) values(?,?,?,?)");
			//?에 데이터를 바인딩
			pstmt.setInt(1, item.getCode());
			pstmt.setString(2, item.getTitle());
			pstmt.setString(3, item.getCategory());
			pstmt.setString(4, item.getDescription());

			//실행
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("중복검사 예외:"+e.getMessage());
			e.printStackTrace();
		}finally {
			try {
			
				pstmt.close();
			}catch(Exception e) {
				
			}
		}
		return result;
	}
	
	//상세보기처럼 하나의 데이터를 찾아오는 메소드는 리턴할 데이터를 null로 초기화 하고 처리
	
	@Override
	public Item detailItem(int code) {
		Item item = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt=con.prepareStatement("select code,title,category,description from item where code=?");
			pstmt.setInt(1, code);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				item = new Item();
				item.setCode(rs.getInt("code"));
				item.setTitle(rs.getString("title"));
				item.setCategory(rs.getString("category"));
				item.setDescription(rs.getString("description"));
			}
			
		}catch(Exception e) {
			System.out.println("중복검사 예외:"+e.getMessage());
			e.printStackTrace();
		}finally {
			try {
		
			}catch(Exception e) {
				
			}
		}
		return item;
	}

	@Override
	public int updateItem(Item item) {
		int result = -1;
		PreparedStatement pstmt = null;
	
		try {
			//code를 가지고 title, category, description을 수정
			pstmt = con.prepareStatement("update item set title=?,category=?,description=? where code=?");
			//입력받은 파라미터를 SQL 문자에 바인딩
			pstmt.setString(1, item.getTitle());
			pstmt.setString(2, item.getCategory());
			pstmt.setString(3, item.getDescription());
			pstmt.setInt(4, item.getCode());
			//SQL을 실행
			result=pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("데이터 수정 예외:"+e.getMessage());
			e.printStackTrace();
		}finally {
			try {
			
				pstmt.close();
			}catch(Exception e) {
				
			}
		}
		return result;
	}

	@Override
	public int deleteItem(int code) {
		int result = -1;
		PreparedStatement pstmt = null;
	
		try {
			pstmt = con.prepareStatement("delete from item where code=?");
			pstmt.setInt(1, code);
			result=pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("데이터 수정 예외:"+e.getMessage());
			e.printStackTrace();
		}finally {
			try {
			
				pstmt.close();
			}catch(Exception e) {
				
			}
		}
		return result;
	}
}






