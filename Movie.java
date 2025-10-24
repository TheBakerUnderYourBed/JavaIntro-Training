package moviedatabase;

/**
 * Object focused on movie, movie construction
 */

public class Movie {

	private String title;
	private int score;

	public Movie() {

	}

	/**
	 * constructor with two parameter, used for adding movies
	 * @param title
	 * @param score
	 */

	public Movie(String title, int score) {
		this.title = title;
		this.score = score;
	}

	/**
	 * Used for accessing the title of movies
	 *
	 * @return returns the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Used for setting title
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * used for getting score
	 * @return returns the score
	 */

	public int getScore() {
		return score;
	}

	/**
	 * used for setting score
	 * @param score
	 */

	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @return returns the output.
	 */

	@Override
	public String toString() {
		return "Title: " + this.getTitle() + " Review score: " + this.getScore() + "/5";
	}

}
