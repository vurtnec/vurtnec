<!DOCTYPE html>
<html lang="en">

<head>
<title>Home - Vurtnec Blog</title>
<link
	href="${pageContext.request.contextPath}/css/bootstrap-combined.no-icons.min.css"
	rel="stylesheet">
<%@ include file="/view/front/common/header.jspf"%>
<style>
#editor {
	overflow: scroll;
	max-height: 300px
}
</style>

<link rel="stylesheet" href="${ctx}/css/index.css" type="text/css">
<link
	href="http://netdna.bootstrapcdn.com/font-awesome/3.0.2/css/font-awesome.css"
	rel="stylesheet">

</head>

<body>
	<br />
	<div class="container">
		<div class="row">
			<a href="${ctx}/startCrawler">start crawler</a>
		</div>
	</div>
	<br />
	<div class="container">
		<div class="row">
			<form action="${ctx}/synchronize" id="synchronizeForm" method="get">
				<input type="text" name="url">
				<button id="synchronizeBtm">synchronize</button>
			</form>
		</div>
	</div>

	<%@ include file="/view/front/common/footer.jspf"%>
	<script type="text/javascript">
	$(function(){
		$('#synchronizeBtm').bind('click',function(){
				$('#synchronizeForm').submit();
		});
	});
	
	</script>
</body>

</html>
