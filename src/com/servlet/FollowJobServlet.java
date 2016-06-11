package com.servlet;

import twitter4j.TwitterException;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;

public class FollowJobServlet extends AbstractJobServlet {

	protected String getJobType() {
		return JobAction.FOLLOW.name();
	}

	protected void performAction(String followuser, String authenticateduser)
			throws EntityNotFoundException, TwitterException {
		TwitterUtil.createFriendship(followuser, authenticateduser);
	}

	@Override
	protected String getTarget(Entity jobEntity) {
		return Job.getTarget(jobEntity);
	}
	

}