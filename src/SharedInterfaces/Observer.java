package SharedInterfaces;

import Tier3_HQServer.Observable;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.rmi.Remote;

public interface Observer<AccountCurency> extends Remote {
	void notify(Observable obs, AccountCurency... args) throws IOException, UnsupportedAudioFileException, LineUnavailableException;
}

