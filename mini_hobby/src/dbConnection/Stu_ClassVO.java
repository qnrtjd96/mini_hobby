package dbConnection;

public class Stu_ClassVO {
	private int class_num;
	private String id;
	private String pay_class;
	private String pay_cate;
	private int pay;
	private String pay_date;
	
	public Stu_ClassVO() {
		
	}
	public Stu_ClassVO(int class_num, String id, String pay_class, String pay_cate, int pay, String pay_date) {
		this.class_num=class_num;
		this.id=id;
		this.pay_class=pay_class;
		this.pay_cate=pay_cate;
		this.pay=pay;
		this.pay_date=pay_date;
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

	

}
