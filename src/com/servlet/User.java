package com.servlet;

import com.google.appengine.api.datastore.Entity;

public class User {
	String consumerKey;
	String consumerSecret;
	String token;
	String tokenSecret;
	String name;
	
	public static Entity createEntity(String name,/*String consumerKey,	String consumerSecret,*/	String token,	String tokenSecret){
		
		Entity  user = new Entity ("User",name);
		//user.setProperty("consumerKey", consumerKey);
		//user.setProperty("consumerSecret", consumerSecret);
		user.setProperty("token",token);
		user.setProperty("tokenSecret",tokenSecret);
		
		return user;
	}

	/*public static String getConsumerKey(Entity entity) {
		return entity.getProperty("consumerKey").toString();
	}

	public static String getConsumerSecret(Entity entity) {
		return entity.getProperty("consumerSecret").toString();
	}*/

	public static String getToken(Entity entity) {
		return entity.getProperty("token").toString();
	}

	public static String getTokenSecret(Entity entity) {
		return entity.getProperty("tokenSecret").toString();
	}

	public static String getName(Entity entity) {
		return entity.getKey().getName();
	}
	
	
}
