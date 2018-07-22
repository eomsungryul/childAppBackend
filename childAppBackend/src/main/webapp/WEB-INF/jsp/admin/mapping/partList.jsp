<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set value="${pageContext.request.contextPath}" var="contextPath" />
<c:set value="${ requestScope['javax.servlet.forward.servlet_path'] }" var="requestUri" />
<%
  String contextPath = (String)request.getContextPath();
%>
<table class="table table-hover">
	<thead>
		<tr>
			<th>
<!-- 			<input type="checkbox" id="checkall" onclick="window.ESR23Common.checkbox_checkall('checkall','partChk')"> -->
			</th>
			<th>부위명</th>
		</tr>
	</thead>
	<tbody id="part_table">
		<c:if test="${fn:length(resultList)!=0}">
			<c:forEach var="result" items="${ resultList }" varStatus="status">
				<tr style="cursor: pointer;" onclick="partClick(this,${ result.code });">
					<td> 
					<input type="checkbox" id="partChk${status.count}" name="partChk" value="${ result.code}" 
					onclick="chkBoxOnClick(this,${ result.code });" <c:if test="${result.mapping_yn == 'Y'}"> checked="checked"</c:if> >
					</td>
					<td>
						${ result.code_value}
<%-- 					<label for="partChk${status.count}">${ result.code_value}${result.mapping_yn == 'Y'}${result.mapping_yn}</label> --%>
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${fn:length(resultList)==0}">
			<tr>
			 	<td colspan="2" class="text-center"> 운동 부위가 존재하지 않습니다.</td>
			</tr>
		</c:if>
	</tbody>
</table>
	