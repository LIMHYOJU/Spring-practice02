package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import dao.MemberDao;
import springTest.ChangePasswordSerive;
import springTest.MemberInfoPrinter;
import springTest.MemberListPrinter;
import springTest.MemberPrinter;
import springTest.MemberRegisterService;
import springTest.MemberSummaryPrinter;

@Configuration
@ComponentScan(basePackages= {"springTest","dao"},
excludeFilters = @Filter(type= FilterType.ASSIGNABLE_TYPE,pattern="dao.*Dao"))
public class AppCtx {
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean 
	@Qualifier("printer")
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
	
	@Bean 
	@Qualifier("summaryPrinter")
	public MemberSummaryPrinter memberPrinter2() {
		return new MemberSummaryPrinter();
	}

}
