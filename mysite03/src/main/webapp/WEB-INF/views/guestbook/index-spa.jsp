<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/guestbook-spa.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/ejs/ejs.js"></script>
<script>
var listItemTemplate = new EJS({
	url: "${pageContext.request.contextPath }/assets/js/ejs/list-item-template.ejs"
});
// 리스트 형식의 EJS
var listTemplate = new EJS({
	url: "${pageContext.request.contextPath }/assets/js/ejs/list-template.ejs"
});
var messageBox = function(title, message, callback){
	$("#dialog-message p").text(message);
	$("#dialog-message").attr("title", title).dialog({
		width: 340,
		height: 170,
		modal: true,
		buttons: {
			"확인": function(){
				$(this).dialog('close');
			}
		},
		close: callback
	});
}
var fetch = function(){
	var lastli = $("#list-guestbook li:last-child").data("no");
	if(lastli == null){
		lastli = 0;
	}
	$.ajax({
		url: "${pageContext.request.contextPath}/guestbook/api?sno=" + lastli,
		type: "get",
		dataType: "json",
		success: function(response){
			if(response.result === 'fail'){
				console.error(response.message);
				return;
			}

			//response.data.forEach(function(vo){
			//	render(vo);
			//})
			var htmls = listTemplate.render(response);
			$("#list-guestbook").append(htmls);
		}
	});
}

$(function(){
	$("#add-form").submit(function(){
		event.preventDefault();
		//1. 이름 유효성 체크
		if ($("#input-name").val() === '') {
			messageBox("Guestbook", "이름이 비어 있습니다.", function(){
				$("#input-name").focus();
			});
			return;
		}
	
		// 2. 비밀번호 유효성 체크
	    if ($('#input-password').val() === '') {
	      	messageBox("Guestbook", '비밀번호가 비어 있습니다.', function(){
	      		$('#input-password').focus();
	      	});
	        return;
	    }

	 	// 3. 이메일 유효성 체크
	    if ($('#tx-content').val() === '') {
	      	messageBox("Guestbook", '내용이 비어 있습니다.', function(){
	      		$('#tx-content').focus();
	      	});
	        return;
	    }
		// form serialization
		var vo = {};
		vo.name = $("#input-name").val();
		vo.password = $("#input-password").val();
		vo.message = $("#tx-content").val();
		
		/* validation & messagebox */
		$.ajax({
			url: "${pageContext.request.contextPath}/guestbook/api",
			type: "post",
			dataType: "json",
			contentType: "application/json",
			data: JSON.stringify(vo),
			success: function(response){
				if(response.result === 'fail'){
					console.error(response.message);
					return;
				}
				
				// render(response.data, true);
				// EJS로 실행
				var htmls = listItemTemplate.render(response.data);
				$("#list-guestbook").prepend(htmls);
				
				
				$("#add-form")[0].reset();
			}
		});
	});
});

$(function(){
	// 삭제 다이알로그 jQuery 객체 미리 만들기
	var $dialogDelete = $("#dialog-delete-form").dialog({
		autoOpen: false,
		modal: true,
		buttons: {
			"삭제": function() {
				var no = $("#hidden-no").val();
				var password = $("#password-delete").val();
				$.ajax({
					url: "${pageContext.request.contextPath}/guestbook/api/" + no,
					type: "delete",
					dataType: "json",
					data: "password=" + password,
					success: function(response) { 
						if(response.result === 'fail') {
							console.error(response.message);
							return;
						}	
						if(!response.data){
							$("#dialog-delete")[0].reset();
							$("#dialog-delete-form p.validateTips.error").show();
							return;
						}
							$("#list-guestbook li[data-no=" + no + "]").remove();
							$dialogDelete.dialog('close');
					}

				});
			},
			"취소": function() {
				console.log("삭제 다이알로그의 폼 데이터 리셋하기");
				$("#dialog-delete")[0].reset();
				$(this).dialog('close');
			}
		},
		close: function(){
			$("#dialog-delete")[0].reset();
			$("#dialog-delete-form p.validateTips.error").hide();
		}
	});
	
	// 메세지 삭제 버튼 click 이벤트 처리(Live Event)
	$(document).on('click', "#list-guestbook li", function(event){
		event.preventDefault();
		
		$("#hidden-no").val($(this).data("no"));
		$dialogDelete.dialog('open');
	});
$(function(){
	$(window).scroll(function(){
		var $window = $(this);
		var $document = $(document);
					
		var windowHeight = $window.height();
		var documentHeight = $document.height();
		var scrollTop = $window.scrollTop();
		
		if(documentHeight < windowHeight + scrollTop + 10){
			fetch();
		}
	});
});
// 최초 리스트 가져오기
fetch();
});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<h1>방명록</h1>
				<form id="add-form" action="" method="post">
					<input type="text" id="input-name" placeholder="이름">
					<input type="password" id="input-password" placeholder="비밀번호">
					<textarea id="tx-content" placeholder="내용을 입력해 주세요."></textarea>
					<input type="submit" value="보내기" />
				</form>
				<ul id="list-guestbook"></ul>
			</div>
			<div id="dialog-delete-form" title="메세지 삭제" style="display:none">
  				<p class="validateTips normal">작성시 입력했던 비밀번호를 입력하세요.</p>
  				<p class="validateTips error" style="display:none">비밀번호가 틀립니다.</p>
  				<form id="dialog-delete">
 					<input type="password" id="password-delete" value="" class="text ui-widget-content ui-corner-all">
					<input type="hidden" id="hidden-no" value="">
					<input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
  				</form>
			</div>
			<div id="dialog-message" title="" style="display:none">
  				<p></p>
			</div>						
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>