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
				<form id="search_form" action="${pageContext.request.contextPath }/guestbook?page=${page }&kwd=${kwd } " method="post">
					<input type="text" id="kwd" name="kwd" value="${kwd }">
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
						<c:if test="${vo.depth == 0}">
							<td style="text-align:left; padding-left:0px" >
								<a href="${pageContext.request.contextPath }/board?no=${vo.no}">${vo.title }</a>
							</td>
						</c:if>
						
						<c:if test="${vo.depth == 1}">
							<td style="text-align:left; padding-left:15px">
							<img src="${pageContext.request.contextPath }/assets/images/reply.png">
								<a href="${pageContext.request.contextPath }/board?no=${vo.no}">${vo.title }</a>
							</td>
						</c:if>
						
						<c:if test="${vo.depth == 2}">
							<td style="text-align:left; padding-left:30px">
							<img src="${pageContext.request.contextPath }/assets/images/reply.png">
								<a href="${pageContext.request.contextPath }/board?no=${vo.no}">${vo.title }</a>
							</td>
						</c:if>
						<c:if test="${vo.depth == 3}">
							<td style="text-align:left; padding-left:45px">
							<img src="${pageContext.request.contextPath }/assets/images/reply.png">
								<a href="${pageContext.request.contextPath }/board?no=${vo.no}">${vo.title }</a>
							</td>
						</c:if>
						<c:if test="${vo.depth > 3}">
							<td style="text-align:left; padding-left:45px">
							<img src="${pageContext.request.contextPath }/assets/images/reply.png">
								<a href="${pageContext.request.contextPath }/board?no=${vo.no}">${vo.title }</a>
							</td>
						</c:if>
						
							<td>${vo.uname }</td>
							<td>${vo.hit }</td>
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
					<c:choose>
						<c:when test="${startOffset == 1 }">
							<li class="disabled">◀</li>
						</c:when>
						<c:otherwise>
							<li><a href="${pageContext.request.contextPath }/board?page=${startOffset-1 }">◀</a></li>
						</c:otherwise>
					</c:choose>
					<c:forEach var="startOffset" begin="1" end="${toTalCount }" step="1">
						<li><a href="${pageContext.request.contextPath }/board?page=${startOffset }">${startOffset }</a></li>
					</c:forEach>
					<c:choose>
						<c:when test="${startOffset == toTalCount }">
							<li class="disabled">▶</li>
						</c:when>
						<c:otherwise>
							<li><a href="${pageContext.request.contextPath }/board?page=${startOffset+1 }">▶</a></li>
						</c:otherwise>
					</c:choose>
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