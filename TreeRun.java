public class TreeRun {

	//Instance Variables
	String[] _commands;

	//constructor
	public TreeRun() {
		_commands = {"exit", "cmdlist", "help", "trees", "deltree", "crtree", "edtree", "prtree", "rm", "add", "togglebalstate", "rename", "converttype", "rmrow", "rmleaves", "addrandom"};

	public static boolean checkCommand(String[] cmd) {
		for (int i = 0; i < _commands.length; i++) if (cmd[0].equals(_commands[i])) return true;
		return false;
	}

	public static void runTime() {
		System.out.println("Self-Balancing Binary Search Tree v1.0");
		System.out.println("By Anya Keller, Constantine Athanitis, and Quinn Dibble");
		System.out.println("For a list of commands, type the command \"cmdlist\" at any time");
		System.out.println("To exit the program, type the command \"exit\"");
		System.out.print("Enter a command:\t ");
		String rawcmd = StdIn.readString();
		Boolean exit = false;
		while (true) { //infinite loop, the exit command will break it
			String[] cmd = rawcmd.split(" ");
			//checks if the command is valid, if it is then find the proper command to execute, otherwise it whines at the user
			if (checkCommand(cmd)) {

				/* This condition will exit the program, is always called first */
				if (cmd[0].equals("exit")) {
					System.out.print("Are you sure you want to exit? (y/n):\t");
					String confirm = StdIn.readString();
					while (true) {
						if (confirm.equals("y")) {
							//somehow exit the program
							exit = true;
							break;
						}
						else if (confirm.equals("n")) {
							//go back to asking for a command
							break;
						}
						else {
							System.out.println();
							System.out.println("Command not recognised.\nAre you sure you want to exit? (y/n):\t");
							confirm = StdIn.readString();
						}
					}
				} //end of the exit command



			} //end of the command runtime

			/*
			here is going to be where the commands are going to be executed,
			using a lot of if statements to check if a command is valid.

			Temp command list:

			exit					prompts the user if they want to exit the program, they respond with y/n
			cmdlist					displays all the available commands to the user
			trees					shows all current created trees
			deltree					prompts the user with a list of current trees, asking which one to delete.
									If there are no trees available, the program will not run the command
									if there the user inputs an invalid treename, the program will be angry
			crtree <treename>		starts the procedure to create a new tree, with prompts for things such as
									type, etc.
			edtree					Prompts the user with a list of trees they are able to edit
			*/
			/* standard format for the conditions for calling a command 
			else if (cmd[0].equals(<commandname>)) {
				<execute command here>;
			}
		}

	}

	public static void main(String[] args) {
		
	}
}