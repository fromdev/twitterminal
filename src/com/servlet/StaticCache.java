package com.servlet;

import java.util.List;
import java.util.Properties;

import twitter4j.Status;

import com.fromdev.automation.util.Cache;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class StaticCache {
	private static Cache<Entity> userCache = new Cache<>();
	private static Cache<Entity> jobCache = new Cache<>();
	private static Cache<Status> tweetCache = new Cache<>();
	private static Cache<String> termsCache = new Cache<>();
	private static Properties prop;

	public static Cache<Entity> getUserCache() {
		return userCache;
	}

	public static Cache<Entity> getJobCache() {
		return jobCache;
	}
	
	public static boolean jobCacheHasSomething() {
		return (StaticCache.getJobCache() != null && StaticCache.getJobCache().cache() != null && StaticCache.getJobCache().cache().size() > 0);
	}
	public static boolean userCacheHasSomething() {
		return (StaticCache.getUserCache() != null && StaticCache.getUserCache().cache() != null && StaticCache.getUserCache().cache().size() > 0);
	}

	public static Cache<Status> getTweetCache() {
		return tweetCache;
	}
	
	public static boolean tweetCacheHasSomething() {
		return (StaticCache.getTweetCache() != null && StaticCache.getTweetCache().cache() != null && StaticCache.getTweetCache().cache().size() > 0);
	}
	public static Cache<String> getTermsCache() {
		return termsCache;
	}
	
	public static boolean termsCacheHasSomething() {
		return (StaticCache.getTermsCache() != null && StaticCache.getTermsCache().cache() != null && StaticCache.getTermsCache().cache().size() > 0);
	}
	public static String cacheUsers() {
		String message = "";
		String entityType = "User";
		if (!StaticCache.userCacheHasSomething()) {
			try {
				DatastoreService datastore = DatastoreServiceFactory
						.getDatastoreService();
				Query q = new Query(entityType);
				PreparedQuery pq = datastore.prepare(q);
				List<Entity> entities = pq.asList(FetchOptions.Builder
						.withLimit(20));
				int count = 0;
				for (Entity entity : entities) {
					StaticCache.getUserCache().add(entity);
					count++;
				}
				message = "Cached : " +  entityType  + " "   + count;

			} catch (Exception e) {
				e.printStackTrace();
				message = "Error Caching : " + entityType  + " " + e.getMessage();
			}
		}
		return message;
	}
	
	public static String cacheJobs() {
		String message = "";
		String entityType = "Job";
		if (!StaticCache.jobCacheHasSomething()) {
			try {
				DatastoreService datastore = DatastoreServiceFactory
						.getDatastoreService();
				
				Query q = new Query(entityType);
				PreparedQuery pq = datastore.prepare(q);
				List<Entity> entities = pq.asList(FetchOptions.Builder
						.withLimit(20));
				int count = 0;
				for (Entity entity : entities) {
					StaticCache.getJobCache().add(entity);
					count++;
				}
				message = "Cached : " +  entityType  + " "   + count;
			} catch (Exception e) {
				e.printStackTrace();
				message = "Error Caching : " + entityType  + " " + e.getMessage();
			}
		}
		return message;
	}

	public static void setProp(Properties prop) {
		StaticCache.prop = prop;
	}
	
	public static Properties getProp() {
		return prop;
	}
	
	public static String getConsumerKey() {
		return "3gAmiNEAX4Ec2FA0WOu8bCCYV";
		//prop.setProperty("oauth.consumerSecret", "p2h0ZpQOZzpoXozM9q29o35JxLgozauQDc4IJgooUE6pGBoYVG");

		//return getProp().getProperty("oauth.consumerKey");
	}
	public static String getConsumerSecret() {
		return 	"p2h0ZpQOZzpoXozM9q29o35JxLgozauQDc4IJgooUE6pGBoYVG";

		//return getProp().getProperty( "oauth.consumerSecret");
	}
	
}
