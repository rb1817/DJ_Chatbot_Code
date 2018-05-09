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
<!-- bootstrap end -->
<link href="<c:url value="/resources/css/bubbles.css" />" rel="stylesheet">
<script type="text/javascript">


$(document).ready(function(){
	$('#btn_restart').click(function(){
		let re = confirm('기존 대화가 모두 사라집니다. \n대화를 다시 할까요?')	
		if(re){
			$('#said').html('');
			ajax_process();
		}
	});
	
	$('#txt_isay').keypress(function(e){
		if(e.which === 13) {
			conversation();
		}
	});
	
	$('#btn_isay').click(function(){
		conversation();
		link();
	});	
	
	ajax_process('');
});

function conversation() {
	let _isay = $.trim($('#txt_isay').val());
	if (_isay ==='') {
		alert('내용을 입력하세요');
		return;
	}
	let isay = '<div>'+'<p class="oval-thought-border right">' + _isay + '</p>'+'<img class="img-circle" style="width:20%;float:right;padding-left:10px;" src="<c:url value="/resources/img/ggg.PNG"/>" />' +'</div>';
	$('#said').append(isay);
	$('#txt_isay').val('');
	ajax_process(_isay);
}

function ajax_process(_isay) {
	$.ajax({
		type:'POST',
		url :'watsonsay',
		data:{'isay': _isay },
//		async:false,
		success: function(data){
			let watsonsay = 
				'<br/>'+'<br/>'+'<br/>'+'<br/>'+'<br/>'+'<br/>'+'<br/>'+'<br/>'+'<div>'+'<p class="oval-speech-border left">'  + data.output.text;
			watsonsay += '</p>'+'<img class="img-circle" style="width:20%;" src="<c:url value="/resources/img/sss.PNG" />" />'+'</div>';
			$('#said').append(watsonsay);
			$('html, body').animate({scrollTop:$(document).height()}, 500);
			if(data.intent=='추천'){
				link(data);
			}
		}
	});
}
function link(data){
	var $getval = data;

	var sTargetUrl = "https://www.googleapis.com/youtube/v3/search?part=snippet&order=relevance"
						+ "&q="+ encodeURIComponent($getval) +"&key=AIzaSyB_zvcN1hRvL-pJUpqceFYe1XnkI2NilVA";
	$.ajax({
		type: "POST",
		url: sTargetUrl,
		dataType: "jsonp",
		success: function(jdata) {
			console.log(jdata.items[0].id.videoId);
			var code = jdata.items[0].id.videoId;
			//$(jdata.items).each(function(i){
				cosole.log(res);
			$("#said").append("<a href='https://youtu.be/"+code+"' target='_blank'>안녕</a>");
			console.log(res);
			return;
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
<div id="said"></div>
<button type="button" id="btn_restart" class="btn btn-default">대화재시작</button>
<input type="text" id="txt_isay" class="pinched" />
<button type="button" id="btn_isay" class="btn btn-default">say</button>
</body>
</html>