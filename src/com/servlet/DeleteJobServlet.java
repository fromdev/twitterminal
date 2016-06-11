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
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class DeleteJobServlet extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String message = "";
		try {
			String target = req.getParameter(Job.TARGET);
			message = Job.deleteJob(target);
		} catch (Exception e) {
			System.out.println("#$#$#" + e.getMessage() + "#$#$#");
			message = "Error " + e.getMessage();
		}
		res.sendRedirect("home.jsp?message=" + message);
	}

	private long getLong(String v) {
		try {
			return Long.parseLong(v);
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

}