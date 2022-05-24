package curopus;
import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.text.DecimalFormat;

import javax.swing.*;

//curopus: the task management program
//by olivia white, kirby assaf, megan willis

class classMain{
	//instantiating global variables
	public static double xp;
	public static double hunger;
	public static double happiness;
	public static double energy;
	public static String diff;
	public static File savefile;
	public static HashMap<String, String> taskMap = new HashMap<String, String> ();
	public static Scanner scan = new Scanner(System.in);
	
	//add() - adds new task and task difficulty from user input
	public static void add() {
		
		//task creation
		System.out.println("What is the task you'd like to add?");
		String desc = scan.nextLine();
		
		boolean loop = false;
		
		//task difficulty
		while (loop == false) {
			System.out.println("How difficult is this task?");
			System.out.println("1. Easy");
			System.out.println("2. Moderate");
			System.out.println("3. Difficult");
			diff = scan.nextLine();
			
			
			if (diff.equals("1") || diff.equals("2") || diff.equals("3")) {
				loop = true;
				taskMap.put(desc, diff); //add task (as key) and diff (as value) to hashmap
			}
			else {
				System.out.println("Difficulty must be 1.(Easy), 2.(Moderate), or 3.(Difficult).");
			}
		}
	}
	
	//source: https://stackoverflow.com/questions/1066589/iterate-through-a-hashmap
    public static void printTasks() {
        if (taskMap.isEmpty() == false) {
            System.out.println("Here are your tasks:");
            for (Map.Entry<String, String> entry : taskMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                System.out.println("Task: " + key + "\tDifficulty: " + value);
            }
        }
        else {
            System.out.println("You have no tasks");
        }
    }
	
    //view()- allows user to view tasks in list (taskList) and complete, remove tasks, or exit
  	//calls task() 
  	//return: String inputV
	public static String view() {
		
		printTasks();
		
		boolean loop2 = true;
		
		String inputV = "0";
		
		//complete or delete a task loop
		while (loop2 == true) {
			System.out.println("What would you like to do?");
			if (taskMap.isEmpty() == false) {
				System.out.println("1. Complete a task");
				System.out.println("2. Delete a task");
				System.out.println("3. Return to menu");
				inputV = scan.nextLine();
			}
			else {
				System.out.println("1. Add a task");
				System.out.println("2. Return to menu");
				inputV = scan.nextLine();
				if (inputV.equals("1")) {
					inputV = "4";
				}
				else if (inputV.equals("2")) {
					inputV = "3";
				}
			}
			
			
			if (inputV.equals("1") || inputV.equals("2")) {
				task(inputV);  
			}
			else if (inputV.equals("3")) {
				loop2 = false;
				System.out.println("Returning to menu.");
			}
			else if (inputV.equals("4")) {
				add();
			}
			else {
				System.out.println("Please select a valid option.");
			}
		}
		return(inputV);
	}
	
	//task()- lists current tasks, allows user to select which task to remove, adds to xp
	//if the user completed a task based on the task difficulty (diff)
	//updates hashmap (taskMap) and list (taskList) as well
	public static void task(String inputV) { 
		//removing/completing task
			
		printTasks();
		if (taskMap.isEmpty()== false) {
			System.out.println("Which task would you like to remove?");
			
			String taskName = scan.nextLine();
			String temp = taskMap.get(taskName); //this will get the difficulty
			//System.out.println("takName " + taskName + "temp " + temp);
			//rest in peace takName
			if (taskMap.containsKey(taskName) == false) { //checks to see if key taskName in map -> changed by olivia 2/28 (containsValue to containsKey)
				System.out.println("That task is not in your list. Would you like to try again?\n1. Enter a different task.\n2. Exit");
				String tryAgain = scan.nextLine();
				//loops and takes user input for task until they choose to exit, or it is in map
				while (tryAgain.equals("1")) {
					System.out.println("Which task would you like to remove?");
					taskName = scan.nextLine();
					if (taskMap.containsKey(taskName) == true) {
						temp = taskMap.get(taskName);
						break;
					}
				}
			}
			
			if (inputV.equals("1")) {
				System.out.println(taskName + " completed.");
				if (temp != null) {
					if (temp.equals("1")) {
						xp = xp + 5;
					}
					else if (temp.equals("2")) {
						xp = xp + 10;
					}
					else if (temp.equals("3")) {
						xp = xp + 15;
					}
				}
			}
			//subtract values if they delete a task instead of completing it (sorry bro)
			else if (inputV.equals("2")){
				System.out.println(taskName + " deleted.");
				if (temp != null) {
					if (temp.equals("1")) {
						xp = xp - 5;
					}
					else if (temp.equals("2")) {
						xp = xp - 10;
					}
					else if (temp.equals("3")) {
						xp = xp - 15;
					}
					if (xp < 0) {
						xp = 0;
					}
				}
			}
			taskMap.remove(taskName); //remove the key/value from the hashmap
		}
		else {
			System.out.println("You cannot complete or delete tasks you don't have!");
		}
	}
	
	//opus()- prints out the character opus, hunger/happiness etc. levels, and store options
	//allows user to purchase items for opus
	public static void opus() {
		//this was the original ascii opus before olivia designed the new one
		/*
		
	             .      .
			     |\____/|
			    (\|----|/)
			     \ 0  0 /
			      |    |
			   ___/\../\____
			  /     --       \
			 /  \         /   \
			|    \___/___/(   |
			\   /|  }{   | \  )
			 \  ||__}{__|  |  |
			  \  |;;;;;;;\  \ / \_______
			   \ /;;;;;;;;| [,,[|======'
			     |;;;;;;/ |     /
			     ||;;|\   |
			     ||;;/|   /
			     \_|:||__|
			      \ ;||  /
			      |= || =|
			      |= /\ =|
			      /_/  \_\
			     
		*/
		
		JFrame f = new JFrame ("Opus") ;
		//***NEED OPUS.PNG IN FILES TO LOAD***
		ImageIcon opus = new ImageIcon("opus.png");
		f. add (new JLabel(opus)) ;
		f.pack();
		f.setVisible(true);
		
		int redeem = 0;
		
		
		//store
		while (redeem != 2) {
			DecimalFormat df = new DecimalFormat("###.#");
			
			System.out.printf("Hunger: " + df.format(hunger) + "/" + "50.0\n");
			System.out.printf("Happiness: " + df.format(happiness) + "/" + "50.0\n");
			System.out.printf("Energy: " + df.format(energy) + "/" + "50.0\n");
			System.out.println("Experience Points: " + xp);
			
			System.out.println("Would you like to buy anything?");
			System.out.println("1. Food (+5 Hunger) (5 xp)");
			System.out.println("2. Toy (+5 Happiness) (5 xp)");
			System.out.println("3. Lightning Bolt (+5 Energy) (5 xp)");
			System.out.println("4. Exit");
			
			String purchase = scan.nextLine();
			
			if (purchase.equals("1")) {
				if (xp < 5) {
					System.out.println("You don't have enough xp! Complete tasks to earn more xp.");
				}
				else {
					System.out.println("Bought Food for 5xp. Hunger increased by 5.");
					xp = xp - 5;
					hunger = hunger + 5;
				}
			}
			else if (purchase.equals("2")) {
				if (xp < 5) {
					System.out.println("You don't have enough xp! Complete tasks to earn more xp.");
				}
				else {
					System.out.println("Bought Toy for 5xp. Happiness increased by 5.");
					xp = xp - 5;
					happiness = happiness + 5;
				}
			}
			else if (purchase.equals("3")) {
				if (xp < 5) {
					System.out.println("You don't have enough xp! Complete tasks to earn more xp.");
				}
				else {
					System.out.println("Bought Lightning Bolt for 5xp. Energy increased by 5.");
					xp = xp - 5;
					energy = energy + 5;
				}
			}
			else if (purchase.equals("4")) {
				System.out.println("Returning to main menu.");
				redeem = 2;
			}
			else {
				System.out.println("Please select a valid option.");
			}
			
		}
		f.setVisible(false);
	}
	
	public static String getUsername() {
		System.out.println("Please enter your username if you are a new or returning user: ");
		String username = scan.nextLine();
		return(username);
	}
	
	public static File createFile(String u) throws IOException {
		String fileName = u + ".txt";
		File myFile = new File(fileName);
		xp = 0;
		hunger = 0;
		happiness = 0;
		energy = 0;
		return(myFile);
	}
	
	public static void writeToFile() {
		//override old file with new hashmap
		try{
			FileWriter fw = new FileWriter(savefile, false);
			PrintWriter pw = new PrintWriter(fw, false);
			pw.flush();
			pw.close();
			fw.close();
		}//end try
		catch(Exception exception){
			System.out.println("Exception have been caught");
		}//end catch
		
		BufferedWriter bf = null;
		  
		try {
		//write to file code -> source: https://www.geeksforgeeks.org/write-hashmap-to-a-text-file-in-java/
		//create new BufferedWriter for the output file
			bf = new BufferedWriter(new FileWriter(savefile));
		   
		    //iterate hashmap entries
			for (Map.Entry<String, String> entry : taskMap.entrySet()) {
		        //add key and value to file, separated by colon
				bf.write(entry.getKey() + ":" + entry.getValue());
		        //add a new line
		        bf.newLine();
		      }//close for
		        bf.flush();
		}//close try
		catch (IOException e) {
		      e.printStackTrace();
		}//close catch
		
		//writing values
		try {
			bf.write("xp" + ":" + xp);
			bf.newLine();
			bf.write("hunger" + ":" + hunger);
			bf.newLine();
			bf.write("happiness" + ":" + happiness);
			bf.newLine();
			bf.write("energy" + ":" + energy);
			bf.newLine();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
			
		finally {
			try {
		        //always close the writer
				bf.close();
			}//close finally try
			catch (Exception e) {
			}//close catch
		}//close finally
	}
	
	public static void saveToFile() {
		//turning string into hashmap!!! -> source: https://www.geeksforgeeks.org/reading-text-file-into-java-hashmap/
        BufferedReader br = null;
        
        try {
            //create BufferedReader object from the user's file
            br = new BufferedReader(new FileReader(savefile));
  
            String line = null;
  
            //read file line by line
            while ((line = br.readLine()) != null) {
                //split the line at the colon
                String[] parts = line.split(":");
  
                //string before colon is task, string after colon is difficulty 
                String txtTask = parts[0].trim();
                String txtDiff = parts[1].trim();
                
                //saving tasks differently than xp, hunger, happiness, energy
                if (txtTask.matches("xp") || txtTask.matches("hunger") || txtTask.matches("happiness") || txtTask.matches("energy")) {
                	
                	if (txtTask.equals("xp")) {
                		double xpAdd = Double.parseDouble(txtDiff);
                		xp = xpAdd;
                	}
                	else if (txtTask.equals("hunger")) {
                		double hungAdd = Double.parseDouble(txtDiff);
                		hunger = hungAdd;
                	}
                	else if (txtTask.equals("happiness")){
                		double happAdd = Double.parseDouble(txtDiff);
                		happiness = happAdd;
                	}
                	else if (txtTask.equals("energy")) {
                		double enerAdd = Double.parseDouble(txtDiff);
                		energy = enerAdd;
                	}
                }
  
                //if task and diff aren't empty and it's not a value
                //put task and diff in HashMap
                if (!txtTask.equals("") && !txtDiff.equals("") && !txtTask.matches("xp") && !txtTask.matches("hunger") && !txtTask.matches("happiness") && !txtTask.matches("energy")) {
                	taskMap.put(txtTask, txtDiff);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
  
            //always close the BufferedReader
            if (br != null) {
                try {
                    br.close();
                }
                catch (Exception e) {
                };
            }
        }
	}
	
	//main()
	
	public static void main (String [] args) throws IOException {
		//login loop
		
		boolean login = false;
		
		while (login == false) {
			
			String username = getUsername(); //calls getUsername()
			
			//calls createFile, either opens existing or creates new file
				
			try {
				savefile = createFile(username); //calls createFile, either opens existing or creates new file
				//true if new file is created, false if the file already exists
				boolean tf = savefile.createNewFile();
				if (tf == false) {
					System.out.println("File " + savefile.getName() + " loaded. Login successful, welcome " + username);//if user already has a file
				} //end if
				else {
					System.out.println("Welcome, new user " + username); //if new user
				} //end else
				login = true;
				} catch (FileNotFoundException e) {
					System.out.println("First catch: An error occurred.");
					e.printStackTrace();
				} //end try/catch
			
		} //end while
		
		//main loop
		boolean cont = true;
		
		saveToFile();
		
		//decrease by a random percent
		double min = 0.08;
		double max = 0.12;
		double random = ThreadLocalRandom.current().nextDouble(min, max);
		
		//this decreases opus's stats over time
		hunger = (hunger - (hunger * random));
		if (hunger < 0) {
			hunger = 0;
		}
		happiness = (happiness - (happiness * random));
		if (happiness < 0) {
			happiness = 0;
		}
		energy = (energy - (energy * random));
		if (energy < 0) {
			energy = 0;
		}
		
		while(cont == true) {
			
	        //return taskMap;
			
			String inputv = "";
			System.out.println("\nCuropus\nPlease select an option:\n1. Add New Task\n2. View To-Do List\n3. View Opus\n4. Exit");
			String input = scan.nextLine();
		
			if (input.equals("1")) {
				add();
			}
			else if (input.equals("2")) {
				inputv = view();  
			}
			else if (input.equals("3")) {
				opus();
			}
			else if (input.equals("4")) {
				System.out.println("Goodbye!");
				writeToFile();
				break;
			}
			else {
				boolean isValid = false;
				while (isValid == false) {
					System.out.println("Please select a valid option.\n1. Add New Task\n2. View To-Do List\n3. View Opus\n4. Exit");
					input = scan.nextLine();
					if (input.equals("1") | input.equals("2") | input.equals("3") |input.equals("4")) {
						isValid = true;
					} //if input
				} //while isValid
			} //else
			if (input.equals("4")) {
				System.out.println("Goodbye!");
				writeToFile();
				break;
			}
			if (!inputv.equals("3")) {
                System.out.println("What would you like to do?\n1. Return to Main Menu\n2. Exit");
                String contYesNo = scan.nextLine();
                if (contYesNo.equals("2")) {
                    cont = false;
                    System.out.println("Goodbye!");
                    writeToFile();
                    break;
                }
			
			} //end if contYesNo
		} //end while
	} //close main
} //close classMain