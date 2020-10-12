<%@ include file="init.jsp" %>
<portlet:actionURL name="pageNavigate" var="pageNavigationURL" />

<portlet:renderURL var="pageURL" />

<%-- Current page = ${currPage}<br> --%>
<div class="pagination">
	<input type="button" class="${currPage eq 1 ? 'hidden' : 'btn-secondary' }" id="prepage_Previousvious" onclick="goToPage(${currPage - 1})" value="PREV" />
	<c:forEach var="j" begin="1" end="${pageCount}">
	<input type="button" class="${currPage eq j ? 'btn-primary' : 'btn-secondary' }" id="page_${j}" onclick="goToPage(${j})" value="${j}" />
	</c:forEach>
	<input type="button" class="${currPage eq pageCount ? 'hidden' : 'btn-secondary' }" id="page_Next" onclick="goToPage(${currPage + 1})" value="NEXT" />
</div>

<script type="text/javascript">
function goToPage(pageNo){
	var url = '${pageURL}'+'&page='+pageNo;
	location.href=url;
}

</script>

<style type="text/css">	 

.pagination input {
    padding: 0 7px;
    margin: 0 3px;
    border-radius: 5px;
}

.hidden{
	display: none;
}

.records-count{
	position: relative;
	float: right;
}

</style> 