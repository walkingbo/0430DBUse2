package dao;

import java.util.List;

import domain.Item;

//repository - 저장소
//저장소에 접근하는 인스턴스 : Data Access Object
public interface ItemDAO {
	//item 테이블의 전체 데이터를 가져오는 메소드
	//리턴 타입은 배열이나 List
	//Set 도 가능하지만 Set은 순서를 알 수 없어서 출력할 때 정렬을 다시 해야 합니다.
	//배열도 개수를 미리 알아야 해서 번거롭기 때문에 잘 사용안함
	public List<Item> allItems();
	
	//code 중복 검사를 수행해주는 메소드
	//리턴을 만들 때는 기본형 보다는 참조형으로 만드는 것이 좋을 때가 많습니다.
	//참조형은 null을 리턴해서 없다는 것을 나타낼 수 있기 때문입니다.
	
	public Integer codeCheck(int code);
	
	//데이터 삽입을 위한 메소드
	public int insertItem(Item item);
	
	//상세보기를 위한 메소드
	public Item detailItem(int code);
	
	//데이터 수정을 위한 메소드
	public int updateItem(Item item);
	
	//데이터 삭제를 위한 메소드
	public int deleteItem(int code);
}
