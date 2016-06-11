package com.servlet;

import com.google.appengine.api.datastore.Entity;

public class User {

	public static Entity createEntity(String name, String token,
			String tokenSecret) {

		Entity user = new Entity("User", name);
		user.setProperty("token", token);
		user.setProperty("tokenSecret", tokenSecret);

		return user;
	}

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
