package com.neosoft.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Scanner;

import com.neosoft.Exception.InsufficientBalanceException;
import com.neosoft.Exception.UserNotFoundException;
import com.neosoft.inter.AdminOperation;
import com.neosoft.main.NeosoftBank;
import com.neosoft.model.Customer;
import com.neosoft.model.Transaction;

public class AdminOperationImpl implements AdminOperation {

	Customer customer;
	Transaction transaction;
	Scanner sc = new Scanner(System.in);
	@Override
	public void accountOpen() {
		System.out.println("Enter a Name:");
		String name=sc.next();
		System.out.println("Enter a Age:");
		int age=sc.nextInt();
		System.out.println("Enter a Balance:");
		BigDecimal balance=sc.nextBigDecimal();
		customer = new Customer(name,Customer.i,balance,age);
		boolean addCheck =NeosoftBank.list1.add(customer);
		if(addCheck) {
			System.out.println("Account Created Successfully.....");
			transaction =new Transaction(customer.getAccountNo(),"deposit",balance,balance);
			customer.transactions.add(transaction);
			System.out.println("Amount is also Deposited....");
		}
		else {
			System.out.println("Account creation failed...Try Again...");
		}
	}

	@Override
	public void deposit() throws UserNotFoundException {
		System.out.println("Enter a Account No. for deposit:");
		long acc=sc.nextLong();
		boolean found=false;
		for(Customer c:NeosoftBank.list1 ) {
			if(c.getAccountNo()==acc) {
				found=true;
				System.out.println("Enter a amount:");
				BigDecimal amt=sc.nextBigDecimal();
				BigDecimal balance=c.getBalance().add(amt);
				c.setBalance(balance);
				System.out.println("Balance Deposited....");
				System.out.println("Balance is:"+c.getBalance());
				transaction=new Transaction(acc,"deposit",amt,c.getBalance());
				customer.transactions.add(transaction);
				for(Transaction trans:customer.transactions) {
					System.out.println(trans);
				}
			}
		}
		if(found==false) {
			throw new UserNotFoundException();
		}
	}

	@Override
	public void withdraw() throws UserNotFoundException, InsufficientBalanceException {
		System.out.println("Enter a Account No. for withdraw:");
		long acc=sc.nextLong();
		boolean found=false;
		for(Customer c: NeosoftBank.list1) {
			if(c.getAccountNo()==acc) {
				found=true;
				System.out.println("Enter a amount:");
				BigDecimal amt=sc.nextBigDecimal();
				if(c.getBalance().compareTo(amt)>=0) {
				BigDecimal balance=c.getBalance().subtract(amt);
				c.setBalance(balance);
				System.out.println("Balance Withdrew...");
				System.out.println("Balance is:"+c.getBalance());
				transaction=new Transaction(acc,"withdraw",amt,c.getBalance());
				customer.transactions.add(transaction);
				for(Transaction trans:customer.transactions) {
							System.out.println(trans);
						}
				}
				else {
					throw new InsufficientBalanceException();
				}
			}
		}
		if (found==false) {
			throw new UserNotFoundException();
		}
		
	}

	@Override
	public void deleteAccount() throws UserNotFoundException {
		System.out.println("Enter a Account No.for Delete:");
		long acc=sc.nextLong();
		boolean found=false;
		for(int i=0;i<NeosoftBank.list1.size();i++) {
			Customer c=NeosoftBank.list1.get(i);
			if(c.getAccountNo()==acc) {
				found=true;
				Boolean delete= NeosoftBank.list1.remove(c);
				if(delete) {
					System.out.println("Account Deleted Successfully...");
				}
				else{
					System.out.println("Account Deletion Failed...");
				}
			}
		}
		if (found==false) {
			throw new UserNotFoundException();
		}
	}

	@Override
	public void viewAllTheCustomers() {
		for(Customer c: NeosoftBank.list1) {
			System.out.println(c);
		}
	}

	@Override
	public void LogOut() {
		System.out.println("You have successfully logout....");
		NeosoftBank.main(null);
	}
	
	@Override
	public void balanceCheck() throws UserNotFoundException {
		System.out.println("Enter a Account No. for balance check:");
		long acc=sc.nextLong();
		boolean found=false;
		for(Customer c: NeosoftBank.list1) {
			if(c.getAccountNo()==acc) {
				found=true;
				System.out.println("Balance is"+c.getBalance());
			}
		}
		if (found==false) {
			throw new UserNotFoundException();
		}
	}
	
	@Override
	public void selectAdminOperation() {
		int ch;
		AdminOperationImpl aImpl = new AdminOperationImpl();
		do {
		System.out.println("*****Admin Operation******");
		System.out.println("1. Open Account");
		System.out.println("2. Deposit Amount");
		System.out.println("3. Withdraw Amount");
		System.out.println("4. Balance Check");
		System.out.println("5. Delete Account");
		System.out.println("6. View all the customers of Bank");
		System.out.println("7. Log Out");
		System.out.println("Select the service");
		ch=Integer.parseInt(sc.nextLine());
		switch(ch) {
		case 1:
			aImpl.accountOpen();
		break;
		
		case 2:
			try {
				aImpl.deposit();
			} catch (UserNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		break;
		
		case 3:
			try {
				aImpl.withdraw();
			} catch (UserNotFoundException | InsufficientBalanceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		break;
		
		case 4:
			try {
				aImpl.balanceCheck();
			} catch (UserNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		break;
		
		case 5: 
			try {
				aImpl.deleteAccount();
			} catch (UserNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		break;
		
		case 6:
			aImpl.viewAllTheCustomers();
		break;
		
		case 7:
			aImpl.LogOut();
		break;
			
		default:
			System.out.println("Wrong Choice");
		break;
		}
		}while(ch!=7);
	}


}
