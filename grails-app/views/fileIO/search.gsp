<div id="search">
	<g:form url='[controller: "FileIO", action: "search"]'
			id="fileSearchForm"
			name="fileSearchForm"
			method="get">
		<g:textField name="q" value="${params.q}"/>
		<input type="submit" value="Find a file" />		
	</g:form>
</div>