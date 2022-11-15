package com.neosoft.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.neosoft.impl.AdminOperationImpl;
import com.neosoft.impl.CustomerOperationImpl;
import com.neosoft.inter.CommonMethods;
import com.neosoft.model.Customer;
import com.neosoft.model.Transaction;

class login implements CommonMethods{
	Scanner sc = new Scanner(System.in);
	AdminOperationImpl aImpl = new AdminOperationImpl();
	CustomerOperationImpl cImpl = new CustomerOperationImpl();
	@Override
	public void adminLogin() {
		System.out.println("Enter a Username:");
		String username=sc.nextLine();
		System.out.println("Enter a Password:");
		String password=sc.nextLine();
		if(username.equals(CommonMethods.userName) && password.equals(CommonMethods.password)) {
			System.out.println("Your Login Successfully....");
			aImpl.selectAdminOperation();
		}
		else {
			System.out.println("Your username or password is incorrect");
		}
	}

	@Override
	public void customerLogin() {
		System.out.println("Enter a Name:");
		String name=sc.next();
		System.out.println("Enter a Password:(Note: Password is your account no.)");
		long pass=sc.nextLong();
		for(Customer c: NeosoftBank.list1) {
			if(c.getName().equalsIgnoreCase(name) && c.getAccountNo()==pass) {
				System.out.println("Your Login Successfully....");
				cImpl.selectCustomerOperation(pass);
			}
		}
	}

	
}
public class NeosoftBank {
	public static List<Customer> list1 = new ArrayList<>();
	public static List<Transaction> tx = new ArrayList<>();
	public static void main(String[] args) {
		int ch;
		Scanner sc=new Scanner(System.in);
		login l=new login();
		do {
		System.out.println("*******Welcome to Neosoft Bank*******");
		System.out.println("**MENU**");
		System.out.println("1. Login as admin");
		System.out.println("2. Login as customer");
		System.out.println("3. Exit");
		System.out.println("Select the service");
		ch=Integer.parseInt(sc.nextLine());
		switch (ch) {
		case 1:
			l.adminLogin();
		break;

		case 2:
			l.customerLogin();
		break;
		
		case 3:
		break;
			
		default:
			System.out.println("Wrong Choice");
		break;
		}
	}while(ch!=3);
}
}
