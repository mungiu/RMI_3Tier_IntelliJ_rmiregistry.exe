package Tier2_BranchServer;

import static SharedInterfaces.ITier3.T3_SERVICE_NAME;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import SharedInterfaces.ITier2;
import SharedInterfaces.ITier3;
import SharedModel.Account;

public class ControllerTier2 extends UnicastRemoteObject implements ITier2 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ITier3 tier3;

	protected ControllerTier2() throws RemoteException {
		try {
			
			Naming.rebind(T2_SERVICE_NAME, this);
			tier3 = (ITier3) Naming.lookup(T3_SERVICE_NAME);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public boolean withdraw(int acNumber, double amount) throws RemoteException {
		Account account = tier3.getAccount(acNumber);
		
		if(account == null)
			return false;
		
		else if (amount <= 0.0)
			return false;
		
		else {
			try {
				account.withdraw(amount, account.getAccountCurrency());
			} catch (Exception e) {
				e.printStackTrace();
			}
			tier3.updateAccount(account);
			System.out.println("Now you have: " + tier3.getAccount(acNumber).getBalance());
			return true;
		}
	}
}

