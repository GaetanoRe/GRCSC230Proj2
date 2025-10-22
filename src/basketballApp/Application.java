package basketballApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Application {
	
	public static void main(String args[]) {
		BinarySearchTree<BasketballApplicant> tree = new BinarySearchTree<BasketballApplicant>();
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
				tree.insert(currApp.applicationToDueDate(), currApp);
			}
			scnr.close();
			tree.inOrderTraverseTree(tree.root);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
