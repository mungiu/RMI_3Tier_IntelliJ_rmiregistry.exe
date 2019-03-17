package SharedInterfaces;

import SharedModel.AccountCurrency;

import java.rmi.RemoteException;

public interface ITier2 extends Observer<AccountCurrency> {
	
	public boolean withdraw(int acNumber, double amount) throws RemoteException;
	
	public static final String T2_SERVICE_NAME = "rmi://localhost:1099/T2";

}
