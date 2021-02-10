package dbConnection;

public class MemoVO {
	private String memo_date;
	private String memo_detail;
	private String id;

	public MemoVO() {
		
	}
	public MemoVO(String memo_date, String memo_detail, String id) {
		this.memo_date = memo_date;
		this.memo_detail = memo_detail;
		this.id=id;
	}

	public String getMemo_date() {
		return memo_date;
	}

	public void setMemo_date(String memo_date) {
		this.memo_date = memo_date;
	}

	public String getMemo_detail() {
		return memo_detail;
	}

	public void setMemo_detail(String memo_detail) {
		this.memo_detail = memo_detail;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
