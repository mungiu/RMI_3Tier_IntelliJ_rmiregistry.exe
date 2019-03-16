package SharedModel;

import java.io.Serializable;
import java.util.Map;

public class Account extends Customer implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int accountNumber;
	private double balance;
	private String currency;
	private Map<Integer, Account> _accountMap;
	private Logger _logger;

	private AccountCurrency accountCurrency;


	public Account(int cprNr, String fullName, String address, int accountNumber, double balance, String currency) {
		super(cprNr, fullName, address);

		this.accountNumber = accountNumber;
		this.balance = balance;
		this.currency = currency;

		_accountMap = AccountList.getInstance().getAccountMap();
		_logger = Logger.getInstance();
		_accountMap.putIfAbsent(accountNumber, this);


		switch (currency.toUpperCase()) {
			case "DKK":
				accountCurrency.type = AccountCurrency.TYPE.DKK;
				accountCurrency = new AccountCurrency(1);
				break;
			case "USD":
				accountCurrency.type = AccountCurrency.TYPE.USD;
				accountCurrency = new AccountCurrency(6.5);
				break;
			case "EUR":
				accountCurrency.type = AccountCurrency.TYPE.EUR;
				accountCurrency = new AccountCurrency(7.5);
				break;
			default:
				accountCurrency.type = AccountCurrency.TYPE.DKK;
				accountCurrency = new AccountCurrency(1);
				break;
		}

	}

	public void deposit(double amount, AccountCurrency externalCurrency) throws Exception {
		_logger.log("Trying to deposit: " + amount + currency);

		if (accountCurrency.type.equals(externalCurrency.type))
			balance += amount;
		else {
			double exchangeRate = accountCurrency.currencyWeight - externalCurrency.currencyWeight;
			balance += amount / exchangeRate;
		}
	}

	public void withdraw(double amount, AccountCurrency externalCurrency) throws Exception {
		_logger.log("Trying to withdraw: " + amount + currency);

		externalCurrency.type = AccountCurrency.TYPE.valueOf(currency.toUpperCase());
		if (accountCurrency.type.equals(externalCurrency.type) && this.balance >= amount)
			balance -= amount;
		else {
			double exchangeRate = accountCurrency.currencyWeight - externalCurrency.currencyWeight;
			double tempResult = balance - (amount / exchangeRate);
			if (tempResult >= 0.0)
				balance = tempResult;
			else throw new Exception("Not enough funds");
		}
	}

	public void transferTo(int accountNumber, double amount, AccountCurrency externalCurrency) {
		_logger.log("Trying to transfer: " + amount + currency + "from account" + this.accountNumber + " to account" + accountNumber);
		if (_accountMap.containsKey(accountNumber)) {
			try {
				_accountMap.get(this.accountNumber).withdraw(amount, externalCurrency);
				_accountMap.get(accountNumber).deposit(amount, externalCurrency);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public AccountCurrency getAccountCurrency() {
		return accountCurrency;
	}

	public double getBalance() {
		return balance;
	}
}

