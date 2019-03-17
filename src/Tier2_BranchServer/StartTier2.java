package Tier2_BranchServer;

import java.rmi.RemoteException;

public class StartTier2 {

	public static void main(String[] args) {
		/**
		 * Instantiate Skeleton here
 		 */
		try 
		{
			//Registry reg = LocateRegistry.createRegistry(0);
			ControllerTier2 controllerTier2 = new ControllerTier2();
		} catch (RemoteException e) 
		{
			e.printStackTrace();
		}

	}

}
