package dbConnection;

public class MemberVO {
	private String id;
	private String pwd;
	private String name;
	private String birth;
	private String mail;
	private String tel;
	private String addr;
	private String login_date;
	private int sort;
	private int payCnt;
	private String cate;

	public MemberVO() {
		
	}
	//2021-02-08 이강산
	//관리자 회원출력중 선생님출력부분 : AdminMemberList
	public MemberVO(String id, String name, String cate) {
		this.id = id;
		this.name = name;
		this.cate = cate;
	}

	//2021-02-09 이강산
	//관리자 휴먼계정조회중 필요한 테이블
	public MemberVO(String id, int sort, String login_date) {
		this.id=id;
		this.sort = sort;
		this.login_date = login_date;
	}
	public MemberVO(String mail) {
		this.mail = mail;
	}
	public MemberVO(String id, String mail) {
		this.id = id;
		this.mail = mail;
	}
	
	public MemberVO(String id, String name, int payCnt) {
		this.id = id;
		this.name = name;
		this.payCnt = payCnt;
	}
	public int getPayCnt() {
		return payCnt;
	}
	public void setPayCnt(int payCnt) {
		this.payCnt = payCnt;
	}
	public MemberVO(String id, String pwd, String name, String birth, String mail, String tel, String addr, int sort) {
		this(id, mail);
		this.pwd = pwd;
		this.name = name;
		this.birth = birth;
		this.tel = tel;
		this.addr = addr;
		this.sort = sort;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getBirth() {
		return birth;
	}


	public void setBirth(String birth) {
		this.birth = birth;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getAddr() {
		return addr;
	}


	public void setAddr(String addr) {
		this.addr = addr;
	}


	public String getLogin_date() {
		return login_date;
	}


	public void setLogin_date(String login_date) {
		this.login_date = login_date;
	}


	public int getSort() {
		return sort;
	}


	public void setSort(int sort) {
		this.sort = sort;
	}
	
	public String getCate() {
		return cate;
	}
	public void setCate(String cate) {
		this.cate = cate;
	}
	
	
}
