<<<<<<< HEAD
<<<<<<< HEAD
package dbConnection;

public class ConsVO {
	private int msg_num;
	private String get;
	private String send;
	private String msg_title;
	private String msg_detail;
	private String send_time;
	private int sort;

	public ConsVO() {
	}
	public ConsVO(int msg_num, String get, String send, String msg_title, String msg_detail) {
		this.msg_num = msg_num;
		this.get = get;
		this.send = send;
		this.msg_title = msg_title;
		this.msg_detail = msg_detail;
	}
	//받은메세지 다이어로그
	public ConsVO(int msg_num, String get, String send, String msg_title, String msg_detail, String send_time) {
		this.msg_num = msg_num;
		this.get = get;
		this.send = send;
		this.msg_title = msg_title;
		this.msg_detail = msg_detail;
		this.send_time = send_time;
	}
	//관리자 받은메세지
	public ConsVO(int msg_num, String send, int sort, String msg_title, String msg_detail, String send_time) {
		this.msg_num = msg_num;
		this.send = send;
		this.sort = sort;
		this.msg_title = msg_title;
		this.msg_detail = msg_detail;
		this.send_time = send_time;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getSend_time() {
		return send_time;
	}
	public void setSend_time(String send_time) {
		this.send_time = send_time;
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
=======
package dbConnection;

public class ConsVO {
	private int msg_num;
	private String get;
	private String send;
	private String msg_title;
	private String msg_detail;
	private String send_time;
	private int sort;

	public ConsVO() {
	}
	public ConsVO(int msg_num, String get, String send, String msg_title, String msg_detail) {
		this.msg_num = msg_num;
		this.get = get;
		this.send = send;
		this.msg_title = msg_title;
		this.msg_detail = msg_detail;
	}
	//받은메세지 다이어로그
	public ConsVO(int msg_num, String get, String send, String msg_title, String msg_detail, String send_time) {
		this.msg_num = msg_num;
		this.get = get;
		this.send = send;
		this.msg_title = msg_title;
		this.msg_detail = msg_detail;
		this.send_time = send_time;
	}
	//관리자 받은메세지
	public ConsVO(int msg_num, String send, int sort, String msg_title, String msg_detail, String send_time) {
		this.msg_num = msg_num;
		this.send = send;
		this.sort = sort;
		this.msg_title = msg_title;
		this.msg_detail = msg_detail;
		this.send_time = send_time;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getSend_time() {
		return send_time;
	}
	public void setSend_time(String send_time) {
		this.send_time = send_time;
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
>>>>>>> refs/remotes/origin/master
=======
package dbConnection;

public class ConsVO {
	private int msg_num;
	private String get;
	private String send;
	private String msg_title;
	private String msg_detail;
	private String send_time;
	private int sort;

	public ConsVO() {
	}
	public ConsVO(int msg_num, String get, String send, String msg_title, String msg_detail) {
		this.msg_num = msg_num;
		this.get = get;
		this.send = send;
		this.msg_title = msg_title;
		this.msg_detail = msg_detail;
	}
	//받은메세지 다이어로그
	public ConsVO(int msg_num, String get, String send, String msg_title, String msg_detail, String send_time) {
		this.msg_num = msg_num;
		this.get = get;
		this.send = send;
		this.msg_title = msg_title;
		this.msg_detail = msg_detail;
		this.send_time = send_time;
	}
	//관리자 받은메세지
	public ConsVO(int msg_num, String send, int sort, String msg_title, String msg_detail, String send_time) {
		this.msg_num = msg_num;
		this.send = send;
		this.sort = sort;
		this.msg_title = msg_title;
		this.msg_detail = msg_detail;
		this.send_time = send_time;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getSend_time() {
		return send_time;
	}
	public void setSend_time(String send_time) {
		this.send_time = send_time;
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
>>>>>>> refs/remotes/origin/master
