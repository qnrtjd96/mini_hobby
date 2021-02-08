package dbConnection;

public class Mem_teacherVO {
	private int career_year;
	private String cate;
	private String id;
	public Mem_teacherVO() {
	}
	public Mem_teacherVO(int career_year, String cate, String id) {
		this.career_year = career_year;
		this.cate = cate;
		this.id = id;
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
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
