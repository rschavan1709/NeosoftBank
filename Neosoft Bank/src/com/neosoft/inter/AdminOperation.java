package com.neosoft.inter;

import com.neosoft.Exception.InsufficientBalanceException;
import com.neosoft.Exception.UserNotFoundException;

public interface AdminOperation {
	public void accountOpen() ;
	public void deposit() throws UserNotFoundException;
	public void withdraw() throws UserNotFoundException, InsufficientBalanceException;
	public void deleteAccount() throws UserNotFoundException;
	public void selectAdminOperation() throws UserNotFoundException;
	public void viewAllTheCustomers();
	public void balanceCheck() throws UserNotFoundException;
	public void LogOut();
}
