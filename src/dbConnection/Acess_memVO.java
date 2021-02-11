package dbConnection;

public class Acess_memVO {
	private int sort;
	private String id;
	private String name;
	public Acess_memVO() {
	}
	public Acess_memVO(int sort, String id, String name) {
		this.sort = sort;
		this.id = id;
		this.name = name;
	}
	//2021.02.11 이강산
	//실시간채팅
	public Acess_memVO(String id, String name) {
		this.id=id;
		this.name=name;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
