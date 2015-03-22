<!DOCTYPE html>
<html lang="en">

<head>
    <title>Home - Vurtnec Blog</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
	<%@ include file="/view/front/common/header.jspf"%>
	<style>
		#editor {overflow:scroll; max-height:300px}
	</style>
	
	<link rel="stylesheet" href="${ctx}/css/index.css" type="text/css">
	<link href="http://netdna.bootstrapcdn.com/font-awesome/3.0.2/css/font-awesome.css" rel="stylesheet">
	
</head>

<body>
	<nav class="navbar navbar-default navbar-custom navbar-fixed-top">
	    <div class="container-fluid">
	        <!-- Brand and toggle get grouped for better mobile display -->
	        <div class="navbar-header page-scroll">
	            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
	                <span class="sr-only">Toggle navigation</span>
	                <span class="icon-bar"></span>
	                <span class="icon-bar"></span>
	                <span class="icon-bar"></span>
	            </button>
	            <a class="navbar-brand" href="home">${currentUser.userName }</a>
	        </div>
	
	        <!-- Collect the nav links, forms, and other content for toggling -->
	        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	            <ul class="nav navbar-nav navbar-right">
	                <li>
	                    <a href="home">Dashboard</a>
	                </li>
	                <li>
	                    <a href="post">Post</a>
	                </li>
	            </ul>
	        </div>
	        <!-- /.navbar-collapse -->
	    </div>
	    <!-- /.container -->
	</nav>
	<header class="intro-header" style="background-image: url('${ctx}/img/home-bg.png')" >
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
    
    <div class="container">
		<div class="row">
			 <form action="${ctx}/synchronize" id="synchronizeForm" method="get">
                <input type="text" name="url" >
                <button id="synchronizeBtm" >synchronize</button>
            </form>
		</div>
	</div>
    
    
	<!-- <div class="container">
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
				<div class="">
					<div class="hero-unit" style="padding: 30px;">
						
						<hr />
						<div id="alerts"></div>
						<div class="btn-toolbar" data-role="editor-toolbar"
							data-target="#editor">
							<div class="btn-group">
								<a class="btn dropdown-toggle" data-toggle="dropdown"
									title="Font"><i class="icon-font"></i><b class="caret"></b></a>
								<ul class="dropdown-menu">
								</ul>
							</div>
							<div class="btn-group">
								<a class="btn dropdown-toggle" data-toggle="dropdown"
									title="Font Size"><i class="icon-text-height"></i>&nbsp;<b
									class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a data-edit="fontSize 5"><font size="5">Huge</font></a></li>
									<li><a data-edit="fontSize 3"><font size="3">Normal</font></a></li>
									<li><a data-edit="fontSize 1"><font size="1">Small</font></a></li>
								</ul>
							</div>
							<div class="btn-group">
								<a class="btn" data-edit="bold" title="Bold (Ctrl/Cmd+B)"><i
									class="icon-bold"></i></a> <a class="btn" data-edit="italic"
									title="Italic (Ctrl/Cmd+I)"><i class="icon-italic"></i></a> <a
									class="btn" data-edit="strikethrough" title="Strikethrough"><i
									class="icon-strikethrough"></i></a> <a class="btn"
									data-edit="underline" title="Underline (Ctrl/Cmd+U)"><i
									class="icon-underline"></i></a>
							</div>
							<div class="btn-group">
								<a class="btn" data-edit="insertunorderedlist"
									title="Bullet list"><i class="icon-list-ul"></i></a> <a
									class="btn" data-edit="insertorderedlist" title="Number list"><i
									class="icon-list-ol"></i></a> <a class="btn" data-edit="outdent"
									title="Reduce indent (Shift+Tab)"><i
									class="icon-indent-left"></i></a> <a class="btn" data-edit="indent"
									title="Indent (Tab)"><i class="icon-indent-right"></i></a>
							</div>
							<div class="btn-group" style="margin-top: 5px;">
								<a class="btn" data-edit="justifyleft"
									title="Align Left (Ctrl/Cmd+L)"><i class="icon-align-left"></i></a>
								<a class="btn" data-edit="justifycenter"
									title="Center (Ctrl/Cmd+E)"><i class="icon-align-center"></i></a>
								<a class="btn" data-edit="justifyright"
									title="Align Right (Ctrl/Cmd+R)"><i
									class="icon-align-right"></i></a> <a class="btn"
									data-edit="justifyfull" title="Justify (Ctrl/Cmd+J)"><i
									class="icon-align-justify"></i></a>
							</div>
							<div class="btn-group" style="margin-top: 5px;">
								<a class="btn dropdown-toggle" data-toggle="dropdown"
									title="Hyperlink"><i class="icon-link"></i></a>
								<div class="dropdown-menu input-append">
									<input class="span2" placeholder="URL" type="text"
										data-edit="createLink" />
									<button class="btn" type="button">Add</button>
								</div>
								<a class="btn" data-edit="unlink" title="Remove Hyperlink"><i
									class="icon-cut"></i></a>
							</div>
	
							<div class="btn-group" style="margin-top: 5px;">
								<a class="btn" title="Insert picture (or just drag & drop)"
									id="pictureBtn"><i class="icon-picture"></i></a> <input
									type="file" data-role="magic-overlay" data-target="#pictureBtn"
									data-edit="insertImage" />
							</div>
							<div class="btn-group" style="margin-top: 5px;">
								<a class="btn" data-edit="undo" title="Undo (Ctrl/Cmd+Z)"><i
									class="icon-undo"></i></a> <a class="btn" data-edit="redo"
									title="Redo (Ctrl/Cmd+Y)"><i class="icon-repeat"></i></a>
							</div>
							<input type="text" data-edit="inserttext" id="voiceBtn"
								x-webkit-speech="">
						</div>
						<div id="editor"></div>
					</div>
				</div>
			</div>
		</div>
	</div> -->
    <%@ include file="/view/front/common/footer.jspf"%>
   <%--  <script src="${ctx}/js/bootstrap-wysiwyg.js" type="text/javascript"></script>
	<script src="${ctx}/js/jquery.hotkeys.js" type="text/javascript"></script> --%>
	<script>
		
	  $(function(){
	    /* function initToolbarBootstrapBindings() {
	      var fonts = ['Serif', 'Sans', 'Arial', 'Arial Black', 'Courier', 
	            'Courier New', 'Comic Sans MS', 'Helvetica', 'Impact', 'Lucida Grande', 'Lucida Sans', 'Tahoma', 'Times',
	            'Times New Roman', 'Verdana'],
	            fontTarget = $('[title=Font]').siblings('.dropdown-menu');
	      $.each(fonts, function (idx, fontName) {
	          fontTarget.append($('<li><a data-edit="fontName ' + fontName +'" style="font-family:\''+ fontName +'\'">'+fontName + '</a></li>'));
	      });
	      $('a[title]').tooltip({container:'body'});
	    	$('.dropdown-menu input').click(function() {return false;})
			    .change(function () {$(this).parent('.dropdown-menu').siblings('.dropdown-toggle').dropdown('toggle');})
	        .keydown('esc', function () {this.value='';$(this).change();});
	
	      $('[data-role=magic-overlay]').each(function () { 
	        var overlay = $(this), target = $(overlay.data('target')); 
	        overlay.css('opacity', 0).css('position', 'absolute').offset(target.offset()).width(target.outerWidth()).height(target.outerHeight());
	      });
	      if ("onwebkitspeechchange"  in document.createElement("input")) {
	        var editorOffset = $('#editor').offset();
	        $('#voiceBtn').css('position','absolute').offset({top: editorOffset.top, left: editorOffset.left+$('#editor').innerWidth()-35});
	      } else {
	        $('#voiceBtn').hide();
	      }
		};
		function showErrorAlert (reason, detail) {
			var msg='';
			if (reason==='unsupported-file-type') { msg = "Unsupported format " +detail; }
			else {
				console.log("error uploading file", reason, detail);
			}
			$('<div class="alert"> <button type="button" class="close" data-dismiss="alert">&times;</button>'+ 
			 '<strong>File upload error</strong> '+msg+' </div>').prependTo('#alerts');
		}; */
		
		$('#synchronizeBtm').bind('click',function(){
			$('#synchronizeForm').submit();
		});
	    /* initToolbarBootstrapBindings(); 
		$('#editor').wysiwyg({ fileUploadError: showErrorAlert} ); */
	  });
	</script>
</body>

</html>
