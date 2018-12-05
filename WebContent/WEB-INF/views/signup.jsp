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
	<div class="container">
		<div class="row h-100">
			<div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12 my-auto">
				<form action="signup" style="vertical-align:center" method="post">
					<div class="form-group">
					  <label for="email">Email:</label>
					  <input type="text" class="form-control" id="email" placeholder="Enter email" name="email" required>
					</div>
					<div class="form-group">
						<label for="Nick Name">Nick Name:</label>
						<input type="text" class="form-control" id="nickname" placeholder="Choose Nick Name" name="nickname" required>
					</div>
					<div class="form-group">
						<label for="Buddy Name">Buddy Name:</label>
						<input type="text" class="form-control" id="buddyname" placeholder="Choose Buddy Name" name="buddyname">
					</div>
					<div class="form-group">
						<label for="mobile" >Mobile:</label>
						<input type="text" class="form-control" id="mobileno" placeholder="Enter mobile no" name="mobileno" pattern="[0-9]{10}" required>
					</div>
					<div class="form-group">
						<label for="profilePic">Choose Profile Picture:</label>
						<input type="file" class="form-control-file" id="profilePic">
					 </div>
					<div class="col-12 text-center">
						<button type="submit" class="btn btn-primary">Submit</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	</body>
	</html>
