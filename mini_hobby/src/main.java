

public class main{

	public main() {
		if(true/*로그인정보가 학생이면*/) {
			//학생 5~9 페이지 메뉴(2021.01.31 이영준)
			//new StudenTopMenu(); //상단바 테스트
			//new AdminMembership(); //회원가입 테스트
			new StudenCateList();
		}//else if(true/*로그인정보가 강사이면*/){
//			//강사 28~33페이지 메뉴(2021.01.31 이영준)
			//new TeachTopMenu();
//		}
	}

	public static void main(String[] args) {
		new main();
	}

}
