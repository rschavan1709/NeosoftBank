package com.neosoft.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.neosoft.Exception.InsufficientBalanceException;
import com.neosoft.Exception.UserNotFoundException;
import com.neosoft.inter.CustomerOperation;
import com.neosoft.main.NeosoftBank;
import com.neosoft.model.Customer;
import com.neosoft.model.Transaction;


public class CustomerOperationImpl implements CustomerOperation{

	Scanner sc=new Scanner(System.in);
	Transaction transaction;
	Customer customer;
	@Override
	public void viewAccount(long acc) {
		for(Customer c:NeosoftBank.list1) {
			if(c.getAccountNo()==acc) {
				System.out.println("***Account Details***");
				System.out.println("Account No.:"+c.getAccountNo());
				System.out.println("Name:"+c.getName());
				System.out.println("Age:"+c.getAge());
				System.out.println("Balance:"+c.getBalance());
			}
		}
	}

	@Override
	public void viewAllTransactions(long acc) {
		for(Transaction trans:customer.transactions) {
			if(trans.getAccNo()==acc) {
			System.out.println(trans);
			}
		}
	}

	@Override
	public void transferMoney(long acc) throws UserNotFoundException, InsufficientBalanceException {
		System.out.println("Enter a Beneficiary Account No.:");
		long acc1=sc.nextLong();
		BigDecimal amt;
		boolean found=false;
		for(Customer c:NeosoftBank.list1) {
			if(c.getAccountNo()==acc1) {
				found=true;
				for(Customer c1:NeosoftBank.list1 ) {
					if(c1.getAccountNo()==acc) {
						System.out.println("Enter a amount:");
						amt=sc.nextBigDecimal();
						if(c1.getBalance().compareTo(amt)>=0) {
							c1.setBalance(c1.getBalance().subtract(amt));
							transaction=new Transaction(acc,"withdraw",amt,c1.getBalance());
							boolean check= customer.transactions.add(transaction);
							if(check) {
								System.out.println("The Amount "+amt+"is debited from your account. Now your current balance is "+c1.getBalance());
							}
							for(Customer c2:NeosoftBank.list1) {
								if(c2.getAccountNo()==acc1) {
									c2.setBalance(c2.getBalance().add(amt));
									transaction=new Transaction(acc1,"deposit",amt,c2.getBalance());
									check = customer.transactions.add(transaction);
									if(check) {
										System.out.println("Successfully transferred money....");
									}
								}
							}
						}
						else {
							throw new InsufficientBalanceException();
						}
					}
				}
			}
		}
		if(found==false) {
			throw new UserNotFoundException();
		}
	}

	@Override
	public void lastFiveTransactions(long acc) {
		
		List<Transaction> t=new ArrayList<>();
		
		/*
		 * Optional<Transaction>
		 * optional=NeosoftBank.tx.stream().filter(item->item.getAccNo()==acc).findFirst
		 * (); if (optional.isPresent()) { t.add(optional.get()); }
		 */
		 
		
		
		 for(Transaction trans:customer.transactions) 
		 { 
			 if(trans.getAccNo()==acc) 
			 {
				 t.add(trans); 
			 } 
		}
		/*
		 * ListIterator itr=t.listIterator(); while(itr.previousIndex() != t.size()-5) {
		 * Transaction transaction=(Transaction) itr.previous();
		 * System.out.println(transaction); }
		 */
		if (t.size()>5) {
			Collections.reverse(t);
			t=t.subList(0, 5);
			ListIterator itr=t.listIterator();
			while(itr.hasNext()) {
				Transaction transaction=(Transaction) itr.next();
				System.out.println(transaction);
			}
			 
		}
		else {
			Collections.reverse(t);
			ListIterator itr=t.listIterator();
			while(itr.hasNext()) {
				Transaction transaction=(Transaction) itr.next();
				System.out.println(transaction);
			}
		}
		
	}
	
	@Override
	public void logOut() {
		System.out.println("You have successfully logout....");
		NeosoftBank.main(null);
	}
	
	@Override
	public void selectCustomerOperation(long accNo) {
		int ch;
		long acc=accNo;
		Scanner sc = new Scanner(System.in);
		CustomerOperationImpl impl=new CustomerOperationImpl(); 
		do {
		System.out.println("****Customer Operation****");
		System.out.println("1. View Account");
		System.out.println("2. View All Transactions");
		System.out.println("3. Transfer Money");
		System.out.println("4. Last Five Transactions");
		System.out.println("5. Logout");
		System.out.println("Select the service");
		ch=Integer.parseInt(sc.nextLine());
		switch(ch) {
		case 1:
			impl.viewAccount(acc);
		break;
		
		case 2:
			impl.viewAllTransactions(acc);
		break;
		
		case 3:
			try {
				impl.transferMoney(acc);
			} catch (UserNotFoundException | InsufficientBalanceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		break;
		
		case 4:
			impl.lastFiveTransactions(acc);
		break;
			
		case 5:
			impl.logOut();
		break;
			
		default:
			System.out.println("Wrong Choice");
		break;
		}
		}while(ch!=5);
	}

}
