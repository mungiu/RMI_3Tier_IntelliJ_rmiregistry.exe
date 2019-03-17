package Tier3_HQServer;

import java.rmi.Naming;

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
