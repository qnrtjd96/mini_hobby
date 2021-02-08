package dbConnection;

public class BoardVO {
	private int class_num;
	private String id;
	private String classname;
	private String cate;
	private String review;
	private String city;
	private int cost;
	private String intro;
	private String career;
	private String area;
	private String writedate;
	
	public BoardVO() {
		
	}
	
	public BoardVO(int class_num, String id, String classname, String cate, String review,
			String city, int cost, String intro, String career, String area, String writedate) {
		this.class_num = class_num;
		this.id=id;
		this.classname=classname;
		this.cate=cate;
		this.review=review;
		this.city=city;
		this.cost=cost;
		this.intro=intro;
		this.career=career;
		this.area=area;
		this.writedate=writedate;
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

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
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
