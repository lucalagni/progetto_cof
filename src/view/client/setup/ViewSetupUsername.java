package view.client.setup;

import java.util.Scanner;

public class ViewSetupUsername {
	
	public String show(){
		String username = null; ;
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		boolean flag = false ;
		
		do{
			System.out.println("\n=========={ Insert Username }==========\n\n");
			System.out.print("Username> ");
			username = input.nextLine();
			
			if(username.length() <= 1){
				System.out.println("\nInvalid username\n");
				flag = false;
			}
			else flag = true ;
			
		}while(flag == false);
		
		return username;
	}
}
