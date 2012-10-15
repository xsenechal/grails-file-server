
<%--<%@ page import="springsecurity.User" %>--%>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'File')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		 <style>
		.ui-progressbar .ui-progressbar-value { background-image: url(images/pbar-ani.gif); }
	</style>
	<r:require modules="jquery-ui"/>
	<script type="text/javascript">
	function initProgressBar(){
		$("#progressbar").progressbar({
			value: 30
		});
	}
	
	</script>
	</head>
	
	<body>
	
		<div id="progressbar"></div>
		<a href="#list-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<%--		<div class="nav" role="navigation">--%>
<%--			<ul>--%>
<%--				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>--%>
<%--				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>--%>
<%--			</ul>--%>
<%--		</div>--%>
		<div id="list-user" class="content scaffold-list" role="main">
		
			<g:render template="addFileForm"></g:render>
			<h1><g:message code="default.list.label" args="[entityName]" /><g:include action="breadCrumb" params="[dir : "${currentFolder}"]"></g:include></h1>
			<g:render template="gotoDir"></g:render>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
						<g:sortableColumn property="name" title="Name" />
<%--						<g:sortableColumn property="absolutePath"  title="Absolute Path"/>--%>
						<th>Copy</th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${dirInstanceList}" status="i" var="dirInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
<%--						<td><g:link action="show" id="${fileInstance.id}">${fieldValue(bean: fileInstance, field: "username")}</g:link></td>--%>
						<td><g:link controller="FileIO" action="${list}" params="[ dir: "${dirInstance.fpath}"]">${dirInstance.fname}</g:link></td>
<%--						<td>${dirInstance.fpath}</td>--%>
						<td><g:link controller="Intra" action="copyDir" params="[ origin: "${dirInstance.fpath}"]">Copy</g:link></td>
					</tr>
				</g:each>
				</tbody>
			</table>
			files : 
			<table>
				<thead>
					<tr>
						<g:sortableColumn property="name" title="Name" />
<%--						<g:sortableColumn property="absolutePath"  title="Absolute Path"/>--%>
						<th>Down</th>
						<th>Share</th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${fileInstanceList}" status="i" var="fileInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
<%--						<td><g:link action="show" id="${fileInstance.id}">${fieldValue(bean: fileInstance, field: "username")}</g:link></td>--%>
						<td><g:link controller="FileIO" action="view" params="[ path: "${fileInstance.fpath}" ]">${fileInstance.fname}</g:link></td>
						<td><g:link controller="FileIO" action="download" params="[ path: "${fileInstance.fpath}" ]">Down</g:link></td>
<%--					
<%--						<td>${fileInstance.fpath}</td>--%>
						<td><g:link controller="ShareFile" action="create" params="[ name: "${fileInstance.fname}",AbsolutePath: "${fileInstance.fpath}"]">Share</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
<%--			<div class="pagination">--%>
<%--				<g:paginate total="${fileInstanceTotal}" />--%>
<%--			</div>--%>
		</div>
	</body>
</html>
