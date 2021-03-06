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

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class RetweetServlet extends HttpServlet {
	
	public void service(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException{
		
		try {
			String tweeturl = req.getParameter("tweeturl");
			String authenticateduser = req.getParameter("authenticateduser");
			

			//twitter.setOAuthConsumer(User.getConsumerKey(entity),User.getConsumerSecret(entity));
			String tweetId = tweeturl.substring(tweeturl.lastIndexOf("/")+1);
			System.out.println(tweetId);
			
			TwitterUtil.retweet(authenticateduser, tweetId);
			
			res.sendRedirect("home.jsp?message="+tweetId+"%20is%20marked%20as%20retweet");
		} catch(NumberFormatException e){
			res.sendRedirect("home.jsp?message=Can't find the tweet " + e.getMessage());
		} catch (TwitterException e) {
			String error = e.getErrorMessage();
			System.out.println("#$#$#"+e.getMessage()+"#$#$#");
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
			System.out.println("#$#$#"+e.getMessage()+"#$#$#");
			res.sendRedirect("home.jsp?message=There was a problem with your network connection.");			
		}
	}

}