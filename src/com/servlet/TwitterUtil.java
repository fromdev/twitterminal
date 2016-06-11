package com.servlet;

import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import com.fromdev.automation.util.StringUtil;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class TwitterUtil {
	public static void createFriendship(String followuser,
			String authenticateduser) throws EntityNotFoundException,
			TwitterException {
		if (StringUtil.isNotNull(followuser)
				&& StringUtil.isNotNull(authenticateduser)) {
			DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();
			Key key = KeyFactory.createKey("User", authenticateduser);
			Entity entity = datastore.get(key);

			Twitter twitter = new TwitterFactory().getInstance();
			//twitter.setOAuthConsumer(StaticCache.getConsumerKey(),StaticCache.getConsumerSecret());
			twitter.setOAuthAccessToken(new AccessToken(User.getToken(entity),
					User.getTokenSecret(entity)));

			twitter.createFriendship(followuser);
		}
	}

	public static void createFavorite(long tweetId, String authenticateduser)
			throws Exception {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Key key = KeyFactory.createKey("User", authenticateduser);
		Entity entity = datastore.get(key);
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(StaticCache.getConsumerKey(),StaticCache.getConsumerSecret());
		twitter.setOAuthAccessToken(new AccessToken(User.getToken(entity), User
				.getTokenSecret(entity)));

		twitter.createFavorite(tweetId);
	}

	public static void retweet(String authenticateduser, String tweetId)
			throws EntityNotFoundException, TwitterException {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Key key = KeyFactory.createKey("User", authenticateduser);
		Entity entity = datastore.get(key);

		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(StaticCache.getConsumerKey(),StaticCache.getConsumerSecret());
		twitter.setOAuthAccessToken(new AccessToken(User.getToken(entity), User
				.getTokenSecret(entity)));
		twitter.retweetStatus(Long.valueOf(tweetId));
	}

	public static List<Status> search(String term) throws Exception {
		Twitter twitter = new TwitterFactory().getInstance();
		Query query = new Query(term);
		QueryResult result;
		result = twitter.search(query);
		List<Status> tweets = result.getTweets();
		int count = 0;
		for (Status tweet : tweets) {
			System.out.println("@" + tweet.getUser().getScreenName() + " - "
					+ tweet.getText() + " " + tweet.getId());
		}
		return tweets;
	}
	
	public static void main(String[] args) throws Exception {
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer("3gAmiNEAX4Ec2FA0WOu8bCCYV","p2h0ZpQOZzpoXozM9q29o35JxLgozauQDc4IJgooUE6pGBoYVG");
		twitter.setOAuthAccessToken(new AccessToken("341947256-pphYOhTHCDdvJAgc6EXTHmr6bIFJng20Yd8eVZ5u",
				"sv9B89tj2dcwsm3yZCAADBmAOBQ6vEr3PsVwpvOiBjING"));

		twitter4j.User u = twitter.createFriendship("jbwinkin");
		System.out.println("Done " + u.getFriendsCount() );
	}

}
