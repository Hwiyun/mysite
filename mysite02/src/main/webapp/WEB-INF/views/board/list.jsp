<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value=""> <input type="submit" value="찾기">
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
							<td>${count-status.index }</td>
							<td style="text-align:left; padding-left:${vo.depth*15 }px">
								<c:if test="${vo.depth > 0 }">
									<img src="${pageContext.request.contextPath }/assets/images/reply.png">
								</c:if>
								<a href="${pageContext.request.contextPath }/board?a=viewform&no=${vo.no }">${vo.title }</a>
							</td>
							<td>${vo.name }</td>
							<td>${vo.hit }</td>
							<td>${vo.regDate }</td>

							<td>
								<c:if test="${authUser.no == vo.userNo}">

									<a href="${pageContext.request.contextPath }/board?a=delete&no=${vo.no }" class="del">삭제</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>

				</table>
				<!-- pager 추가 -->
				<div class="pager">
					<ul>

						<li><a href="${pageContext.request.contextPath }/board?a=list&pageNo=${currentPage-1}">◀</a></li>
						<c:forEach begin="${beginPage }" end="${endPage }" step="1" var="index">
							<c:choose>
								<c:when test="${currentPage == index }">

									<li class="selected"><a href="${pageContext.request.contextPath }/board?a=list&pageNo=${index }">${index }</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="${pageContext.request.contextPath }/board?a=list&pageNo=${index }">${index }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<li><a href="${pageContext.request.contextPath }/board?a=list&pageNo=${currentPage+1}">▶</a></li>
					</ul>
				</div>
				<!-- pager 추가 -->
				<div class="bottom">
					<c:if test="${not empty authUser }">
						<a href="${pageContext.request.contextPath }/board?a=writeform" id="new-book">글쓰기</a>
					</c:if>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>