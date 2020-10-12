<%@ include file="init.jsp" %>

<portlet:actionURL name="addCelebrity" var="addCelebrityURL">
</portlet:actionURL>

<liferay-ui:success key="addedCelebrity" message="record.added" />
     
<div class="container-fluid-1280">
	<liferay-ui:header title="celebrity.add.header"/>
	<aui:form id="addRecord" action="${addCelebrityURL}" name="addRecord" method="post">
	
		<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:input name="cel-name" placeholder="name.placeholder">
				<aui:validator name="required" errorMessage="name.error.msg"></aui:validator>
			</aui:input>
			
			<aui:input name="cel-profession" placeholder="profession.placeholder">
				<aui:validator name="required" errorMessage="profession.error.msg"></aui:validator>
			</aui:input>
			
			<aui:input name="cel-country" placeholder="country.placeholder" >
				<aui:validator name="required" errorMessage="country.error.msg"></aui:validator>
			</aui:input>
			
			<aui:input name="cel-nickname" placeholder="nickname.placeholder"/>
		</aui:fieldset>
		</aui:fieldset-group>
		
		<%--Buttons. --%>
         <aui:button-row>
             <aui:button cssClass="btn btn-primary" type="submit" value="add.celebrity"/>
             <aui:button class="btn btn-secondary" id="resetForm" type="reset" value="Reset"/>
         </aui:button-row>
		
	</aui:form>

</div>

<script type="text/javascript">
$(document).ready(function(){
	console.log('Document ready called.....');
	$("[id*=cel]").val(' ');
});



</script>