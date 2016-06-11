package com.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.Status;

import com.fromdev.automation.util.StringUtil;
import com.google.appengine.api.datastore.Entity;

public class AutoFavoriteJobServlet extends HttpServlet {

	private Logger log = Logger.getLogger(AutoFavoriteJobServlet.class
			.getName());
	private String termsFilePath = "https://github.com/fromdev/fromdev-static/raw/gh-pages/release/twitter-keywords.txt";

	@Override
	public void init(ServletConfig conf) throws ServletException {
		super.init();
		String path = conf.getInitParameter("termsFilePath");
		if (!StringUtil.isNullOrEmpty(path)) {
			termsFilePath = path;
		}
	}

	private void initProps() {
		if (StaticCache.getProp() == null) {
			InputStream in = this.getClass().getResourceAsStream(
					"twitter4j.properties");
			Properties prop = new Properties();
			if (in != null) {
				
				try {
					prop.load(in);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				/*
				 */
				prop.setProperty("oauth.consumerKey", "3gAmiNEAX4Ec2FA0WOu8bCCYV");
				prop.setProperty("oauth.consumerSecret", "p2h0ZpQOZzpoXozM9q29o35JxLgozauQDc4IJgooUE6pGBoYVG");
			}
			StaticCache.setProp(prop);
		}
	}

	private void cacheTerms() {
		if (!StaticCache.termsCacheHasSomething()
				&& !StringUtil.isNullOrEmpty(termsFilePath)) {
			String[] file = StringUtil
					.readRemoteFileAsStringArray(termsFilePath);
			if (file != null && file.length > 0) {
				StaticCache.getTermsCache().cache().addAll(Arrays.asList(file));
			}
		}
	}

	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String message = "";
		try {
			initProps();
			cacheTerms();
			cacheTweets(message);
			Status tweet = StaticCache.getTweetCache().getRandomItem();
			Entity userEntity = StaticCache.getUserCache().getRandomItem(false);
			TwitterUtil.createFavorite(tweet.getId(), User.getName(userEntity));
			message += "FAV " + tweet.getText() + " - "
					+ User.getName(userEntity);
		} catch (Exception e) {
			System.out.println(StringUtil.getStackTrace(e));
			message = "Error "  + StaticCache.getConsumerKey() + " " + StringUtil.getStackTrace(e);
			log.log(Level.SEVERE, message);
		}
		res.sendRedirect("home.jsp?message=" + message);
	}

	private void cacheTweets(String message) throws Exception {
		if (!StaticCache.tweetCacheHasSomething()) {
			String term = StaticCache.getTermsCache().getRandomItem();
			List<Status> tweets = TwitterUtil.search(term);
			StaticCache.getTweetCache().cache().addAll(tweets);
		}
	}

}