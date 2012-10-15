
<%@ page import="gfileserver.ShareFile" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'shareFile.label', default: 'ShareFile')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-shareFile" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-shareFile" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list shareFile">
			
				<g:if test="${shareFileInstance?.absolutePath}">
				<li class="fieldcontain">
					<span id="absolutePath-label" class="property-label"><g:message code="shareFile.absolutePath.label" default="Absolute Path" /></span>
					
						<span class="property-value" aria-labelledby="absolutePath-label"><g:fieldValue bean="${shareFileInstance}" field="absolutePath"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${shareFileInstance?.dlCount}">
				<li class="fieldcontain">
					<span id="dlCount-label" class="property-label"><g:message code="shareFile.dlCount.label" default="Dl Count" /></span>
					
						<span class="property-value" aria-labelledby="dlCount-label"><g:fieldValue bean="${shareFileInstance}" field="dlCount"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${shareFileInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="shareFile.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${shareFileInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${shareFileInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="shareFile.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="user" action="show" id="${shareFileInstance?.user?.id}">${shareFileInstance?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${shareFileInstance?.id}" />
					<g:link class="edit" action="edit" id="${shareFileInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
