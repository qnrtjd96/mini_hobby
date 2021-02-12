package dbConnection;

public class BoardVO {
	private int class_num;
	private String id;
	private String classname;
	private String name; //membertbl 강사이름 조인
	private String pay_date; // stu_class 결제일자 조인
	private String cate;
	private String city;
	private int cost;
	private String intro;
	private String career;
	private String area;
	private String writedate;
	private String classdate;
	private String classtime;
	public BoardVO() {
		
	}
	//선생님 새글쓰기
	//2021-02-10 이강산
	public BoardVO(String id, String classname, String cate, String city, int cost, String intro, String career, String area, String classdate, String classtime) {
		System.out.println();
		this.id=id;
		this.classname=classname;
		this.cate=cate;
		this.city=city;
		this.cost=cost;
		this.intro = intro;
		this.career=career;
		this.area=area;
		this.classdate = classdate;
		this.classtime=classtime;
	}
	// 관리자 결제 상세내역
	public BoardVO(int class_num, String id, String name, int cost, String pay_date) {
		this.class_num = class_num;
		this.id = id;
		this.name = name;
		this.cost = cost;
		this.pay_date = pay_date;
	}
	// 수정
	public BoardVO(int class_num, String id, String classname, String cate, String city,
			int cost, String intro, String career, String area) {
		this.class_num = class_num;
		this.id=id;
		this.classname=classname;
		this.cate=cate;
		this.city=city;
		this.cost=cost;
		this.intro=intro;
		this.career=career;
		this.area=area;
	}
	public BoardVO(int class_num, String id, String classname, String cate, String city,
			int cost, String intro, String career, String area, String writedate) {
		this.class_num = class_num;
		this.id=id;
		this.classname=classname;
		this.cate=cate;
		this.city=city;
		this.cost=cost;
		this.intro=intro;
		this.career=career;
		this.area=area;
		this.writedate=writedate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPay_date() {
		return pay_date;
	}
	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}
	public String getClassdate() {
		return classdate;
	}

	public void setClassdate(String classdate) {
		this.classdate = classdate;
	}

	public String getClasstime() {
		return classtime;
	}

	public void setClasstime(String classtime) {
		this.classtime = classtime;
	}

	public int getClass_num() {
		return class_num;
	}

	public void setClass_num(int class_num) {
		this.class_num = class_num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getCate() {
		return cate;
	}

	public void setCate(String cate) {
		this.cate = cate;
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

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getWritedate() {
		return writedate;
	}

	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}

	

}
