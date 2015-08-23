package com.bank.control;

//import java.io.BufferedReader;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bank.operator.OptRecord;
import com.bank.service.EnterpriseAccSer;
import com.bank.service.IAccountSer;
import com.bank.service.RegularAccSer;
import com.bank.service.VIPAccSer;

public class BankManager {
	public static Object getBean(String beanName) {
		ApplicationContext context = 
			new ClassPathXmlApplicationContext("/ApplicationContext.xml");
		return context.getBean(beanName);
	}
	
	public static void main(String[] args) throws IOException {
		
		Scanner scan = new Scanner( System.in );
		String print = null;
		int i=0;
		while(i != 4) {
			ScreenPrinter.printAccSelection();
			IAccountSer accser = null;
			i = scan.nextInt();
			switch(i) {
			case 1:
				accser = (EnterpriseAccSer) getBean("EnterpriseAccSer");
				print = new String("You have choose to process a EnterpriseAcc.");
				break;
			case 2:
				accser = (VIPAccSer) getBean("VIPAccSer");
				print = new String("You have choose to process a VIPAcc.");
				break;
			case 3:
				accser = (RegularAccSer) getBean("RegularAccSer");
				print = new String("You have choose to process a RegularAcc.");
				break;
			case 4:
				continue;
			}
			if(accser != null) {
				System.out.println(print);
				OptAccount(accser, scan);
			}
			else
				System.out.println("Error input, please try again.\n");
		}
	}
	public static String[] split(String inputline) {
		return inputline.split(" +");
	}
	
	public static void OptAccount(IAccountSer accser, Scanner scan) throws NumberFormatException, IOException{
		int k=-1;
		OptRecord record = null;
		ScreenPrinter.printOptSelection();
		while(k != 9) {
			k = scan.nextInt();
			record = new OptRecord();
			switch(k) {
			case 1:
				try {
					ScreenPrinter.printRgtAccTips();
					record = accser.createAccount(
							scan.nextLong(), 
							scan.next().charAt(0), 
							scan.nextDouble(), 
							scan.next(),
							scan.next());
				} catch (Exception e) {
					//e.printStackTrace();
					System.out.println("Error Input Format or Args Nums.");
					scan.nextLine();
				}
				if(record.output() != null)
					System.out.println(record.output());
				break;
			case 2:
				try {
					ScreenPrinter.printDepositTips();
					record = accser.deposit(
							scan.nextLong(),
							scan.next(),
							scan.nextDouble());
				} catch (Exception e) {
					System.out.println("Error Input Format or Args Nums.");
					scan.nextLine();
				}
				if(record.output() != null)
					System.out.println(record.output());
				break;
			case 3:
				try {
					ScreenPrinter.printWithdrawTips();
					record = accser.withdraw(
							scan.nextLong(),
							scan.next(),
							scan.nextDouble());
				} catch (Exception e) {
					System.out.println("Error Input Format or Args Nums.");
					scan.nextLine();
				}
				if(record.output() != null)
					System.out.println(record.output());
				break;
			case 4:
				try {
					ScreenPrinter.printCheckBalTips();
					record = accser.checkbalance(
							scan.nextLong(),
							scan.nextLong(),
							scan.next());
				} catch (Exception e) {
					System.out.println("Error Input Format or Args Nums.");
					scan.nextLine();
				}
				if(record.output() != null)
					System.out.println(record.output());
				break;
			case 5:
				try {
					ScreenPrinter.printTransferTips();
					record = accser.transfer(
							scan.nextLong(), 
							scan.nextLong(),
							scan.next(),
							scan.next(), 
							scan.nextLong(), 
							scan.next(), 
							scan.nextDouble());
				} catch (Exception e) {
					System.out.println("Error Input Format or Args Nums.");
					scan.nextLine();
				}
				if(record.output() != null)
					System.out.println(record.output());
				break;
			case 6:
				try {
					ScreenPrinter.printResetTips();
					record = accser.reset(
							scan.nextLong(),
							scan.nextLong(),
							scan.next(),
							scan.next());
				} catch (Exception e) {
					System.out.println("Error Input Format or Args Nums.");
					scan.nextLine();
				}
				if(record.output() != null)
					System.out.println(record.output());
				break;
			case 7:
				try {
					ScreenPrinter.printDestoryTips();
					record = accser.destory(
							scan.nextLong(),
							scan.nextLong(),
							scan.next());
				} catch (Exception e) {
					System.out.println("Error Input Format or Args Nums.");
					scan.nextLine();
				}
				if(record.output() != null)
					System.out.println(record.output());
				break;
			case 8:
				try {
					ScreenPrinter.printAddOptTips();
					record = ((EnterpriseAccSer)accser)
						.addAccOperator(
							scan.nextLong(),
							//scan.nextLong(),
							scan.next(),
							scan.nextLong(),
							scan.next(),
							scan.next());
				} catch (Exception e) {
					System.out.println("Error Input Format or Args Nums.");
					scan.nextLine();
				}
				if(record.output() != null)
					System.out.println(record.output());
				break;
			case 9:
				System.out.println("Go back.");
				continue;
				//break;
			case 0:
			default:
				ScreenPrinter.printOptSelection();
				break;				
			}
			System.out.print("> ");
		}
		
	}
}
