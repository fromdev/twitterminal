<%@ page import="com.google.appengine.api.datastore.Query" %>
<%@ page import="com.google.appengine.api.datastore.Query.SortDirection" %>
<%@ page import = "com.google.appengine.api.datastore.*" %>
<%@ page import = "java.util.List" %>
<%@ page import = "com.servlet.User" %>
<!DOCTYPE html>
<style type="text/css">
		#footer {width:100%;height:80px;position:absolute;bottom:0;	left:0;}
	</style>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Twitter Application on App Engine</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

</head>
<body>
    
<nav id="myNavbar" class="navbar navbar-default navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="home.jsp" target="_self">Registered Users</a></li>
              <!--   <li><a href="login.jsp" target="_self">Login</a></li>
				 --><li><a href="follow.jsp" target="_self">Follow</a></li>
				<li><a href="favorite.jsp" target="_self">Favorite</a></li>
				<li><a href="retweet.jsp" target="_self">Retweet</a></li>
            </ul>
        </div>
    </div>
</nav>

<br/><br/><br/>
<% if(request.getParameter("message") !=null) { %>
<div class="alert alert-warning">
    <a href="#" class="close" data-dismiss="alert">&times;</a>
    <strong>Alert!</strong> <%= request.getParameter("message") %>
</div>
<%} %>
<h2>List of Registered  Users</h2>
<br/>
<table class="table table-striped">
    <thead>
        <tr>
            <th>Screen Name</th>
      <!--       <th>Consumer key</th>
            <th>Consumer Secret</th>
       -->      <th>Token</th>
            <th>Token Secret</th>
        </tr>
    </thead>
    <tbody>
    
    <%
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Query q = new Query("User");
	PreparedQuery pq = datastore.prepare(q);
	List<Entity> entities = pq.asList(FetchOptions.Builder.withLimit(20));
	for(Entity entity : entities)
		{
		
		
    %>
        <tr>
            <td><%= User.getName(entity) %></td>
          <%--   <td><%= User.getConsumerKey(entity) %></td>
            <td><%= User.getConsumerSecret(entity) %></td>
           --%>  <td><%= User.getToken(entity) %></td>
            <td><%= User.getTokenSecret(entity) %></td>
        </tr>
    <%
    	}
    %>
    
     </tbody>
</table>
<br/><br/>
<div class="form-group">
			<div class="col-xs-10">
				<button type="submit" onclick="window.location.href='/registerUser'" class="btn btn-primary">Register a New User</button>
			</div>
		</div>    
  <footer id="footer">  <p>Copyright 2013 FromDev.Contact information: <a href="mailto:sachin@fromdev.com">  sachin@fromdev.com</a>.</p>
</footer>

</body>
</html>                                		