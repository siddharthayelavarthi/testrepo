<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>IDW Buddy</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  <link rel="stylesheet" href="css/style.css">
  <script src="scripts/script.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-light justify-content-center">
  <h1>IDW BUDDY</H1>
</nav>
<div class="row">
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-bottom">
		<div class="col-6 col-sm-6 col-md-6 col-lg-6 col-xl-6 text-center">
			 <a href="#"><button type="button" class="btn">Sign In</button></a>
		</div>
		<div class="col-6 col-sm-6 col-md-6 col-lg-6 col-xl-6 text-center">
			<form action="forward" method="get"><button type="submit" class="btn">Sign Up</button></form>
		</div>
	</nav>
</div> 
<div class="container">
	<div class="row h-100">
		<div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12 my-auto">
			<form action="#" style="vertical-align:center">
				<div class="form-group">
				  <label for="email">Email:</label>
				  <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
				</div>
				<div class="form-group" id="otpButton">
				</div>
				<div class="col-12 text-center">
					<button id="otpButtonText" type="submit" class="btn btn-primary" onclick="otpFunction();">Get OTP</button>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>
