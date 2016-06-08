import java.util.ArrayList;

public class TreeRun {

	//Instance Variables
	ArrayList<TreeNode<Object>> _trees;
	ArrayList<String> _names;

	public TreeRun() {
		_trees = new ArrayList<TreeNode<Object>>();
		_names = new ArrayList<String>();
	}

	public void runTime() {

		//Here is the opening thingy
		System.out.println("Self-Balancing Binary Search Tree v1.2");
		System.out.println("By Anya Keller, Constantine Athanitis, and Quinn Dibble");
		System.out.println("For help, type the command \"help\"");
		System.out.println("For a list of commands, type the command \"cmdlist\"");
		System.out.println("To exit the program, type the command \"exit\"");

		//controls the while loop, the exit command will end the loop.
		boolean loopAgain = true;
		
		//while loop, that keeps running until the user exits the program
		while (loopAgain) {

			//String cmd is the user inputted command
			//there will be no parameters for the command,
			//rather the user will be prompted to enter all of the necessary thingies
			System.out.print("Enter a command:  ");
			String cmd = StdIn.readString();
			
			//handles the exit command
			if (cmd.equals("exit")) {
				while (true) {
					System.out.print("Are you sure you want to exit? (y/n)  ");
					String confirm = StdIn.readString();
					if (confirm.equals("y")) {
						loopAgain = false;
						break;
					}
					else if (confirm.equals("n"))
						break;
					else System.out.println("Command not recognized");
				}
			}
			
			//handles the cmdlist command
			else if (cmd.equals("cmdlist")) {

				//these are all the commands
				//modify this as commands are added
				System.out.println("Commands: cmdlist, exit, help");
			}

			else if (cmd.equals("help")) {
				System.out.println("~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("BST Application Help:");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("cmdlist\t:\tlists all available commands");
				System.out.println("exit\t:\tprompts the user if they would like to exit the application");
				System.out.println("help\t:\tdisplays a list of all the commands and their usage");
			}

			//none of the commands are recognized
			else {
				System.out.println("Command not recognized");
			}
		}
	}

	public static void main(String[] args) {
		TreeRun n = new TreeRun();
		n.runTime();
	}
}

