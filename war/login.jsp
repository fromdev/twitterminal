<!DOCTYPE html>
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
                <li class="active"><a href="home.jsp" target="_blank">Registered Users</a></li>
                <li><a href="login.jsp" target="_blank">Login</a></li>
				<li><a href="follow.jsp" target="_self">Follow</a></li>
				<li><a href="favorite.jsp" target="_self">Favorite</a></li>
				<li><a href="retweet.jsp" target="_self">Retweet</a></li>
            </ul>
        </div>
    </div>
</nav>
<form class="form-horizontal" action="registerUser">
    <br/><br/><br/><br/>
    <h2>Registering a new User</h2>
    <br/><br/>
    <div class="form-group">
        <label for="consumerKey" class="control-label col-xs-2">Consumer Key</label>
        <div class="col-xs-4">
            <input type="text" class="form-control" name="consumerKey" placeholder="Consumer Key">
        </div>
    </div>
    <br/>
    <div class="form-group">
        <label for="consumerSecret" class="control-label col-xs-2">Consumer Secret</label>
        <div class="col-xs-4">
            <input type="text" class="form-control" name="consumerSecret" placeholder="Consumer Secret">
        </div>
    </div>
    <div class="form-group">
        <div class="col-xs-offset-2 col-xs-4">
            <div class="checkbox">
                <label><input type="checkbox"> Remember me</label>
            </div>
        </div>
    </div>
    <br/>
    <div class="form-group">
        <div class="col-xs-offset-2 col-xs-10">
            <button type="submit" class="btn btn-primary">Login</button>
        </div>
    </div>
    </form>
</body>
</html>                                		