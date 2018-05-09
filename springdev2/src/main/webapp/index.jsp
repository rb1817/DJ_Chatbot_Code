<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8"%>
<html>
<head>
	<title>Spring Web Index</title>
</head>
<script src="http://www.youtube.com/player_api"></script>
<script type="text/javascript">

function fnGetList(sGetToken){
	var $getval = $("#search_box").val();
	if($getval==""){
		alert("검색어를 입력하세요.");
		$("#search_box").focus();
		return;
	}


	var sTargetUrl = "https://www.googleapis.com/youtube/v3/search?part=snippet&order=relevance"
						+ "&q="+ encodeURIComponent($getval) +"&key=AIzaSyB_zvcN1hRvL-pJUpqceFYe1XnkI2NilVA";
	if(sGetToken){
		sTargetUrl += "&pageToken="+sGetToken;
	}
	$.ajax({
		type: "POST",
		url: sTargetUrl,
		dataType: "jsonp",
		success: function(jdata) {
			console.log(jdata.items[0].id.videoId);
		},
		error:function(xhr, textStatus) {
			console.log(xhr.responseText);
			alert("지금은 시스템 사정으로 인하여 요청하신 작업이 이루어지지 않았습니다.\n잠시후 다시 이용하세요.[2]");
			return;
		}
	});
	
}

// Create youtube player

var player;
// videoId : 공유URL(http://youtu.be/UaY9xbHmVAc)에서 'http://youtu.be'만 제거한 아이디

// playerVars : autoplay-자동시작, controls-하단컨트롤 사용여부, html5-html5 사용여부

function onYouTubePlayerAPIReady() {

	player = new YT.Player('player', {

		height: '480',

		width: '853',

		videoId: '3H85SIUCY_I',	

		playerVars: { 'autoplay': 1, 'controls': 1, 'html5': 1 },

		events: {

		'onReady': onPlayerReady,

		'onStateChange': onPlayerStateChange

		}

	});

}




// autoplay video

 function onPlayerReady(event) {

	//동영상 로드가 끝난 후 이벤트 

	alert("동영상 로드");

}




// when video ends

function onPlayerStateChange(event) {        

	if(event.data === 0) {

		//동영상 끝난 후 이벤트 

		alert("영상 끝");

	}

}

</script>
<body>
	<form name="form1" method="post" onSubmit="return false;">
		<input type="text" id="search_box"><button onClick="fnGetList();">가져오기</button>
	</form>
	<div id="get_view"></div>
<h1>index</h1>
<div id="player"></div>
<img src="resources/img/2018.jpg" width=300 height=200/>
<ol>
	<li><a href="product/getProduct">product</a></li>
	<li><a href="product/getProductList">product</a></li>
	<li><a href="product/view">product</a></li>
	<li><a href="chatbot/">chatbot</a></li>
</ol>

</body>
</html>
