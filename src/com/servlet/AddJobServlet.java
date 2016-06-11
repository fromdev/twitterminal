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

public class AddJobServlet extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String message = "";
		try {

			String action = req.getParameter(Job.ACTION);
			String target = req.getParameter(Job.TARGET);
			if((!JobAction.FOLLOW.name().equalsIgnoreCase(action)) &&  target.indexOf("//") > -1) {
				target = target.substring(target.lastIndexOf("/")+1);
			}
			String countStr = req.getParameter(Job.COUNT);
			long count = getLong(countStr);
			message = addJob(target, action, count);
		} catch (Exception e) {
			System.out.println("#$#$#" + e.getMessage() + "#$#$#");
			message="Error " + e.getMessage();
		}
		res.sendRedirect("home.jsp?message=" + message);
	}

	private String addJob(String target, String action, long count) {
		String message;
		if (StringUtil.isNotNull(action) && StringUtil.isNotNull(target)
				&& count > 0) {
			Entity jobEntity = Job.createEntity(target, target, action,
					count);
			DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();
			datastore.put(jobEntity);
			message="Done";
		} else {
			message="Missing Required Fields";
		}
		return message;
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