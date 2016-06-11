package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.TwitterException;

import com.fromdev.automation.util.StringUtil;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public abstract class AbstractJobServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1574348035071280560L;

	public AbstractJobServlet() {
		super();
	}

	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String message = "";
		res.setContentType("text/plain");
		try {
			StaticCache.cacheJobs();
			StaticCache.cacheUsers();
			if (StaticCache.jobCacheHasSomething()
					&& StaticCache.userCacheHasSomething()) {
				Entity jobEntity = StaticCache.getJobCache().cache().get(0);
				long count = Job.getCountLong(jobEntity);
				String action = Job.getAction(jobEntity);
				if (getJobType().equalsIgnoreCase(action)) {
					String target = getTarget(jobEntity);
					Entity userEntity = StaticCache.getUserCache()
							.getRandomItem();
					String authenticateduser = User.getName(userEntity);

					performAction(target, authenticateduser);
					count--;
					Job.setCount(jobEntity, count);
					DatastoreService datastore = DatastoreServiceFactory
							.getDatastoreService();
					if (count <= 0) {
						Job.deleteJob(target);
						StaticCache.getJobCache().cache().remove(0);
						System.out.println("deleting job");
						message += " DELETED JOB ";
					} else {
						Job.update(jobEntity);
						message += " UPDATED JOB " + count;
					}
					message += " SUCCESS " + authenticateduser + " "
							+ getJobType() + " " + target;
				}
			} else {
				message += "NothingTODO: UsersCacheHasSomething="
						+ StaticCache.userCacheHasSomething()
						+ " JobsCacheHasSomething="
						+ StaticCache.jobCacheHasSomething();
			}
		} catch (TwitterException e) {
			String error = e.getErrorMessage();
			System.out.println("#$#$# Twitter " + StringUtil.getStackTrace(e) + "#$#$#");
			if (error == null) {
				if (e.getMessage().indexOf("\"errors\":\"") != -1) {
					int startIndex = e.getMessage().indexOf("\"errors\":\"") + 10;
					if (e.getMessage().indexOf("\"", startIndex) != -1)
						error = e.getMessage().substring(startIndex,
								e.getMessage().indexOf("\"", startIndex));
				}
			}
			if (error == null)
				error = "There was a problem with your network connection.";
			message = "Error " + StringUtil.getStackTrace(e);
		} catch (Exception e) {
			System.out.println("#$#$#" + StringUtil.getStackTrace(e) + "#$#$#");
			message = ("Error " + StringUtil.getStackTrace(e));
		}
		res.getWriter().println("Done [" + message + "]");
	}

	protected abstract String getTarget(Entity jobEntity);

	protected abstract String getJobType();

	protected abstract void performAction(String followuser,
			String authenticateduser) throws Exception;

}