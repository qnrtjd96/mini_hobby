package dbConnection;

public class Stu_ClassVO {
	private int class_num;
	private String id;
	private String pay_class;
	private String pay_cate;

	private String sName;
	private int pay;
	private String pay_date;
	private String classdate;
	private String classtime;
	private String teach_id; //BoardTbl 선생님 아이디 조인
	private String area; //BoardTbl 상세지역 조인
	
	public Stu_ClassVO() {
		
	}
	public Stu_ClassVO(String pay_cate, String sName, int pay) {
		this.pay_cate = pay_cate;
		this.sName = sName;
		this.pay = pay;
	}
	public Stu_ClassVO(int class_num, String id, String pay_class, String pay_cate, int pay, String pay_date) {
		this.class_num=class_num;
		this.id=id;
		this.pay_class=pay_class;
		this.pay_cate=pay_cate;
		this.pay=pay;
		this.pay_date=pay_date;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getsName() {
		return sName;
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

	public String getPay_class() {
		return pay_class;
	}

	public void setPay_class(String pay_class) {
		this.pay_class = pay_class;
	}

	public String getPay_cate() {
		return pay_cate;
	}

	public void setPay_cate(String pay_cate) {
		this.pay_cate = pay_cate;
	}

	public int getPay() {
		return pay;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}

	public String getPay_date() {
		return pay_date;
	}

	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}
	public String getTeach_id() {
		return teach_id;
	}
	public void setTeach_id(String teach_id) {
		this.teach_id = teach_id;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}

	

}
