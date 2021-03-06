<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- bootstrap start -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title> page title </title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-3.3.1.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script type="text/javascript">

// Create youtube player
// videoId : 공유URL(http://youtu.be/UaY9xbHmVAc)에서 'http://youtu.be'만 제거한 아이디

// playerVars : autoplay-자동시작, controls-하단컨트롤 사용여부, html5-html5 사용여부


function fnGetList(){
	var $getval = $("#search_box").val();
	if($getval==""){
		alert("검색어를 입력하세요.");
		$("#search_box").focus();
		return;
	}


	var sTargetUrl = "https://www.googleapis.com/youtube/v3/search?part=snippet&order=relevance"
						+ "&q="+ encodeURIComponent($getval) +"&key=AIzaSyB_zvcN1hRvL-pJUpqceFYe1XnkI2NilVA";
	$.ajax({
		type: "POST",
		url: sTargetUrl,
		dataType: "jsonp",
		success: function(jdata) {
			console.log(jdata.items[0].id.videoId);
			var res = jdata.items[0].id.videoId;
			//$(jdata.items).each(function(i){
			$("#get_view").append("<a href='https://youtu.be/"+res+"' target='_blank'>안녕</a>");
			//}).promise().done();
			console.log(res);
		},
		error:function(xhr, textStatus) {
			console.log(xhr.responseText);
			alert("지금은 시스템 사정으로 인하여 요청하신 작업이 이루어지지 않았습니다.\n잠시후 다시 이용하세요.[2]");
			return;
		}
	});
	
}

</script>
</head>
<body>
<form name="form1" method="post" onSubmit="return false;">
<input type="text" id="search_box"><button onClick="fnGetList();">가져오기</button>
</form>
<div id="ytplayer"></div>
<div id="get_view"></div>
</body>
</html>