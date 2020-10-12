<%@ include file="init.jsp" %>

<portlet:actionURL name="deleteCelebrity" var="deleteCelebrityURL" />

<portlet:actionURL name="viewDetails" var="viewCelebrityURL"  />


<liferay-ui:success key="deletedCelebrity" message="record.deleted"/>
<div class="records-count">Total Records ${celbCount}</div>
<liferay-ui:header title="celebrity.list.header"/>

<div class="celebrity-list">
	
	<%@ include file="pagination.jsp" %>
	<form action="#" name="formDeleteAction" id="formAction" method="post">
	<div class="table-celebrity">
	
		<c:if test="${celbCount ne 0}">
			<table class="table table-striped">
			  <thead class="thead-light">
			    <tr>
			      <!-- <th scope="col">Id</th> -->
			      <th scope="col">Name</th>
			      <th scope="col">Profession</th>
			      <th scope="col">Country</th>
			      <th scope="col">Nickname</th>
			      <th scope="col">Delete</th>
			    </tr>
			  </thead>
			  <tbody>
			  	<c:forEach items="${celebrities}" var="celebrity"  varStatus="status" begin="0" end="9">
	   				<tr>
				      <th scope="row"><a href="javascript:void(0)" Class="view-celebrity" onClick="viewDetails('${celebrity.getCelebrityId()}')">${celebrity.getName()}</a></th>
				      <td>${celebrity.getProfession()}</td>
				      <td>${celebrity.getCountry()}</td>
				      <td>${celebrity.getNickName()}</td>
				      <td><a href="javascript:void(0)" Class="delete-celebrity" onclick="deleteRecordById('${celebrity.getCelebrityId()}')">Delete</a></td>
				    </tr>
				</c:forEach>  
			  </tbody>
			</table>
		</c:if>
	</div>
</form>
</div>

<script type="text/javascript">

function deleteRecordById(id){
	console.log("Delete Button Clicked >>> ");
	
	var url = '${deleteCelebrityURL}';
	console.log("line-1 >>> "+url);
	
	var celebId = id;
	console.log("id...."+celebId);
	
 	url = url + "&celebrityId="+celebId;
	console.log("line-2 >>> "+url); 
	
	document.forms["formDeleteAction"].action = url;
	document.forms["formDeleteAction"].submit();
}

function viewDetails(id){
	console.log('Details button clicked');
	var url = '${viewCelebrityURL}' + '&id=' + id;
	location.href = url;
}


</script>
