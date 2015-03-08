<!DOCTYPE html>
<html lang="en">

<head>
    <title>Home - Vurtnec Blog</title>
	<%@ include file="/view/front/common/header.jspf"%>
</head>

<body>
	<%@ include file="/view/front/common/topnav.jspf"%>
    <!-- Page Header -->
    <!-- Set your background image for this header on the line below. -->
    <header class="intro-header" style="background-image: url('img/home-bg.png')" >
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    <div class="site-heading">
                        <h1>Welcome</h1>
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
            <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                <c:forEach items="${articles }" var="article">
                	<div class="post-preview">
	                    <a href="post?articleId=${article.articleId }">
	                    	<c:if test="${not empty article.articleImage}">
		                    	<div style="background-image: url('${article.articleImage}');height:238px;background-size:cover;background-position:center;margin-bottom:-20px;">&nbsp;</div>
	                    	</c:if>
	                        <h2 class="post-title">
	                            ${article.articleTitle}
	                        </h2>
	                        <h3 class="post-subtitle">
	                            ${article.articleSubTitle}
	                        </h3>
	                    </a>
	                    <p>${fn:substring(article.articleContent,0,200) }...</p>
	                    <c:choose>
	                    	<c:when test="${empty article.articleAuthor}">
	                    		<p class="post-meta">Posted on <fmt:formatDate value="${article.articleCreateTime }" pattern="MMMM dd, yyyy" /></p>
	                    	</c:when>
	                    	<c:otherwise>
	                    		<p class="post-meta">Posted by <a href="#">${article.articleAuthor}</a> on <fmt:formatDate value="${article.articleCreateTime }" pattern="MMMM dd, yyyy" /></p>
	                    	</c:otherwise>
	                    </c:choose>
	                </div>
                	<hr>
                </c:forEach>

                <!-- Pager -->
                <ul class="pager">
	                <c:if test="${currentPage != 1 }">
	                    <li class="previous">
	                        <a href="home?page=${currentPage - 1}">&larr; Newer Posts</a>
	                    </li>
    	            </c:if>
    	            <c:if test="${currentPage != totalPageNum }">
	                    <li class="next">
	                        <a href="home?page=${currentPage + 1}">Older Posts &rarr;</a>
	                    </li>
    	            </c:if>
                </ul>
            </div>
        </div>
    </div>
    <%@ include file="/view/front/common/footer.jspf"%>
</body>

</html>
