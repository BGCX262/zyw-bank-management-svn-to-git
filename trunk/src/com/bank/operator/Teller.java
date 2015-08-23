package com.bank.operator;

public class Teller extends Employee {
	private Employee superior;

	public Employee getSuperior() {
		return superior;
	}
	public void setSuperior(Employee superior) {
		this.superior = superior;
	}	
}
