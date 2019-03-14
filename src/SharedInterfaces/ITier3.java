package SharedInterfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import SharedModel.Account;

public interface ITier3 extends Remote {
	
	public Account getAccount(int acNumber) throws RemoteException;
	
	public void updateAccount(Account account) throws RemoteException;
	
	public static final String T3_SERVICE_NAME = "rmi://localhost/T3";

}

