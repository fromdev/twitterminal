package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.Twitter;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class ReturnServlet extends HttpServlet {
	//static String CONSUMER_KEY = "tXRbRrj3dEHxxhzEarlvlCsvJ";
	//static String CONSUMER_SECRET = "5nqlDmTCFm6jk0ztBDhBrhngCrXs5rLaexus4WO2GCHJJI9mqd";
	
	public void service(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException{
		
		try {
			Twitter twitter = (Twitter )req.getSession().getAttribute("Twitter");
			RequestToken token = (RequestToken )req.getSession().getAttribute("RequestToken");
			String verifier = req.getParameter("oauth_verifier");
			String oauthToken = req.getParameter("oauth_token");
			/*String consumerKey = (String )req.getSession().getAttribute("consumerKey");
			String consumerSecret = (String )req.getSession().getAttribute("consumerSecret");
			*/
			System.out.println(token.getToken());
			System.out.println(token.getTokenSecret());
			System.out.println(verifier);
			System.out.println(oauthToken);
			
			AccessToken accTok = twitter.getOAuthAccessToken(token, verifier);
			System.out.println(accTok.getToken());
			System.out.println(accTok.getTokenSecret());
			
			Entity user =  User.createEntity (twitter.getScreenName(),accTok.getToken(),accTok.getTokenSecret());
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			datastore.put(user);
		    
		    /*System.out.println(user.getKey().getId());
		    
			Twitter twitter1 = new TwitterFactory().getInstance();
			twitter1.setOAuthConsumer(consumerKey,consumerSecret);
		    twitter1.setOAuthAccessToken(new AccessToken(accTok.getToken(), accTok.getTokenSecret()));
		    //twitter1.updateStatus("hello all.");
			res.getWriter().append(twitter1.getScreenName());*/
			
			res.sendRedirect("home.jsp?message=New%20user%20"+twitter.getScreenName()+"%20is%20is%20registered");
		} catch (Exception e) {
			e.printStackTrace();
			res.sendRedirect("home.jsp?message=There%20was%20a%20problem%20with%20your%20network%20connection.");
		}
		
	}
}