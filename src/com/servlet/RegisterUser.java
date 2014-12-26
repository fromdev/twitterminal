package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

public class RegisterUser extends HttpServlet {
	//static String CONSUMER_KEY = "tXRbRrj3dEHxxhzEarlvlCsvJ";
	//static String CONSUMER_SECRET = "5nqlDmTCFm6jk0ztBDhBrhngCrXs5rLaexus4WO2GCHJJI9mqd";
	
	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		/*String consumerKey = req.getParameter("consumerKey");
		String consumerSecret = req.getParameter("consumerSecret");
		*/
		try {
			//ConfigurationBuilder builder = new ConfigurationBuilder();
			//builder.setOAuthConsumerKey(consumerKey);
			//builder.setOAuthConsumerSecret(consumerSecret);
			//builder.setHttpProxyHost("www-proxy.us.oracle.com");
			//builder.setHttpProxyPort(80);
			
			Twitter twitter = new TwitterFactory(/*builder.build()*/).getInstance();
			// twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
			RequestToken requestToken = twitter.getOAuthRequestToken();

			/*req.getSession().setAttribute("consumerKey", consumerKey);
			req.getSession().setAttribute("consumerSecret", consumerSecret);
			*/
			req.getSession().setAttribute("Twitter", twitter);
			req.getSession().setAttribute("RequestToken", requestToken);
			
			res.sendRedirect(requestToken.getAuthorizationURL());
			
		} catch (Exception e) {
			e.printStackTrace();
			res.sendRedirect("home.jsp?message=There%20was%20a%20problem%20with%20your%20network%20connection.");
		}
	}
}