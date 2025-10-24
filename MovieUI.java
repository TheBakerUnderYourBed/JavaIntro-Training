package moviedatabase;

import java.io.*;
import java.util.*;

/**
 * Class MovieUI handles the User Interface port of the lab.
 */
public class MovieUI {
	private Scanner _scanner;

	private ArrayList<Movie> movies;

	/**
	 * Construct a database for MovieUI.
	 */
	public MovieUI() {
		movies = new ArrayList<>();

		// Calls on loadMovies method to load the movies
		loadMovies();
	}

	/**
	 * Initializes the UI for the movies. creates a new scanner. Takes input to decide on which feature will be used.
	 * Quits the program based on user input.
	 *
	 */
	public void initializeUI() {
		_scanner = new Scanner(System.in);
		int input;
		boolean quit = false;

		System.out.println("** Movie Finder **");

		while (!quit) {
			input = getNumberInput(_scanner, 1, 4, showUiMenu());

			switch (input) {
				case 1 -> searchTitel();
				case 2 -> searchReviewScore();
				case 3 -> addMovie();
				case 4 -> {
					saveMoviesToFile();
					quit = true;
				}
			}
		}
		_scanner.close();
	}

	/**
	 * Get input and translate it to a valid number.
	 * 
	 * @param scanner the Scanner we use to get input
	 * @param min     the lowest number
	 * @param max     the highest number
	 * @param message message to user
	 * @return user input correlating to program feature
	 */
	private int getNumberInput(Scanner scanner, int min, int max, String message) {
		int input = -1;

		while (input < 0) {
			System.out.println(message);
			try {
				input = Integer.parseInt(scanner.nextLine().trim());
			} catch (NumberFormatException nfe) {
				input = -1;
			}
			if (input < min || input > max) {
				System.out.println("Invalid value");
				input = -1;
			}
		}
		return input;
	}

	/**
	 * This method seeks if a movie in the list of movies contains the user keyword and then shows the result
	 */
	private void searchTitel() {
		System.out.print("Enter keyword: ");
		String title = _scanner.nextLine().trim();

		for (Movie m : movies) {
			if (m.getTitle().contains(title))
				System.out.println(m.toString());
		}

	}

	/**
	 * This method takes an input from the user to display all the movies with a score equal or greater than the input
	 */
	private void searchReviewScore() {
		int review = getNumberInput(_scanner, 1, 5, "Enter minumum review score (1 - 5): ");

		for (Movie m : movies) {
			if (m.getScore() >= review)
				System.out.println(m.toString());
		}

	}

	/**
	 * This method adds a new movie by taking title and score input from the user.
	 */
	private void addMovie() {
		System.out.print("Title: ");
		String title = _scanner.nextLine().trim();
		int reviewScore = getNumberInput(_scanner, 1, 5, "Review score (1 - 5): ");
		movies.add(new Movie(title, reviewScore));
		saveMoviesToFile();
	}

	/**
	 * This method is the menu for the UI
	 */
	private String showUiMenu() {
		return "\n" + "1. Search title\n" + "2. Search review score\n" + "3. Add movie\n"
				+ "\n" + "4. Close program";
	}

	/**
	 * this method is for taking the information from movies.txt file
	 */
	private void loadMovies() {

		movies = new ArrayList<Movie>();
		try {
			File myObj = new File("resource//movies.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();

				String[] spl = data.split(",");

				movies.add(new Movie(spl[0], Integer.parseInt(spl[1])));
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	/**
	 * This method is used for adding new movies to the file
	 */
	private void saveMoviesToFile() {

		try {
			FileWriter myWriter = new FileWriter("resource//movies.txt");
			for (Movie m : movies) {
				myWriter.write(m.getTitle() + "," + m.getScore() + "\n");
			}
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

}