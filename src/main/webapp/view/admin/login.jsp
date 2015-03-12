<!DOCTYPE html>
<html lang="en">

<head>
    <title>Home - Vurtnec Blog</title>
	<%@ include file="/view/front/common/header.jspf"%>
	<link rel="stylesheet" type="text/css" href="css/login.css" />
</head>

<body>
	<div class="container">
	    <div class="row colored">
	        <div class="contcustom">
	            <span class="fa fa-camera-retro bigicon"></span>
	            <h2>Vurtnec</h2>
	            <c:if test="${not empty errorCode }">
		            <p id="errorMsg"><span class="fa fa-exclamation-circle"></span>&nbsp;${errorCode }</p>
	            </c:if>
	            <div>
	            <form action="signIn" id="loginForm" method="post">
	                <input type="text" name="userName" placeholder="userName"  >
	                <input type="password" name="password" placeholder="password" >
	                <button id="loginBtm" class="btn btn-default wide"><span class="fa fa-check med"></span></button>
	            </form>
	            </div>
	        </div>
	    </div>
	</div>
    <%@ include file="/view/front/common/footer.jspf"%>
</body>
<script type="text/javascript">
$(document).ready(function () {
	$('#loginBtm').bind('click',function(){
		$('#loginForm').submit();
	});
}
</script>
</html>
