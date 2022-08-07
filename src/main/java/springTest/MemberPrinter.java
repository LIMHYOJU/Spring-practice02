package springTest;

public class MemberPrinter {
	public void print(Member member) {
		System.out.println("회원정보: ID"+member.getId()+"email"+member.getEmail()+"이름"+member.getName()+"등록일"+member.getRegisterDateTime()+"F\n");
		
	}

}
