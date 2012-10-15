<%--<g:if test="${ breadCrumbList[0]}">--%>
<%--	<g:link controller="FileIO" action="list" params="[ dir: "${breadCrumbList[0].path}" ]">${breadCrumbList[0].path}</g:link>--%>
<%--</g:if>--%>
<g:each in="${breadCrumbList}" status="i" var="breadCrumbInstance">
	<g:if test="${ breadCrumbInstance.path}">
		<g:link controller="FileIO" action="list" params="[ dir: "${breadCrumbInstance.path}" ]">${breadCrumbInstance.name}</g:link>/
		
	</g:if>
	<g:else>
		${breadCrumbInstance.name}
	</g:else>
</g:each>