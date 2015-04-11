<!DOCTYPE html>
<html lang="en">

<head>
<title>Home - Vurtnec Blog</title>
<%@ include file="/view/front/common/header.jspf"%>
<link rel="stylesheet" type="text/css" href="${ctx}/css/login.css" />
<style type="text/css">
	#articlePreview {
		font-size: 16px;
		font-family: 'Microsoft YaHei', 'WenQuanYi Micro Hei', 'tohoma,sans-serif';
		line-height: 150%;
		margin-top: 10px;
		margin-bottom: 10px;
	}
	#articleTitle {
		font-family: 'Microsoft YaHei', 'WenQuanYi Micro Hei', 'tohoma,sans-serif';
		font-size: 24px;
		font-weight: 500;
	}
	#articleSubTitle {
		font-size: 20px;
	}
</style>
</head>

<body>
	<%@ include file="/view/front/common/topnav.jspf"%>
	<!-- Page Header -->
	<!-- Set your background image for this header on the line below. -->
	<header class="intro-header"
		style="background-image: url('img/home-bg.png')">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
					<div class="site-heading" style="padding-top: 200px;padding-bottom: 50px;">
						<img src="img/profile.jpg" style="width: 90px;height: 90px;border: solid;border-radius: 10px;">
						<hr class="small">
						<span class="subheading">Stay hungry, Stay foolish.</span>
					</div>
				</div>
			</div>
		</div>
	</header>

	<!-- Main Content -->
	<div class="container">
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1" style="box-shadow: 0 1px 7px #bcbcbc;border-radius: 10px;">
				<h2 style="color:black;padding-top: 20px;margin-top: 0px;">Latest Articles</h2>
				<hr>
				<c:forEach items="${articles }" var="article">
					<div class="post-preview">
						<a href="post?articleId=${article.articleId }"> <c:if
								test="${not empty article.articleImage}">
								<div
									style="background-image: url('${article.articleImage}');height:238px;background-size:cover;background-position:center;margin-bottom:-20px;margin-left:-15px;margin-right:-15px;">&nbsp;</div>
							</c:if>
							<h2 class="post-title" id="articleTitle">${article.articleTitle}</h2>
							<h3 class="post-subtitle" id="articleSubTitle">${article.articleSubTitle}</h3>
						</a>
						<c:if test="${not empty article.articlePreview }">
							<p id="articlePreview">${article.articlePreview }...</p>
						</c:if>
						<c:choose>
							<c:when test="${empty article.articleAuthor}">
								<p class="post-meta">
									Posted on
									<fmt:formatDate value="${article.articleCreateTime }"
										pattern="MMMM dd, yyyy" />
								</p>
							</c:when>
							<c:otherwise>
								<p class="post-meta">
									Posted by <a href="#">${article.articleAuthor}</a> on
									<fmt:formatDate value="${article.articleCreateTime }"
										pattern="MMMM dd, yyyy" />
								</p>
							</c:otherwise>
						</c:choose>
					</div>
					<hr>
				</c:forEach>

				<!-- Pager -->
				<c:if test="${totalPageNum > 5 }">
					<ul class="pager">
						<c:if test="${currentPage != 1 }">
							<li class="previous"><a href="home?page=${currentPage - 1}">&larr;
									Newer Posts</a></li>
						</c:if>
						<c:if test="${currentPage != totalPageNum }">
							<li class="next"><a href="home?page=${currentPage + 1}">Older
									Posts &rarr;</a></li>
						</c:if>
					</ul>
				</c:if>
			</div>
		</div>
	</div>
	<%@ include file="/view/front/common/footer.jspf"%>
</body>
</html>
