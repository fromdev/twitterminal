package com.servlet;

import com.fromdev.automation.util.StringUtil;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class Job {

	public static final String COUNT = "count";
	public static final String ACTION = "action";
	public static final String TARGET = "target";

	public static Entity createEntity(String name, String target,
			String action, long count) {

		Entity job = new Entity("Job", name);
		job.setProperty(TARGET, target);
		job.setProperty(ACTION, action);
		job.setProperty(COUNT, count);

		return job;
	}

	public static String getTarget(Entity entity) {
		return entity.getProperty(TARGET).toString();
	}

	public static String getAction(Entity entity) {
		return entity.getProperty(ACTION).toString();
	}

	public static String getCount(Entity entity) {
		return entity.getProperty(COUNT).toString();
	}

	public static void setCount(Entity entity, long count) {
		entity.setProperty(COUNT, count);
	}

	public static long getCountLong(Entity entity) {
		try {
			return Long.parseLong(entity.getProperty(COUNT).toString());
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			return 0l;
		}
	}

	public static String getName(Entity entity) {
		return entity.getKey().getName();
	}

	public static String deleteJob(String target) {
		String message;
		if (StringUtil.isNotNull(target)) {
			Key key = KeyFactory.createKey("Job", target);
			DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();
			datastore.delete(key);
			message = "Done";
		} else {
			message = "Missing Required Fields";
		}
		return message;
	}

	public static String update(Entity e) throws Exception {
		String message;
		if (e != null) {
			Key key = KeyFactory.createKey("Job", getTarget(e));
			DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();
			Entity j = datastore.get(key);
			j.setPropertiesFrom(e);
			datastore.put(j);
			message = "Done";
		} else {
			message = "Missing Required Fields";
		}
		return message;
	}

}
