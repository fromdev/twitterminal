<%@ page import="com.google.appengine.api.datastore.Query"%>
<%@ page import="com.google.appengine.api.datastore.Query.SortDirection"%>
<%@ page import="com.google.appengine.api.datastore.*"%>
<%@ page import="java.util.List"%>
<%@ page import="com.servlet.User"%>
<!DOCTYPE html>
<style type="text/css">
		#footer {width:100%;height:80px;position:absolute;bottom:0;	left:0;}
		.form-group.required .control-label:after {  content:" *";color:red;}
	</style>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Twitter Application on App Engine</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>
<body>

	<nav id="myNavbar"
		class="navbar navbar-default navbar-inverse navbar-fixed-top"
		role="navigation">
		<div class="container-fluid">
			<div class="collapse navbar-collapse" id="navbarCollapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="home.jsp" target="_self">Registered
							Users</a></li>
				<!-- 	<li><a href="login.jsp" target="_self">Login</a></li>
				 -->	<li><a href="follow.jsp" target="_self">Follow</a></li>
				<li><a href="favorite.jsp" target="_self">Favorite</a></li>
				<li><a href="retweet.jsp" target="_self">Retweet</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<form class="form-horizontal" action="favoriteservlet">
		<br />
		<br />
		<br />
		<h2>Favorite a Tweet</h2>
		<br/><br/>
		<div class="form-group required">
			<label for="tweeturl" class="control-label col-xs-2">Tweet URL</label>
			<div class="col-xs-4">
				<input type="url" class="form-control" name="tweeturl" required="required"
					placeholder="Tweet URL">
			</div>
		</div>
		<br />
		<div class="form-group required">
			<label for="authenticateduser" class="control-label col-xs-2">Authenticated
				User</label> 
				<div class="col-xs-4">
		<select name="authenticateduser" class="btn btn-default btn-lg btn-block">

				
					<%
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Query q = new Query("User");
	PreparedQuery pq = datastore.prepare(q);
	List<Entity> entities = pq.asList(FetchOptions.Builder.withLimit(20));
	for(Entity entity : entities)
		{
		
		
    %>
					<option><%= User.getName(entity) %></option>
	<%
    	}
    %>
				
			</select>
			 </div>
		</div>
		
		<div class="form-group">
			<div class="col-xs-offset-2 col-xs-10">
				<button type="submit" class="btn btn-primary">Favorite</button>
			</div>
		</div>
	</form>
 <footer id="footer">  <p>Copyright 2013 FromDev.Contact information: <a href="mailto:sachin@fromdev.com">  sachin@fromdev.com</a>.</p>
</footer>

</body>
</html>
