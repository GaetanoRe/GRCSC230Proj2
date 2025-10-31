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
		BinarySearchTree<BasketballApplicant>[] eligableApps = new BinarySearchTree[6];
		BinarySearchTree<BasketballApplicant> readyToBeAdded = new BinarySearchTree<BasketballApplicant>();
		BinarySearchTree<BasketballApplicant> inelligableApps = new BinarySearchTree<BasketballApplicant>();
		int numEligable = 0;
		int teamNum = 0;
		boolean done = false;
		try {
			File applicantFile = new File("applicants.txt");
			Scanner scnr = new Scanner(applicantFile);
			eligableApps[teamNum] = new BinarySearchTree<BasketballApplicant>();
			while(scnr.hasNext()) {
				BasketballApplicant currApp;
				String currentLine = scnr.nextLine();
				String[] entry = currentLine.split(" ");
				String[] birthDate = entry[2].split("-");
				String[] appDate = entry[3].split("-");
				currApp = new BasketballApplicant(entry[0], entry[1], Integer.parseInt(birthDate[0]), Integer.parseInt(birthDate[1]), Integer.parseInt(birthDate[2]),
						Integer.parseInt(appDate[0]), Integer.parseInt(appDate[1]), Integer.parseInt(appDate[2]));
				int currAppAge = currApp.getAge();
				if(currAppAge < 9 || currAppAge > 10) {
					inelligableApps.insert(currApp.applicationToDueDate(), currApp);
				}
				else if(currAppAge == 10 || (teamNum == 5 && numEligable == 9)) {
					readyToBeAdded.insert(currApp.applicationToDueDate(), currApp);
				}
				else if(numEligable == 9) {
					teamNum++;
					eligableApps[teamNum] = new BinarySearchTree<BasketballApplicant>();
					numEligable = 0;
				}
				else{
					eligableApps[teamNum].insert(currApp.applicationToDueDate(), currApp);
					numEligable++;
				}
			}
			
			while(!done) {
				if(readyToBeAdded.isEmpty() || teamNum == 5) {
					done = true;
				}
				else {
					int currKey = readyToBeAdded.getRoot().key;
					if(numEligable == 9) {
						teamNum++;
						numEligable = 0;
					}
					eligableApps[teamNum].insert(currKey, readyToBeAdded.findNode(currKey).data);
					numEligable++;
					readyToBeAdded.remove(currKey);
				}
			}
			
			System.out.println("Eligable Participants:");
			if(teamNum != 0) {
				for(int i = 0; i < teamNum; i++) {
					System.out.println("Team " + (i + 1));
					eligableApps[i].inOrderTraverseTree(eligableApps[i].getRoot());
				}
			}
			else {
				eligableApps[0].inOrderTraverseTree(eligableApps[0].getRoot());
			}
			System.out.println();
			System.out.println("Eligable Participants that will be added space is opened up");
			readyToBeAdded.inOrderTraverseTree(readyToBeAdded.getRoot());
			System.out.println();
			System.out.println("Ineligable Participants:");
			inelligableApps.inOrderTraverseTree(inelligableApps.getRoot());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
