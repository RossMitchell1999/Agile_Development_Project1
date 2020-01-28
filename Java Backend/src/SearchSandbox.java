import java.util.Arrays;
import java.util.Scanner;

public class SearchSandbox {
	
	static String[] splitData;
	static int count_words;

	public void splitter(String s) { // Used for when searching by procedure/condition and based by description
		splitData = s.split(" "); 
		count_words = splitData.length;
		
		System.out.println(Arrays.toString(splitData));
	}
	
	// Sorts starting with the by longest word in case we would only want to search for some of them instead of the whole user input
	static void sort(String []s, int n)
	{ 
	    for (int i=1 ;i<n; i++) 
	    { 
	        String temp = s[i]; 
	  
	        // Insert s[j] at its correct position 
	        int j = i - 1; 
	        while (j >= 0 && temp.length() > s[j].length()) 
	        { 
	            s[j+1] = s[j]; 
	            j--; 
	        } 
	        s[j+1] = temp; 
	    } 
	} 
	
	public void countWords() {
		System.out.println(count_words);
		
		if (count_words == 0) {
			System.out.println("Input can't be empty");
		}
		
		if (count_words == 1) {
			System.out.println(count_words);
		}

	}
	
	// Search that would be used for DRG Description
	public void drgSearch() {
		System.out.println("Enter DRG Code:");
		Scanner s = new Scanner(System.in);  // Create a Scanner object
		String drg = s.nextLine();  // Read user input

        /*Statement statement = connection.createStatement();
		ResultSet results = statement.executeQuery("SELECT * FROM medicalprovidercharges WHERE Definition LIKE '%" + drg + "%';");

				while (results.next())
				{
					String outputString =
						String.format(
							"Data row = (%s, %s, %s)",
							results.getString(1),
							results.getString(2),
							results.getString(3));
					System.out.println(outputString);
				}
		*/
	}
	
	public static void main(String[] args) {
		Test test = new Test();
		
		System.out.println("Enter description");
		Scanner s = new Scanner(System.in);  // Create a Scanner object
	    String desc = s.nextLine();  // Read user input

		test.splitter(desc);
		Test.sort(splitData, count_words);
		System.out.println(Arrays.toString(splitData));
		test.countWords();

	}
}
