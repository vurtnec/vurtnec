<!DOCTYPE html>
<html lang="en">
<head>
<meta name="description" content="">
<title>Signin - Vurtnec</title>

<%@ include file="/view/common/header.jspf"%>

<!-- Custom styles for this template -->
<link href="css/signin.css" rel="stylesheet">
</head>

<body>
	<div class="container">
		<form class="form-signin">
			<h2 class="form-signin-heading">Please sign in</h2>
			<label for="inputEmail" class="sr-only">Email address</label> 
			<input type="email" id="inputEmail" class="form-control" placeholder="Email address" required="" autofocus=""> 
			<label for="inputPassword" class="sr-only">Password</label> 
			<input type="password" id="inputPassword" class="form-control" placeholder="Password" required="">
			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me">
					Remember me
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
		</form>
	</div>

<%@ include file="/view/common/footer.jspf"%>
</body>
</html>