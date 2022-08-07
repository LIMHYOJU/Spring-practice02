package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import springTest.ChangePasswordSerive;
import springTest.MemberInfoPrinter;
import springTest.MemberListPrinter;
import springTest.MemberNotFoundException;
import springTest.MemberRegisterService;
import springTest.RegisterRequest;
import springTest.WrongIdPasswordException;

public class MainforSpring {
	
	private static AnnotationConfigApplicationContext ctx= null;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("명령어를 입력 해주세요");
			String command = reader.readLine();
			if (command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다");
			}
			if (command.startsWith("new ")) {
				processNewCommand(command.split(" "));
				continue;
			}else if (command.startsWith("change ")) {
				processChangeCommand(command.split(" "));
				continue;
			}else if (command.startsWith("list")) {
				processListCommand();
				continue;
			}else if (command.startsWith("info ")) {
				processInfoCommand(command.split(" "));
				continue;
			}
			printHelp();
		}
	}
	
	private static void processNewCommand(String[] split) {
		// TODO Auto-generated method stub
		if (split.length != 5) {
			printHelp();
			return;
		}
		MemberRegisterService regSvc = ctx.getBean("memberRegSvc",MemberRegisterService.class);
		RegisterRequest req = new RegisterRequest();
		req.setEmail(split[1]);
		req.setName(split[2]);
		req.setPassword(split[3]);
		req.setConfirmpassword(split[4]);
		if (!req.isPasswordEqualToConfirmPassword()) {
			System.out.println("암호가 일치하지 않습니다.");
			return;
		}
		try {
			regSvc.regist(req);
			System.out.println("등록 완료");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("이미 존재하는 이메일입니다.\n");
		}
	}
	
	private static void processChangeCommand(String[] split) {
		// TODO Auto-generated method stub
		if (split.length != 4) {
			printHelp();
			return;
		}
		ChangePasswordSerive changepwdSvc = ctx.getBean("changePwdSvc",ChangePasswordSerive.class);

		try {
			changepwdSvc.changePassword(split[1], split[2], split[3]);
			System.out.println("변경 완료");
		} catch (MemberNotFoundException e) {
			// TODO: handle exception
			System.out.println("존재하지않는 이메일");
		} catch (WrongIdPasswordException e) {
			// TODO: handle exception
			System.out.println("이메일과 암호가 불일치");
		}
	}

	private static void printHelp() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("잘못된 명령입니다 아래 명령어를 입력해주세요");
		System.out.println("명령어 사용법");
		System.out.println("new 이메일 이름 암호 암호확인");
		System.out.println("change 이메일 이름 현비번 구비번");
		System.out.println();
	}
	
	private static void processListCommand() {
		// TODO Auto-generated method stub
		MemberListPrinter listPrinter= ctx.getBean("listPrinter",MemberListPrinter.class);
		listPrinter.printAll();
	}

	private static void processInfoCommand(String[] split) {
		// TODO Auto-generated method stub
		if (split.length != 2) {
			printHelp();
			return;
		}
		MemberInfoPrinter infoPrinter = ctx.getBean("infoPrinter",MemberInfoPrinter.class);
		infoPrinter.printMemberinfo(split[1]);
	}

}
