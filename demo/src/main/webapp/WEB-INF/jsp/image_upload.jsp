<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/resources/html/newMain.html" flush="true" />
<meta charset="utf-8">
<title>图片上传</title>
</head>
<body style="background:#fff;">
	<form action="image_upload.xhtml" name="form" id="form" method="post"
		enctype="multipart/form-data">
		 <div><input type="file" id="file" name="file" /><input
			type="button" id="submit" value="上传" /></div>
			<img id="img" style="height:100px;" alt=""
			src="${fileUrl }" />
		<div>
			<span id="tips"></span>
		</div>
	</form>

</body>
<script type="text/javascript">
	(function() {

		var initObj = {};
		$.extend(initObj, {
			init : function() {
				var errorMsg = "${errorMsg}";
				if (errorMsg) {
					$("#tips").html(errorMsg);
					$("#img").attr("src", "");
				}

				$("#file").change(function() {
					$("#submit").removeAttr("disabled");
				});

				var fileUrl = "${fileUrl}";
				if (fileUrl) {
					$("#preImg", parent.document).attr("src", fileUrl);
					
				}
				/*
				$("form").submit(function() {
					var fi = $("#file").val();
					var fileUrl = "${file}";
					if (fi.indexOf(fileUrl) > 0) {
						alert("请选择别的图片上传！");
						return false;
					}
				});
				 */
			}
		});
		function loadImg(){
			$.ajax({
				   type: "POST",
				   url: "image_upload.xhtml",
				   data: $("#file").val(),
				   success: function(msg){
					   initObj.init();
				   }
				});
		}

		$(document).ready(function() {
			//initObj.init();
			$("#submit").click(function(){
				loadImg();
			})
		});
	})();
</script>
</html>