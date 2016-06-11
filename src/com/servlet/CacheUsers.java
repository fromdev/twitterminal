package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class CacheUsers extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4769960124542566076L;

	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		StaticCache.cacheUsers();
	}
}