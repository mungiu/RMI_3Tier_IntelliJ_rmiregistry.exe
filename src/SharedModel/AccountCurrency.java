package SharedModel;

public class AccountCurrency {
	public double currencyWeight;
	public TYPE type;

	public AccountCurrency(double currencyWeight) {
		this.currencyWeight = currencyWeight;
	}

	public enum TYPE {
		// NOTE: Order matter when using compareTO()
		DKK,
		USD,
		EUR
	}
}
