<%@ include file="init.jsp" %>

<portlet:actionURL name="deleteCelebrity" var="deleteCelebrityURL" />

<portlet:actionURL name="viewDetails" var="viewCelebrityURL"  />

<portlet:actionURL name="searchCelebrity" var="searchCelebrityURL"  />


<div class="top-div">
	<input type="text" class="search-box" name="q" id="q" placeholder="Search"/>
	<select name="criteria" id="search-type">
		  <option value="none">Any</option>
		  <option value="country">Country</option>
		  <option value="profession">Profession</option>
		  <option value="name">Name</option>
	</select>
	<input type="button" class="search-btn" Value="Search" onclick="searchCelebrity()"/>
</div>

<div class="letter-div">


</div>

<div class="main-div">
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
			  	<c:forEach items="${celebrities}" var="celebrity"  varStatus="status">
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

function searchCelebrity(){
	var url = '${searchCelebrityURL}';
	var keyword = $("#q").val();
	var filter = $("[name='criteria']").val();
	console.log('keyword...'+keyword+'.....filter....'+filter);
	url = url + '&q=' + keyword + '&findBy='+filter;
	location.href = url;
}


</script>

<style type="text/css">

input.search-box {
    margin-left: 120px;
    display: flex;
    width: 35%;
    border-radius: 15px 0 0 15px;
    padding: 5px 15px;
    border: 2px tomato solid;
    float: left;
    border-right: 1px dashed tomato;
}

input.search-btn{
    border: tomato;
    width: 12%;
    border-radius: 0 15px 15px 0;
    color: #FFFFFF;
    background: tomato;
    height: 38px;
    font-weight: 600;
    letter-spacing: 0.8px;
    font-size: larger;
}

div.top-div{
	margin: 10px;
}

div.main-div{

}

select#search-type {
    margin: 0px;
    float: left;
    height: 38px;
    width: 110px;
    border-top: 2px solid tomato;
    border-bottom: 2px solid tomato;
    border-right: none;
    border-left: none;
    padding: 0 5px;
}



</style>
