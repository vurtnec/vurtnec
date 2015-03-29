<!DOCTYPE html>
<html>

<head>
	<%@ include file="/view/front/common/header.jspf" %>
    <title>${fn:substring(article.articleTitle,0,20) } - Vurtnec Blog</title>
    <style type="text/css">
	#articleContent img{
		width:100%
	}    
    </style>
</head>

<body>
	<%@ include file="/view/front/common/topnav.jspf"%>
    <!-- Page Header -->
    <!-- Set your background image for this header on the line below. -->
    <header class="intro-header" style="background-image: url('${article.articleImage}')">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    <div class="post-heading">
                        <h1>${article.articleTitle }</h1>
                        <h2 class="subheading">${article.articleSubTitle }</h2>
                        <c:choose>
	                    	<c:when test="${empty article.articleAuthor}">
	                    		<span class="meta">Posted on <fmt:formatDate value="${article.articleCreateTime }" pattern="MMMM dd, yyyy" /></span>
	                    	</c:when>
	                    	<c:otherwise>
		                        <span class="meta">Posted by <a href="#">${article.articleAuthor}</a> on <fmt:formatDate value="${article.articleCreateTime }" pattern="MMMM dd, yyyy" /></span>
	                    	</c:otherwise>
	                    </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </header>
	
    <!-- Post Content -->
    <article>
        <div class="container">
            <div class="row">
                <div id="articleContent" class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1" style="box-shadow: 0 1px 7px #bcbcbc;border-radius: 10px;padding: 25px;font-size:16px;">
                    ${article.articleContent }
                </div>
            </div>
        </div>
    </article>
    <%@ include file="/view/front/common/footer.jspf"%>
</body>

</html>
