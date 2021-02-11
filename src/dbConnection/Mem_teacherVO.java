package dbConnection;

public class Mem_teacherVO {
	private int career_year;
	private String cate;
	private String id;
	//학생 클래스 검색(studenCateList.java)
	private String className;
	private String city;
	private int cost;
	private String tName;
	private String career;
	//선생님 회원 정보 조회용
	private String tPwd;
	private String tBirth;
	private String tMail;
	private String tTel;
	private String tAddr;
	
	public Mem_teacherVO() {
	}
	public Mem_teacherVO(int career_year, String cate, String id) {
		this.career_year = career_year;
		this.cate = cate;
		this.id = id;
	}
	//학생 클래스 검색(studenCateList.java)
	//클래스명, 지역, 가격, 강사명
	public Mem_teacherVO(String className, String city, int cost, String tName) {
		this.className = className;
		this.city = city;
		this.cost = cost;
		this.tName = tName;
	}
	//강사 클래스 검색(TeachCateList.java)
	//클래스명, 지역, 강사명, 경력사항
	public Mem_teacherVO(String className, String city, String career, String tName) {
		this.className = className;
		this.city = city;
		this.career = career;
		this.tName = tName;
	}
	//강사 회원정보 수정용(TeachInfo) 2021.02.11
	//id, pwd, name, birth, email, tel, addr, cate, year
	public Mem_teacherVO(String id, String tPwd, String tBirth, String tMail, String tTel, String tAddr, String cate, int career_year) {
		this.id = id;
		this.tPwd = tPwd;
		this.tBirth = tBirth;
		this.tMail = tMail;
		this.tTel = tTel;
		this.tAddr = tAddr;
		this.cate = cate;
		this.career_year = career_year;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}
	public int getCareer_year() {
		return career_year;
	}
	public void setCareer_year(int career_year) {
		this.career_year = career_year;
	}
	public String getCate() {
		return cate;
	}
	public void setCate(String cate) {
		this.cate = cate;
		System.out.println("음악 카테고리 찍혔는지 확인 "+cate);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	//선생님 회원정보 수정용 
	public String gettPwd() {
		return tPwd;
	}
	public void settPwd(String tPwd) {
		this.tPwd = tPwd;
	}
	public String gettBirth() {
		return tBirth;
	}
	public void settBirth(String tBirth) {
		this.tBirth = tBirth;
	}
	public String gettMail() {
		return tMail;
	}
	public void settMail(String tMail) {
		this.tMail = tMail;
	}
	public String gettTel() {
		return tTel;
	}
	public void settTel(String tTel) {
		this.tTel = tTel;
	}
	public String gettAddr() {
		return tAddr;
	}
	public void settAddr(String tAddr) {
		this.tAddr = tAddr;
	}

}
