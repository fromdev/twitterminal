package com.servlet;

import com.google.appengine.api.datastore.Entity;

public class FavoriteJobServlet extends AbstractJobServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2671482780792752062L;

	protected String getJobType() {
		return JobAction.FAVORITE.name();
	}

	protected void performAction(String tweetId, String authenticateduser)
			throws Exception {
		TwitterUtil.createFavorite(Long.valueOf(tweetId), authenticateduser);
	}
	protected String getTarget(Entity jobEntity) {
		String target = Job.getTarget(jobEntity);
		String tweetId = target.substring(target.lastIndexOf("/")+1);
		return target;
	}

}