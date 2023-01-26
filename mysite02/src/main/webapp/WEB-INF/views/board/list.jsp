<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
			<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					
					<c:set var="count" value="${fn:length(list) }" />
					<c:forEach items="${list }" var="vo" varStatus="status">
					<tr>
						<td>[${count - status.index }]</td>
						<td style="text-align:left; padding-left:0px" >
							<a href="${pageContext.request.contextPath }/board?a=view&no=${vo.no}">${vo.title }</a>
						</td>
						<td>${vo.uname }</td>
						<td>${vo.hit}</td>
						<td>${vo.regdate }</td>
						<c:if test="${authUser.no == vo.userno}">
								<td><a href="${pageContext.request.contextPath }/board?a=delete&no=${vo.no }&userno=${authUser.no }" class="del">삭제</a></td>
						</c:if>	
						
					</tr>
					</c:forEach>
					
					

				</table>
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li class="selected">2</li>
						<li><a href="">3</a></li>
						<li>4</li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div>					
				<!-- pager 추가 -->
				<c:if test="${not empty authUser }">
				<div class="bottom">
					<a href="${pageContext.request.contextPath }/board?a=writeform&userno=${authUser.no }" id="new-book">글쓰기</a>
				</div>
				</c:if>				
			</div>
		</div>
			<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
			<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	</div>
</body>
</html>