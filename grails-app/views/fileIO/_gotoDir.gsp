<div id="search">
	<g:form url='[controller: "FileIO", action: "list"]'
			id="fileSearchForm"
			name="dir"
			method="get">
		<g:textField style="width:600px;" name="dir" value="${params.dir}"/>
		<input type="submit" value="Go to a Dir" />		
	</g:form>
</div>