package Tier2_BranchServer;

import static SharedInterfaces.ITier3.T3_SERVICE_NAME;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import SharedInterfaces.ITier2;
import SharedInterfaces.ITier3;
import SharedInterfaces.Observer;
import SharedModel.Account;
import SharedModel.AccountCurrency;
import Tier3_HQServer.Observable;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ControllerTier2 extends UnicastRemoteObject implements ITier2{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private ITier3 tier3;
	private AccountCurrency dkkAccountType;
	private AccountCurrency eurAccountType;
	private AccountCurrency usdAccountType;

	protected ControllerTier2() throws RemoteException {
		try {
			dkkAccountType = new AccountCurrency(1.00);
			eurAccountType = new AccountCurrency(7.75);
			usdAccountType = new AccountCurrency(6.5);

			Naming.rebind(T2_SERVICE_NAME, this);
			tier3 = (ITier3) Naming.lookup(T3_SERVICE_NAME);
			tier3.addObserver(this);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public boolean withdraw(int acNumber, double amount) throws RemoteException {
		Account account = tier3.getAccount(acNumber);

		if (account == null)
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

	@Override
	public void notify(Observable obs, AccountCurrency... args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		for (AccountCurrency aC : args){
			if(aC.type.equals(dkkAccountType.type))
				dkkAccountType.currencyWeight = aC.currencyWeight;
			else if(aC.type.equals(eurAccountType.type))
				eurAccountType.currencyWeight = aC.currencyWeight;
			else
				usdAccountType.currencyWeight = aC.currencyWeight;
		}
	}
}

