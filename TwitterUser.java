/**
 * This class describes a user of Twitter. A user has a user ID (e.g. @testudo),
 * a list of tweets, a count of the number of followers, and a list of users
 * that this user follows. The user may contain other static or instance
 * variables. In fact, you will need some to keep track of information such as
 * the number of tweets this user has had, or the maximum number of followers
 * allowed.
 * 
 * You may NOT import any library class.
 * 
 * @author Jonathan Chu
 *
 */
public class TwitterUser {
	

	public static final int MAX_TWEETS = 140, MAX_FOLLOWING = 140;

	// ADD YOUR INSTANCE/STATIC VARIABLES HERE
	private String userID;
	private int numOfTweets;
	private long numFollowers;
	private int numFollowing;
	private Tweet[] userTweets = new Tweet[MAX_TWEETS];
	private String[] followingList = new String[MAX_FOLLOWING];

	/**
	 * A constructor that takes the user's ID. If the ID is null, or longer than 32
	 * characters, or it does not start with "@", throw an IllegalArgumentException.
	 * The ID may contain any ASCII characters, so you don't have to worry about
	 * checking for special characters. Also do not worry about checking whether any
	 * other user has the same ID.
	 * 
	 * All other instance variables should be initialized appropriately.
	 * 
	 * @param userID the ID of the new user
	 */
	public TwitterUser(String userID) {
		if (userID == null || userID.length() > 32 || userID.charAt(0) != '@') {
			throw new IllegalArgumentException();
		}
		this.userID = userID;

		

	}

	/**
	 * A constructor that takes an existing user as well as a new userID. It should
	 * create a new user with the newID given (which has the same requirements as
	 * above), and the same tweets as the old user that is being passed in.
	 * Information about the number of followers or list of users the old user is
	 * following should not be copied. All other instance variables should be
	 * initialized appropriately. It may be a good idea to call the other
	 * constructor here.
	 * 
	 * @param old   the existing TwitterUser whose tweets we are copying
	 * @param newID the ID of the new user to be created
	 */
	public TwitterUser(TwitterUser old, String newID) {
		// calling first constructor with newID param
		this(newID);
		
		for (int i = 0; i < userTweets.length;i++) { // deep copy of tweets
			if (old.userTweets[i] == null) {
				break;
			}

			userTweets[i] = old.userTweets[i];
		}
		

	}

	/**
	 * Creates a new Tweet object using the given message and adds it to this user's
	 * array of tweets. Our version of Twitter has no requirements on the length of
	 * the message.
	 * 
	 * Note: If the String passed in is null OR if the user has already reached the
	 * maximum number of tweets, then this method will not add anything to the array
	 * and simply return false. It will return true if it was able to add to the
	 * array.
	 * 
	 * You may have to update other instance variables in this process.
	 * 
	 * @param message the message of the tweet
	 * @return true if the tweet was added to the array, false otherwise
	 */
	public boolean tweet(String message) {
		if (message == null || getNumTweets() >= MAX_TWEETS) {
			return false;
		}
		Tweet T = new Tweet(message);
		userTweets[numOfTweets] = T;
		numOfTweets++;
		return true;
	}

	/**
	 * This method allows the user to retweet an already existing tweet. The user
	 * can only do this if they have not reached their maximum allowance of tweets.
	 * You may have to make appropriate changes in the Tweet Object.
	 * 
	 * @param t the Tweet Object
	 * @return true if the tweet was added to the array, false otherwise
	 */
	public boolean retweet(Tweet t) {
		if (getNumTweets() < MAX_TWEETS) {
			for(int i = 0; i < getNumTweets(); i++) {
				userTweets[i] = t;
			}
			t.retweet();
			return true;
		}
		return false;
	}

	/**
	 * This method retweets all the tweets of the TwitterUser Object that is passed
	 * in. It will return true if ALL the tweets could be retweeted, and false if at
	 * least one tweet could not be retweeted. In other words, if the current user
	 * reaches the maximum allowance of tweets midway through the array of the other
	 * user's tweets, then the tweets that are allowed to be added will be added,
	 * but the method will return false because not all could be retweeted.
	 * 
	 * @param u the TwitterUser whose tweets are to be retweeted
	 * @return true if all tweets could be retweeted, false otherwise
	 */
	public boolean retweetAll(TwitterUser u) {
		
		int lastTweetIndex = getNumTweets(); // gets the last position in our array to continue appending tweets
		
		for (int i = 0; i < u.getNumTweets(); i++) { // iterate to add retweets to array as long as we have yet to exceed max tweets
			if (getNumTweets() < MAX_TWEETS) {
				userTweets[i+lastTweetIndex] = u.userTweets[i];
			}
			else {
				return false;
			}
			
		}
		return true;
	}

	/**
	 * Like all the tweets of the TwitterUser that is passed in. It would be a good
	 * idea to look at the like() method of the Tweet class.
	 * 
	 * @param u the TwitterUser whose tweets are to be liked
	 */
	public void likeAll(TwitterUser u) {
		
		for (int i = 0; i < u.getNumTweets(); i++) {
			u.userTweets[i].like();
			
			
			
		}	
		
	
		
		
	}
	

	/**
	 * 
	 * @return the number of followers this user has
	 */
	public long getNumFollowers() {
		
		return numFollowers;
	}

	/**
	 * 
	 * @return the userID of this user
	 */
	public String getUsername() {
		return userID;
	}

	/**
	 * 
	 * @return the number of tweets this user has tweeted or retweeted
	 */
	public int getNumTweets() {
		int count = 0;
		for (int i = 0; i < userTweets.length; i++) { // counts the number of tweets in userTweets
			if (userTweets[i] != null) {
				count++;
			}
			
		}
		
		numOfTweets = count;
		return numOfTweets;
	}

	/**
	 * 
	 * @return the number of users this user follows
	 */
	public int getNumFollowing() {
		int count = 0;
		for (int i = 0; i < followingList.length; i++) {
			if (followingList[i] != null) {
				count++;
			}
		}
		
		numFollowing = count;
		return numFollowing;
	}

	/**
	 * Given an index, return the text of the Tweet at that index in the array.
	 * Return null if the index is negative, or greater than or equal to the current
	 * number of tweets.
	 * 
	 * @param index the index of the desired tweet
	 * @return the text of the tweet at index if index is valid, null otherwise
	 */
	public String getTweet(int index) {
		if (index < 0 || index >= getNumTweets()) {
			return null;
		}
		return userTweets[index].getText();
	}

	/**
	 * This method helps the current user follow the user that is passed in. If the
	 * current user has not reached the maximum allowance for the number of accounts
	 * to follow, AND the TwitterUser passed in is not already being followed by the
	 * current user, then add the given user to this user's list. You may have to
	 * make appropriate changes to this user's and/or the other user's instance
	 * variables. This method will probably rely on the equals method you write.
	 * 
	 * @param u the user to be followed
	 * @return true if you were able to add u to this user's array, false otherwise
	 */

	public boolean follow(TwitterUser u) {
		boolean isFollowing = false;
		
		
		for (int i = 0; i < followingList.length; i++) { // iterate to check and see if followingList contains already followed user
			if (followingList[i] == null) {
				continue;
			}
			
			else if(followingList[i].equals(u.getUsername())) {

				isFollowing = true;
			}

		}
		if (getNumFollowing() < MAX_FOLLOWING && isFollowing == false) { 
			followingList[getNumFollowing()] = u.getUsername(); // assigns u username to our string array;
			isFollowing = true;
			
			
		}
		
		if ((getNumFollowing() < MAX_FOLLOWING) && isFollowing) {
			u.numFollowers++; // increments numFollowers instance var to show that the current user has followed u
			
			return true;

		}
		return false;
	}

	/**
	 * Checks if the Object passed in is logically equal to the current TwitterUser
	 * object. For the purpose of this project, two TwitterUsers are equal if they
	 * both have the same userID. You do not need to check for any other fields.
	 */
	public boolean equals(Object other) {
		
		TwitterUser temp = (TwitterUser) other;
		
		char[] currentUserArr = getUsername().toCharArray();
		char[] otherUserArr = temp.getUsername().toCharArray();
		
		if (currentUserArr.length == otherUserArr.length) { // checks to see if the length of both char arrs are equal
			for (int i = 0; i < currentUserArr.length; i++) {
				if (currentUserArr[i] != otherUserArr[i]) { // iteratively check if the characters are the same
					return false; 
				}
			}
		}
		else { // if length is not equal automatically return false
			return false;
		}
		
		return true;
	}
}
