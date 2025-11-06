package basketballApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * <p>Title: Application Class</p>
 * <p>Description: This class contains the main method for the basketball app. It will read off of a
 * text document named, "applicants.txt" and store each of the players into an array of AVL Trees and two
 * AVL Trees. The array of length 6 will be the array that contains the 6 trees that are in the basketball league. 
 * One of the AVL Trees will store the Eligible Applicants that can be added after all applications have gone through. 
 * And the last AVL tree will store ineligible applicants.</p>
 * 
 * @author Gaetano Re
 * N#: N00918949
 */
public class Application {
	
	public static void main(String args[]) {
		@SuppressWarnings("unchecked")
		AVLTree<BasketballApplicant>[] eligibleApps = new AVLTree[6]; /* This is the collection of teams that are 
																		Elligable for the league. 6 teams in total*/
		
		
		AVLTree<BasketballApplicant> readyToBeAdded = new AVLTree<BasketballApplicant>(); /* This is the collection of
		 																					 Teams that are eligable but
		 																					 are 10 years of age. They will
		 																					 get added once space is available*/
		
																							
		AVLTree<BasketballApplicant> ineligibleApps = new AVLTree<BasketballApplicant>(); /* This is the collection of ineligable
																							applicants. These players are either over 
																							the age of 10 or under the age of 9.*/
		
		int numEligible = 0; // This keeps track of the number of applicants that are elligable
		int teamNum = 0; // This keeps track of the team that is being filled up
		boolean done = false; // This will stop the loop that fills up any available positions after the initial reading is done
		try {
			File applicantFile = new File("applicants.txt"); // Read the applicants text file
			
			Scanner scnr = new Scanner(applicantFile); // Utilize the scanner class to read the text file
			
			eligibleApps[teamNum] = new AVLTree<BasketballApplicant>(); // create a new AVLTree to initialize the first team
			
			while(scnr.hasNext()) { // While the scanner has a next line
				BasketballApplicant currApp; // instantiate a new object for the current applicant that is being read
				
				String currentLine = scnr.nextLine(); // Get the current line
				
				String[] entry = currentLine.split(" "); // Split the data of the current line by the spaces that separate the data
				
				String[] birthDate = entry[2].split("-"); // Split up the date of birth by the '-'
				
				String[] appDate = entry[3].split("-"); // Split up the date of the application submission
				
				currApp = new BasketballApplicant(entry[0], entry[1], Integer.parseInt(birthDate[0]), Integer.parseInt(birthDate[1]), Integer.parseInt(birthDate[2]),
						Integer.parseInt(appDate[0]), Integer.parseInt(appDate[1]), Integer.parseInt(appDate[2])); // Insert the split data into the applicant object
				
				int currAppAge = currApp.getAge(); // Get the current age of the applicant
				if(currAppAge < 9 || currAppAge > 10) { // If the applicant is younger or older than the required age
					
					ineligibleApps.insert(currApp); // Insert the ineligible player into the ineligible tree
				}
				
				else if(currAppAge == 10 || (teamNum == 5 && numEligible == 9)) { // If the age of the applicant is 10, or the teams are all filled up
					
					readyToBeAdded.insert(currApp); // Place the players in the ready to be inserted file
				}
				
				else if(numEligible == 9) { // If the number of eligible applicants in this team is 9
					
					teamNum++; // Increment to the next team number
					
					eligibleApps[teamNum] = new AVLTree<BasketballApplicant>(); // Instantiate a new tree
					
					numEligible = 0; // Reset the number eligible counter
				}
				else{ // Else, the player is eligible for the team
					
					eligibleApps[teamNum].insert(currApp); // Insert the player into the tree
					
					numEligible++; // Increment the amount of eligible players on the team
				}
			}
			
			while (!done) { // While we are not done filling up the league
			    if (readyToBeAdded == null || readyToBeAdded.isEmpty() || teamNum >= eligibleApps.length) { // If the ready to be added tree is empty or the league is full
			        done = true; // The loop is done
			        break; // break the loop
			    }

			    if (eligibleApps[teamNum] == null) { // If the current team is null (New team)
			        eligibleApps[teamNum] = new AVLTree<>(); // Make a new tree to avoid null pointers
			    }

			    AVLNode<BasketballApplicant> currPlayer = readyToBeAdded.getRoot(); // The current player being added is taken from the root of the ready to be added tree
			    if (currPlayer == null) { // If the current player is null, then break the loop
			        done = true;
			        break;
			    }
			    
			    if (numEligible == 9) {          // If the number of eligible applicants reaches the limit
			        teamNum++; // Increment to the next team
			        numEligible = 0; // Reset the number eligible 
			        continue;        // Continue the loop
			    }
			    
			    BasketballApplicant data = currPlayer.getData(); // get the data from the current player
			    
			    eligibleApps[teamNum].insert(data); // Insert the current player data into the eligible applicants tree
			    
			    numEligible++; // Add to the number of eligible players
			    
			    readyToBeAdded.delete(data); // Delete the current player from the ready to be added tree
			}			
			System.out.println("Eligible Participants:"); // Display the eligible participants
			if(eligibleApps[0].isEmpty()) { // If the team collection is empty
				
				System.out.println("None"); // Display None
			}
			
			if(teamNum != 0) { // If the amount of teams is not only one
				
				for(int i = 0; i < teamNum; i++) { // For each of the teams in the collection of trees
					
					System.out.println("Team " + (i + 1)); // Display the current team
					
					eligibleApps[i].traverse(); // Display the team in order
					
				}
			}
			else { // If there is only one team
				eligibleApps[0].traverse(); // Only print the players on the single team
			}
			
			System.out.println(); 
			System.out.println("Eligible Participants that will be added space is opened up"); // Display the applicants ready to be added
			if(readyToBeAdded.isEmpty()) { // If the ready to be added list is empty, display None
				System.out.println("None"); 
				
			}
			else { // Else, traverse the tree in order
				readyToBeAdded.traverse();
			}
				
			System.out.println();
			System.out.println("Ineligible Participants:"); // Display the Ineligible applicants
			
			if(ineligibleApps.isEmpty()) { // If the tree is empty, display None
				System.out.println("None"); 
			}
			else { // Else, traverse the tree in order
				ineligibleApps.traverse(); 
			}
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

}
