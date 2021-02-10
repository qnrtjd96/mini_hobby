<<<<<<< HEAD
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
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
=======
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
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
>>>>>>> refs/remotes/origin/master
