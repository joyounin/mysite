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
				<form id="search_form" action="${pageContext.request.contextPath }/board" method="post">
					<input type="text" id="keyword" name="keyword" value="${keyword }">
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
					
					<c:forEach items="${list }" var="vo" varStatus="status" >
						<tr>
						<td>[${vo.no }]</td>
						<c:if test="${vo.depth == 0}">
							<td style="text-align:left; padding-left:0px" >
								<a href="${pageContext.request.contextPath }/board/view?no=${vo.no}">${vo.title }</a>
							</td>
						</c:if>
						
						<c:if test="${vo.depth == 1}">
							<td style="text-align:left; padding-left:15px">
							<img src="${pageContext.request.contextPath }/assets/images/reply.png">
								<a href="${pageContext.request.contextPath }/board/view?no=${vo.no}">${vo.title }</a>
							</td>
						</c:if>
						
						<c:if test="${vo.depth == 2}">
							<td style="text-align:left; padding-left:30px">
							<img src="${pageContext.request.contextPath }/assets/images/reply.png">
								<a href="${pageContext.request.contextPath }/board/view?no=${vo.no}">${vo.title }</a>
							</td>
						</c:if>
						<c:if test="${vo.depth == 3}">
							<td style="text-align:left; padding-left:45px">
							<img src="${pageContext.request.contextPath }/assets/images/reply.png">
								<a href="${pageContext.request.contextPath }/board/view?no=${vo.no}">${vo.title }</a>
							</td>
						</c:if>
						<c:if test="${vo.depth > 3}">
							<td style="text-align:left; padding-left:45px">
							<img src="${pageContext.request.contextPath }/assets/images/reply.png">
								<a href="${pageContext.request.contextPath }/board/view?no=${vo.no}">${vo.title }</a>
							</td>
						</c:if>
						
							<td>${vo.name }</td>
							<td>${vo.hit }</td>
							<td>${vo.reg_date }</td>
							<c:if test="${authUser.no == vo.userno}">
								<td><a href="${pageContext.request.contextPath }/board/delete?&no=${vo.no }" class="del">삭제</a></td>
							</c:if>
							</tr>
					</c:forEach>
					
				</table>
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
					<c:choose>
						<c:when test="${page == 1 }">
							<li class="disabled">◀</li>
						</c:when>
						<c:otherwise>
							<li><a href="${pageContext.request.contextPath }/board?page=${page-1 }&keyword=${keyword }">◀</a></li>
						</c:otherwise>
					</c:choose>
					<c:forEach var="i" begin="${beginPage }" end="${endPage }" step="1">
						<c:choose>
							<c:when test="${page eq i }">
								<li class=selected>${i }</li>
							</c:when>
							<c:otherwise>
								<li><a href="${pageContext.request.contextPath }/board?page=${i }&keyword=${keyword }">${i }</a></li>
							</c:otherwise>
						</c:choose>
						
					</c:forEach>
					<c:choose>
						<c:when test="${page == totalPage }">
							<li class="disabled">▶</li>
						</c:when>
						<c:otherwise>
							<li><a href="${pageContext.request.contextPath }/board?page=${page+1 }&keyword=${keyword }">▶</a></li>
						</c:otherwise>
					</c:choose>
					</ul>
				</div>					
				<!-- pager 추가 -->
				
				<c:if test="${not empty authUser }">
				<div class="bottom">
					<a href="${pageContext.request.contextPath }/board/write" id="new-book">글쓰기</a>
				</div>
				</c:if>				
			</div>
		</div>
			<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
			<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	</div>
</body>
</html>