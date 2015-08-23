package com.bank;

import com.bank.control.ScreenPrinter;;

public class TestClass {
	public static void main(String[] args) {
		System.out.print(
				"/**************************************/\n"+
				"/**                                  **/\n"+
				"/** Welcome to Bank Management System**/\n"+
				"/**                                  **/\n"+
				"/**************************************/\n"+
				" Please enter your ID & password.       \n"+
				"> "
		);
		System.out.print("admin 123456\n\n");
		System.out.println("<!-- You succeed in Login to the System.");
		ScreenPrinter.printAccSelection();
		System.out.print("3\n");
		System.out.println("<!-- You choose to process the Regular Account.  ");
		ScreenPrinter.printOptSelection();
		System.out.print("1\n");
		ScreenPrinter.printRgtAccTips();
	}
}
