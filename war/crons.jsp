<%@ page import="com.google.appengine.api.datastore.Query"%>
<%@ page import="com.google.appengine.api.datastore.Query.SortDirection"%>
<%@ page import="com.google.appengine.api.datastore.*"%>
<%@ page import="java.util.List"%>
<%@ page import="com.servlet.User"%>
<%@ page import="com.servlet.*"%>
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
				<li><a href="/cron/followjob" target="_self">Run Follow Cron</a></li>
				<li><a href="/cron/favoritejob" target="_self">Run Favorite Cron</a></li>
				<li><a href="/cron/retweetjob" target="_self">Run Retweet Cron</a></li>
				<li><a href="/cron/autofavorite" target="_self">Run AutoFav Cron</a></li>
				</ul>
			</div>
		</div>
	</nav>


 <footer id="footer">  <p>Copyright 2013 FromDev.Contact information: <a href="mailto:sachin@fromdev.com">  sachin@fromdev.com</a>.</p>
</footer>

</body>
</html>
