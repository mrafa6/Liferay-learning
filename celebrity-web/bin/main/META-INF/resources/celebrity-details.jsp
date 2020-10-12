<%@ include file = "init.jsp"%>

<portlet:renderURL var="backURL">
</portlet:renderURL>

<a href="<%= backURL.toString()%>">Back</a>

<liferay-ui:header title="celebrity.detail.header"/>

${celebrity.getName()}<br>
${celebrity.getProfession()}<br>
${celebrity.getCountry()}<br>
${celebrity.getNickName()}