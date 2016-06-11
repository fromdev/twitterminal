package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import com.fromdev.automation.util.StringUtil;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class FollowServlet extends HttpServlet {
	
	public void service(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException{
		
		try {
			String followuser = req.getParameter("followuser");
			String authenticateduser = req.getParameter("authenticateduser");
			
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Key key = KeyFactory.createKey("User",authenticateduser);		   
			Entity entity = datastore.get(key);
			System.out.println("Step 1 " + authenticateduser);
			Twitter twitter = new TwitterFactory().getInstance();
			//twitter.setOAuthConsumer(User.getConsumerKey(entity),User.getConsumerSecret(entity));
		    twitter.setOAuthAccessToken(new AccessToken(User.getToken(entity),User.getTokenSecret(entity)));
			System.out.println("Step 2 " + User.getToken(entity));
			
		    twitter4j.User followed = twitter.createFriendship(followuser);
		    System.out.println("Step 3 " + followed.getFollowersCount());
			res.sendRedirect("home.jsp?message="+authenticateduser+"%20now%20following%20"+followuser);
		} catch (TwitterException e) {
			String error = e.getErrorMessage();
			System.out.println("Stacktrace Tw"+StringUtil.getStackTrace(e)+"#$#$#");
			if(error ==null){
				if(e.getMessage().indexOf("\"errors\":\"") !=-1){
					int startIndex = e.getMessage().indexOf("\"errors\":\"") + 10;
					if(e.getMessage().indexOf("\"",startIndex) != -1)
						error = e.getMessage().substring( startIndex, e.getMessage().indexOf("\"",startIndex));
				}					
			}
			if(error == null)
				error="There was a problem with your network connection.";					
			res.sendRedirect("home.jsp?message="+error);
		} catch (Exception e) {
			System.out.println("Stacktrace e "+StringUtil.getStackTrace(e)+"#$#$#");
			res.sendRedirect("home.jsp?message=There was a problem with your network connection.");			
		}
		
	}
}