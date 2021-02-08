package dbConnection;

public class ConsVO {
	private int msg_num;
	private String get;
	private String send;
	private String msg_title;
	private String msg_detail;
	
	public ConsVO() {
	}
	public ConsVO(int msg_num, String get, String send, String msg_title, String msg_detail) {
		this.msg_num = msg_num;
		this.get = get;
		this.send = send;
		this.msg_title = msg_title;
		this.msg_detail = msg_detail;
	}
	public int getMsg_num() {
		return msg_num;
	}
	public void setMsg_num(int msg_num) {
		this.msg_num = msg_num;
	}
	public String getGet() {
		return get;
	}
	public void setGet(String get) {
		this.get = get;
	}
	public String getSend() {
		return send;
	}
	public void setSend(String send) {
		this.send = send;
	}
	public String getMsg_title() {
		return msg_title;
	}
	public void setMsg_title(String msg_title) {
		this.msg_title = msg_title;
	}
	public String getMsg_detail() {
		return msg_detail;
	}
	public void setMsg_detail(String msg_detail) {
		this.msg_detail = msg_detail;
	}
	
}
