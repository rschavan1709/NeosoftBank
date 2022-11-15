package com.neosoft.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

public class Transaction {
	private long accNo;
	private LocalDateTime d=LocalDateTime.now();
	private String type;
	private BigDecimal amt;
	private BigDecimal balance;
	
	public Transaction(long accNo, String type, BigDecimal amt, BigDecimal balance) {
		super();
		this.accNo=accNo;
		this.type = type;
		this.amt = amt;
		this.balance = balance;
	}

	public long getAccNo() {
		return accNo;
	}

	public void setAccNo(long accNo) {
		this.accNo = accNo;
	}
	
	public LocalDateTime getD() {
		return d;
	}
	public void setD(LocalDateTime d) {
		this.d = d;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Transaction [d=" + d + ", type=" + type + ", amt=" + amt + ", balance=" + balance + "]";
	}
	
	
	
}
