package SharedModel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountList {

	private static AccountList instance;
	private static Lock lock = new ReentrantLock();
	private Map<Integer, Account> accountMap;

	/**
	 * SINGLETON
	 */
	private AccountList(){
		accountMap = new HashMap<>();
	}

	public static AccountList getInstance(){
		if (instance == null)
			synchronized (lock)
			{
				if (instance == null)
					instance = new AccountList();
			}
		return instance;
	}

	public Map<Integer, Account> getAccountMap() {
		return accountMap;
	}
}
