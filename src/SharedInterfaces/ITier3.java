package SharedInterfaces;

import java.rmi.RemoteException;

import SharedModel.Account;
import SharedModel.AccountCurrency;
import Tier3_HQServer.Observable;

public interface ITier3 extends Observable<AccountCurrency> {
	
	public Account getAccount(int acNumber) throws RemoteException;
	
	public void updateAccount(Account account) throws RemoteException;
	
	public static final String T3_SERVICE_NAME = "rmi://localhost/T3";

}

