package Tier3;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import SharedInterfaces.ITier2;

public class StartTier3 {

	public static void main(String[] args) {
		try 
		{
			ControllerTier3 controllerTier3 = new ControllerTier3();
			ITier2 tier2 = (ITier2) Naming.lookup(ITier2.T2_SERVICE_NAME);
				
			
			
		} catch (Exception e) 
		{
			
		}
	}

}
