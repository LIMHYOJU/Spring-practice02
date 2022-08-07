package springTest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dao.MemberDao;

@Component("listPrinter")
public class MemberListPrinter {
	
	@Autowired
	private MemberDao memberDao;
	private MemberPrinter printer;
	
	public void printAll() {
		// TODO Auto-generated constructor stub
		Collection<Member> members = memberDao.selectAll();
		members.forEach(m -> printer.print(m));
	}

	@Autowired
	@Qualifier("summaryPrinter")
	public void setMemberPrinter(MemberSummaryPrinter printer) {
		this.printer = printer;
	}
	
	
}
