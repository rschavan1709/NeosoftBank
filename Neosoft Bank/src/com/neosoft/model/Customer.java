package com.neosoft.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

public class Customer {
	private String name;
	private long accountNo;
	private BigDecimal balance;
	private int age;
	public static int i=1001;
	
	public static Collection<Transaction> transactions=new ArrayList<>();
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public long getAccountNo() {
		return accountNo;
	}
	
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	
	public BigDecimal getBalance() {
		return balance;
	}
	
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Customer(String name, long accountNo, BigDecimal balance, int age) {
		super();
		this.name = name;
		this.accountNo = accountNo;
		this.balance = balance;
		this.age = age;
		i++;
	}
	
	@Override
	public String toString() {
		return "Customer [name=" + name + ", accountNo=" + accountNo + ", balance=" + balance + ", age=" + age + "]";
	}
	
	
}
