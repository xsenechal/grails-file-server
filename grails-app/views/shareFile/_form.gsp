<%@ page import="gfileserver.ShareFile" %>



<div class="fieldcontain ${hasErrors(bean: shareFileInstance, field: 'absolutePath', 'error')} ">
	<label for="absolutePath">
		<g:message code="shareFile.absolutePath.label" default="Absolute Path" />
		
	</label>
	<g:textField name="AbsolutePath" value="${shareFileInstance?.absolutePath}"/>
</div>


<div class="fieldcontain ${hasErrors(bean: shareFileInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="shareFile.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${shareFileInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: shareFileInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="shareFile.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${org.example.User.list()}" optionKey="id" required="" value="${shareFileInstance?.user?.id}" class="many-to-one"/>
</div>

