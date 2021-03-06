package dbConnection;

public class BlackListVO {
	
	private String id;
	private String why;
	private String black_date;
	private int sort; // memberVO 연동
	
	public BlackListVO() {
		
	}
	
	public BlackListVO(String id, String why, String black_date, int sort) {
		this.id = id;
		this.why = why;
		this.black_date = black_date;
		this.sort = sort;
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

	public String getWhy() {
		return why;
	}

	public void setWhy(String why) {
		this.why = why;
	}

	public String getBlack_date() {
		return black_date;
	}

	public void setBlack_date(String black_date) {
		this.black_date = black_date;
	}
	
}
