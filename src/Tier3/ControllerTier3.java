package Tier3;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import SharedInterfaces.ITier3;
import SharedModel.Account;

public class ControllerTier3 extends UnicastRemoteObject implements ITier3{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Account acAndrei = new Account(1234,5000);
	
	public ControllerTier3() throws RemoteException 
	{
	try {
		
		Naming.rebind(T3_SERVICE_NAME, this);
		System.out.println("Do som transaction");
		
		
	} catch (Exception e) {
		e.printStackTrace();
		System.exit(1);
	}
	}
	
	//this is a stub to mimic a database
	// Here you can put real database coding stuff
	

	@Override
	public Account getAccount(int acNumber) throws RemoteException {
		return acAndrei;
	}

	@Override
	public void updateAccount(Account account) throws RemoteException {
		acAndrei = account;
		
	}
}
