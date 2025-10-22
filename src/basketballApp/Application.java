package basketballApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Application {
	
	public static void main(String args[]) {
		BinarySearchTree<BasketballApplicant> eligableApps = new BinarySearchTree<BasketballApplicant>();
		BinarySearchTree<BasketballApplicant> readyToBeAdded = new BinarySearchTree<BasketballApplicant>();
		BinarySearchTree<BasketballApplicant> inelligableApps = new BinarySearchTree<BasketballApplicant>();
		int numEligable = 0;
		boolean done = false;
		try {
			File applicantFile = new File("applicants.txt");
			Scanner scnr = new Scanner(applicantFile);
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
				else if(currAppAge == 10 || numEligable == 9) {
					readyToBeAdded.insert(currApp.applicationToDueDate(), currApp);
				}
				else{
					eligableApps.insert(currApp.applicationToDueDate(), currApp);
					++numEligable;
				}
			}
			
			while(!done) {
				if(readyToBeAdded.isEmpty() || numEligable == 9) {
					done = true;
				}
				else {
					int currKey = readyToBeAdded.root.key;
					eligableApps.insert(currKey, readyToBeAdded.findNode(currKey).data);
					readyToBeAdded.remove(currKey);
				}
			}
			
			System.out.println("Elligable Participants:");
			eligableApps.inOrderTraverseTree(eligableApps.root);
			System.out.println();
			System.out.println("Elligable Participants that will be added before space is filled up");
			readyToBeAdded.inOrderTraverseTree(readyToBeAdded.root);
			System.out.println();
			System.out.println("Inelligable Participants:");
			inelligableApps.inOrderTraverseTree(inelligableApps.root);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
