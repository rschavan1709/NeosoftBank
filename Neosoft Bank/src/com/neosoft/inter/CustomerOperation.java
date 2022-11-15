package com.neosoft.inter;

import com.neosoft.Exception.InsufficientBalanceException;
import com.neosoft.Exception.UserNotFoundException;

public interface CustomerOperation {
	public void viewAccount(long acc);
	public void viewAllTransactions(long acc);
	public void  transferMoney(long acc) throws UserNotFoundException, InsufficientBalanceException;
	public void lastFiveTransactions(long acc);
	public void logOut();
	public void selectCustomerOperation(long accNo);
}
