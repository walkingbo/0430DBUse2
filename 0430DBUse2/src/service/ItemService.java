package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import domain.Item;

//서비스 메소드의 원형은 대부분 DAO의 메소드의 return 과 동일하고
//매개변수는 파라미터를 읽을 수 있는 Request 객체
public interface ItemService {
	//전체 가져오기를 수행할 메소드
	public List<Item> allItems(HttpServletRequest request);
	//code 중복 검사를 위한 메소드
	public Integer codeCheck(HttpServletRequest request);
}
