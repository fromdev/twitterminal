package com.servlet;

import com.google.appengine.api.datastore.Entity;


public class RetweetJobServlet extends AbstractJobServlet {

	protected String getJobType() {
		return JobAction.RETWEET.name();
	}

	protected void performAction(String tweetId, String authenticateduser)
			throws Exception {
		TwitterUtil.retweet(tweetId, authenticateduser);
	}
	protected String getTarget(Entity jobEntity) {
		String target = Job.getTarget(jobEntity);
		String tweetId = target.substring(target.lastIndexOf("/")+1);
		return target;
	}
}