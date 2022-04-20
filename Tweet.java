/**
 * This class describes a tweet. A tweet has a message in it, a unique ID,
 * a count of the number of likes, and a count of the number of times it
 * has been retweeted. In addition, the Tweet class will have a static 
 * variable to count the total number of tweets to ever be created 
 * (retweets don't count as a new tweet).
 * 
 * You may NOT import any library class.
 * 
 * @author Jonathan Chu
 *
 */
public class Tweet {

	// ADD YOUR INSTANCE/STATIC VARIABLES HERE
	private String message;
	private long likeCount;
	private long retweets;
	private static long ID = 0;
	private static long totalNumTweets = 0;

	/**
	 * This constructor creates a tweet with the given message.
	 * Assume that the message is not null because only the tweet() method
	 * from the TwitterUser class will call this constructor. There are
	 * no length requirements on Tweets.
	 * 
	 * The very first tweet to ever be created will have an ID of 0, the 
	 * next one will have 1, and so on and so forth. It may help you to use
	 * the static count variable to set the ID. 
	 * 
	 * You will have to initialize other instance variables appropriately.
	 * @param message the text of the tweet
	 */
	public Tweet(String message) {
		this.message = message;
		ID++;
		totalNumTweets++;
		
	}

	/**
	 * Makes appropriate changes in the instance variables to reflect that
	 * this tweet has been retweeted.
	 */
	public void retweet() {
		retweets++;
	}

	/**
	 * Makes appropriate changes in the instance variables to reflect that
	 * this tweet has been liked. 
	 */
	public void like() {
		likeCount++;
	}


	/**
	 * 
	 * @return the ID of this tweet
	 */
	public long getID() {
		return ID;
	}

	/**
	 * 
	 * @return the text of this tweet
	 */
	public String getText() {
		return message;
	}

	/**
	 * 
	 * @return the number of likes this tweet has received
	 */
	public long getNumLikes() {
		return likeCount;
	}

	/**
	 * 
	 * @return the number of times this tweet has been retweeted
	 */
	public long getNumRetweets() {
		return retweets;
	}

	/**
	 * 
	 * @return the total number of tweets to ever be created
	 */
	public static long getNumTweets() {
		return totalNumTweets;
	}
}
