package Tier1;

import java.rmi.Naming;
import java.util.Scanner;

import SharedInterfaces.ITier2;

public class StartTier1 {

	public static void main(String[] args) 
	{
		try 
		{
			ITier2 tier2 = (ITier2) Naming.lookup(ITier2.T2_SERVICE_NAME);
			// hardcoded ammount to withdraw
			while(true)
			{
				System.out.print("What amount you want to withdraw?: ");
				Scanner sc = new Scanner(System.in);
				String input = sc.nextLine();
				int amount = Integer.parseInt(input);
				if(tier2.withdraw(1234, amount))
				{
					
					System.out.print("Withdrawed money: " + amount);
				}
				else
					break;
			}
			
						
				
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}

}
