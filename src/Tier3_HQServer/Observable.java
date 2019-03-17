package Tier3_HQServer;

import SharedInterfaces.Observer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface Observable<AccountCurrency> extends Remote {
	List<Observer> observers = new ArrayList<>();

	/**
	 * Ads an observer
	 *
	 * @param obs
	 * @throws RemoteException
	 */
	default void addObserver(Observer<AccountCurrency> obs) throws RemoteException {
		if (obs == null) throw new NullPointerException();
		if (!observers.contains(obs))
			observers.add(obs);
	}

	default void deleteObserver(Observer<AccountCurrency> obs) throws RemoteException {
		observers.remove(obs);
	}

	default void notifyObservers(AccountCurrency arg) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		for (Observer observer : observers)
			observer.notify(this, arg);
	}
}
