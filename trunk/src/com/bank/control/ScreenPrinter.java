package com.bank.control;

public class ScreenPrinter {
	public static void printAccSelection() {
		System.out.println("<--------please select account type-------->");
		System.out.println("<-------- 1 for Enterprise Account -------->");
		System.out.println("<-------- 2 for VIP Account        -------->");
		System.out.println("<-------- 3 for Regular Account    -------->");
		System.out.println("<-------- 4 for Exit the system    -------->");
		System.out.println("<-------- 0 & others for Help      -------->");
		System.out.print("> ");
	}
	public static void printOptSelection() {
		System.out.println("<--------please select account operation-------->");
		System.out.println("<-------- 1 for Registering Account     -------->");
		System.out.println("<-------- 2 for Depositing Amount       -------->");
		System.out.println("<-------- 3 for Withdrawing Amount      -------->");
		System.out.println("<-------- 4 for Checking Balance        -------->");
		System.out.println("<-------- 5 for Transfering Balance     -------->");
		System.out.println("<-------- 6 for Reseting Password       -------->");
		System.out.println("<-------- 7 for Destorying Account      -------->");
		System.out.println("<-------- 8 for Adding new operator(EntAcc)----->");
		System.out.println("<-------- 9 for Goto Account Seletion   -------->");
		System.out.println("<-------- 0 & others for Help           -------->");
		System.out.print("> ");
	}
	public static void printRgtAccTips() {
		System.out.println("<--------Register account action               -------->");
		System.out.println("<--------please input 5 args as following types-------->");
		System.out.println("<--------Id_Num/ Type/ Amount/ Passwrd/ Name   -------->");
		System.out.println("<--------Long/   Char/ Double/ String/  String -------->");
		System.out.print("> ");
	}
	public static void printDepositTips() {
		System.out.println("<--------Deposit balance action                -------->");
		System.out.println("<--------please input 3 args as following types-------->");
		System.out.println("<--------Acc_ID/ Passwrd/ Amount               -------->");
		System.out.println("<--------Long/   String/  Double               -------->");
		System.out.print("> ");
	}
	public static void printDestoryTips() {
		System.out.println("<--------Destory account action                -------->");
		System.out.println("<--------please input 3 args as following types-------->");
		System.out.println("<--------ID_Num/ Acc_ID/ Passwrd               -------->");
		System.out.println("<--------Long/   Long/   String                -------->");
		System.out.print("> ");
	}
	public static void printResetTips() {
		System.out.println("<--------Reset password action                  -------->");
		System.out.println("<--------please input 4 args as following types-------->");
		System.out.println("<--------ID_Num/ Acc_ID/ Pwd_Old/ Pwd_New      -------->");
		System.out.println("<--------Long/   Long/   String/  String       -------->");
		System.out.print("> ");
	}
	public static void printTransferTips() {
		System.out.println("<--------Transfer amount action                -------->");
		System.out.println("<--------please input 7 args as following types-------->");
		System.out.println("<--------Acc_ID/ ID_Num/ Pwd/    Name/   InAcc_ID/ InName/ Amount ");
		System.out.println("<--------Long/   Long/   String/ String/ Long/     String/ Double ");
		System.out.print("> ");
	}
	public static void printWithdrawTips() {
		System.out.println("<--------WithDraw balance action               -------->");
		System.out.println("<--------please input 3 args as following types-------->");
		System.out.println("<--------Acc_ID/ Passwrd/ Amount               -------->");
		System.out.println("<--------Long/   String/  Double               -------->");
		System.out.print("> ");
	}
	public static void printCheckBalTips() {
		System.out.println("<--------Checking balance action               -------->");
		System.out.println("<--------please input 3 args as following types-------->");
		System.out.println("<--------ID_Num/ Acc_ID/ Passwrd               -------->");
		System.out.println("<--------Long/   Long/   String                -------->");
		System.out.print("> ");
	}
	public static void printAddOptTips() {
		System.out.println("<--------Add opterator action                  -------->");
		System.out.println("<--------please input 3 args as following types-------->");
		System.out.println("<--------Acc_ID/ Sup_Pwd/ ID_Num/ Name/   Passwrd		   -------->");
		System.out.println("<--------Long/   Long/    Long/   String/ String	       -------->");
		System.out.print("> ");
	}
	public static void printUpperSelection() {
		System.out.println("<--------Go back to Upper stage-------->");
		System.out.println("<--------Go back to Upper stage-------->\n");
		
	}
}
