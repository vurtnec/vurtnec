
<!DOCTYPE>
<html>
<head>
<meta name="description" content="vurtnec google search">
<meta name="author" content="vurtnec">
<link rel="icon" href="img/camera.png">
<!-- Bootstrap Core CSS -->
<link href="http://cdn.bootcss.com/bootstrap/2.3.2/css/bootstrap.min.css" rel="stylesheet">
<link href="http://cdn.bootcss.com/bootstrap/2.3.2/css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="http://cdn.bootcss.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href='css/lora.css' rel='stylesheet' type='text/css'>
<link href='css/openscan.css' rel='stylesheet' type='text/css'>
<link href="css/clean-blog.min.css" rel="stylesheet">

<style type="text/css">
/* Sticky footer styles
      -------------------------------------------------- */
html, body {
	height: 100%;
	/* The html and body elements cannot have any padding or margin. */
}

/* Wrapper for page content to push down footer */
#wrap {
	min-height: 100%;
	height: auto !important;
	height: 100%;
	/* Negative indent footer by it's height */
	margin: 0 auto -60px;
}

/* Set the fixed height of the footer here */
#push, #footer {
	height: 60px;
}

#footer {
	background-color: #f5f5f5;
}

/* Lastly, apply responsive CSS fixes as necessary */
@media ( max-width : 767px) {
	#footer {
		margin-left: -20px;
		margin-right: -20px;
		padding-left: 20px;
		padding-right: 20px;
	}
}

/* Custom page CSS
      -------------------------------------------------- */
/* Not required for template or sticky footer method. */
.container {
	width: auto;
	max-width: 680px;
	text-align: center;
}

.container .credit {
	margin: 13px 0;
}

.container .credit a {
	color: black;
}

#wrap {
	margin: 20px 0;
}

#searchBox {
	height: 40px;
	width: 400px;
}

#search {
	border-radius: 5px;
	height: 40px;
	margin-top: -9px;
	line-height: 10px;
}
</style>
</head>
<body>

	<div id="wrap">
		<div class="container">
			<div class="page-header">
				<h1>
					Google Search <small>Vurtnec</small>
				</h1>
			</div>
			<div style="height:40%;margin-top:100px;font-size: 16px;font-family: 'Microsoft YaHei', 'WenQuanYi Micro Hei', 'tohoma,sans-serif';">
			<input id="searchBox" type="text" name="q" value="" placeholder="search something..." />
			<input id="pageNum" type="hidden" value="" name="n" />
			<button id="search" type="submit" class="btn">Search</button>
			<p id="result" style="float:left;text-align: left;"></p>
			</div>
			<div id="pagination" style="display:none;">
				<a id="prePage" style="float:left;margin-left:10px;cursor:pointer;">Previous</a>
				<a id="nextPage" style="float:right;margin-right:10px;cursor:pointer;">Next</a>
			</div>
			<div style="clear:both;"></div>

			<hr />
			<!-- Footer -->
			<footer>
				<div class="container">
					<div class="row">
						<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">

							<a href="http://weibo.com/378910504"> <span class="fa-stack fa-lg"> <i class="fa fa-circle fa-stack-2x"></i> <i class="fa fa-weibo fa-stack-1x fa-inverse"></i>
							</span>
							</a> <a href="https://instagram.com/vurtnec"> <span class="fa-stack fa-lg"> <i class="fa fa-circle fa-stack-2x"></i> <i class="fa fa-instagram fa-stack-1x fa-inverse"></i>
							</span>
							</a> <a href="https://github.com"> <span class="fa-stack fa-lg"> <i class="fa fa-circle fa-stack-2x"></i> <i class="fa fa-github fa-stack-1x fa-inverse"></i>
							</span>
							</a>

							<p class="copyright text-muted">Copyright &copy; Vurtnec 2015</p>
						</div>
					</div>
				</div>
			</footer>
		</div>
	</div>




	<script src="js/jquery.js"></script>

	<script src="js/bootstrap.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<!-- <script src="js/clean-blog.min.js"></script> -->

	<script>
		$(document).ready(function() {

			$("#search").click(function() {
				
				var term = $("#searchBox").val();
				var result = $("#result");
				
				$.ajax({
					url: "http://google.vurtnec.com/google.jsp",
					data: {
						q: term
					} ,
					success: function(data) {
						
						var jsonObj = JSON.parse(data);

						var result = "";
						$(jsonObj).each(function(index, obj) {
							result = result + "<a href='" + obj.url + "'>"
								+ obj.title + "</a><div>"
								+ obj.descrition + "</div>" + "<br />";
						});
						
						$("#pageNum").val(0);
						
						if($("#pageNum").val() == "0" || $("#pageNum").val() == 0) {
							$("#prePage").hide();
						}
						$("#pagination").show();
						
						$("#result").html(result);
					}
					
				});
				
			});
			
			$("#nextPage").click(function() {
				
				var term = $("#searchBox").val();
				var result = $("#result");
				
				var pageNum = parseInt($("#pageNum").val()) + 1;
				
				$.ajax({
					url: "http://google.vurtnec.com/google.jsp",
					data: {
						q: term,
						n: pageNum
					} ,
					success: function(data) {
						
						var jsonObj = JSON.parse(data);

						var result = "";
						$(jsonObj).each(function(index, obj) {
							result = result + "<a href='" + obj.url + "'>"
								+ obj.title + "</a><div>"
								+ obj.descrition + "</div>" + "<br />";
						});
						
						$("#pageNum").val(pageNum);
						$("#prePage").show();
						
						$("#result").html(result);
					}
					
				});
				
			});
			
			$("#prePage").click(function() {
				
				var term = $("#searchBox").val();
				var result = $("#result");
				
				var pageNum = parseInt($("#pageNum").val()) - 1;
				
				$.ajax({
					url: "http://google.vurtnec.com/google.jsp",
					data: {
						q: term,
						n: pageNum
					} ,
					success: function(data) {
						
						var jsonObj = JSON.parse(data);

						var result = "";
						$(jsonObj).each(function(index, obj) {
							result = result + "<a href='" + obj.url + "'>"
								+ obj.title + "</a><div>"
								+ obj.descrition + "</div>" + "<br />";
						});
						
						$("#pageNum").val(pageNum);
						if($("#pageNum").val() == "0" || $("#pageNum").val() == 0) {
							$("#prePage").hide();
						}
						
						$("#result").html(result);
					}
					
				});
				
			});
			
		});
	</script>
</body>
</html>




