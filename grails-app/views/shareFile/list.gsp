
<%@ page import="gfileserver.ShareFile" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'shareFile.label', default: 'ShareFile')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-shareFile" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-shareFile" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="absolutePath" title="${message(code: 'shareFile.absolutePath.label', default: 'Absolute Path')}" />
					
						<g:sortableColumn property="dlCount" title="${message(code: 'shareFile.dlCount.label', default: 'Dl Count')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'shareFile.name.label', default: 'Name')}" />
					
						<th><g:message code="shareFile.user.label" default="User" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${shareFileInstanceList}" status="i" var="shareFileInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${shareFileInstance.id}">${fieldValue(bean: shareFileInstance, field: "absolutePath")}</g:link></td>
					
						<td>${fieldValue(bean: shareFileInstance, field: "dlCount")}</td>
					
						<td>${fieldValue(bean: shareFileInstance, field: "name")}</td>
					
						<td>${fieldValue(bean: shareFileInstance, field: "user")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${shareFileInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
