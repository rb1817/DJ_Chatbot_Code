<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- bootstrap start -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=0, minimum-scale=0.6, maximum-scale=1, user-scalable=no" />
<title>page title</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-3.3.1.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<!-- bootstrap end -->
<link href="<c:url value="/resources/css/bubbles.css" />"
	rel="stylesheet">
<script src="http://www.youtube.com/player_api"></script>
<link href="https://fonts.googleapis.com/css?family=Dancing+Script" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">
<script type="text/javascript">



$(document).ready(function(){
	$('#btn_restart').click(function(){
		let re = confirm('기존 대화가 모두 사라집니다. \n대화를 다시 할까요?')	
		if(re){
			$('#said').html('');
			ajax_process('');
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
	let isay = '<div class = "col-xs-12">'+'<img class="img-circle" style="width:15%;float:right;padding-left:10px;" src="<c:url value="/resources/img/ggg.PNG"/>" >';
		isay += '<p style="float:right">'+'<h4 style="float:right">'+'<br/>'+'USER'+'</h4>'+'</p>'+'</div>';
		isay += '<div class="col-xs-4">'+'</div>'+'<div class="col-xs-8">'+'<div class="alert alert-warning align="right" style="float:right;padding-left:10px;">';
		isay += '<p class="text-center">'+'<h4>'+_isay+'</h4>'+'</p>'+'</div>'+'</div>';
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
			let watsonsay='<div>';
			if(_isay===''){
				watsonsay += "<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>";
				watsonsay += "<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>";
				watsonsay += "<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>";
				watsonsay += "<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>"+"<br/>";
				watsonsay +='<div class = "col-xs-12">'+'<img class="img-circle" style="width:15%;float:left;padding-right:10px;" src="<c:url value="/resources/img/sss.PNG" />" />';
				watsonsay += '<h4>'+'<br/>'+'DJ LEE'+'</h4>'+'</div>';
				watsonsay+='<div class="col-xs-8">'+'<div class="alert alert-warning"style="float:left;padding-right:10px;">' +'<p class="text-center">'+'<h4>'+ data.output.text+'<h4>'+'</p>';
				watsonsay += '</div>'+'<div class="col-xs-4">'+'</div>'+'</div>';
				$('#said').append(watsonsay);
				$('html, body').animate({scrollTop:$(document).height()}, 500);
			}
			else if(data.context.title==='title'&&data.output.text!=null){
				watsonsay +='<div class = "col-xs-12">'+'<img class="img-circle" style="width:15%;float:left;padding-right:10px;" src="<c:url value="/resources/img/sss.PNG" />" />';
				watsonsay += '<h4>'+'<br/>'+'DJ LEE'+'</h4>'+'</div>';
				watsonsay +='<div class="col-xs-8">'+'<div class="alert alert-warning"style="float:left;padding-right:10px;">' +'<p class="text-center">';
				watsonsay +='<h4>'+'당신의 취향에 맞는 노래'+'<br/>'+ data.output.text+'<br/>'+'추천합니다..';
				watsonsay += '<br/>'+'<br/>'+'마음에 들지 않는다면'+'<br/>'+'키워드 다시 입력해주세요!'+'<h4>'+'</p>';
				watsonsay += '</div>'+'<div class="col-xs-4">'+'</div>'+'</div>';
				$('#said').append(watsonsay);
				$('html, body').animate({scrollTop:$(document).height()}, 500);
				link(data.output.text);
			}else{
				watsonsay +='<div class = "col-xs-12">'+'<img class="img-circle" style="width:15%;float:left;padding-right:10px;" src="<c:url value="/resources/img/sss.PNG" />" />';
				watsonsay += '<h4>'+'<br/>'+'DJ LEE'+'</h4>'+'</div>';
				watsonsay +='<div class="col-xs-8">'+'<div class="alert alert-warning"style="float:left;padding-right:10px;">' +'<p class="text-center">'+'<h4>'+ data.output.text+'<h4>'+'</p>';
				watsonsay += '</div>'+'<div class="col-xs-4">'+'</div>'+'</div>';
				$('#said').append(watsonsay);
				$('html, body').animate({scrollTop:$(document).height()}, 500);
			}
		}
	});
}
function link(da){
	var $getval = da;
	var sTargetUrl = "https://www.googleapis.com/youtube/v3/search?part=snippet&order=relevance"
						+ "&q="+ encodeURIComponent($getval) +"&key=AIzaSyB_zvcN1hRvL-pJUpqceFYe1XnkI2NilVA";
	$.ajax({
		type: "POST",
		url: sTargetUrl,
		dataType: "jsonp",
		success: function(jdata) {
			console.log(jdata.items[0].id.videoId);
			var code = jdata.items[0].id.videoId;
			//$(jdata.items).each(function(i)
		/* 	$("#said").append("<a href='https://youtu.be/"+code+"' target='_blank'>사이트</a>"); */
			if(code!=="8FOrfPpnhFI"){
			$("#said").append('<div class = "col-xs-12">'+'<img class="img-circle" style="width:15%;float:left;padding-right:10px;" src="<c:url value="/resources/img/sss.PNG" />" />'
							+'<h4>'+'<br/>'+'DJ LEE'+'</h4>'+'</div>'+'<div class="col-xs-8">'+'<div class="alert alert-warning"style="float:left;padding-right:10px;">'
							+'<iframe width="510" height="315" src="https://www.youtube.com/embed/'+code+'?rel=0&enblejsapi=1" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>'
							+'</div>'+'<div class="col-xs-4">'+'</div>'+'</div>');
		}
		}
	
	});
	}


</script>
</head>
<body>
<div><img src="resources/img/2018.jpg">
	<header class="bg-warning"
		style="margin: 0; padding: 0; width: 100%; height: 60; top: 0; position: fixed">
		<p class="text-center">
		<h1 style="font-family: 'Dancing Script', cursive">
			<strong>Keyword DJ Music♬</strong>
		</h1>
		</p>
	</header>

	<div>
		
		<div class="form-group"
			style="margin: 0; padding: 0; width: 100%; height: 60; bottom: 0;margin-top:20% auto">
			<div class="col-xs-12" id="said" style="font-family: 'Nanum Gothic', sans-serif;margin-bottom:30px;margin-top:30px"></div>
			<div class="input-group" style="margin: 0; padding: 0; width: 100%; tom:100 ; bottom: 0;position: fixed">
				<input type="text" class="form-control input-lg" id="txt_isay"
					placeholder="입력해주세요">
				<div class="input-group-btn">
					<button class="btn btn-default btn-lg" id="btn_isay" type="submit">
						입력</button>
					<button class="btn btn-default btn-lg" id="btn_restart"
						type="submit">다시</button>
				</div>
			</div>
		</div>
	</div>
	</img>
</div>
	<!-- <input type="text" id="txt_isay" class="pinched" />
<button class="btn btn-primary btn-lg btn-block" type="button" id="btn_isay">say</button> -->
</body>
</html>