
<g:uploadForm action="addFile" method="post" >
  <!-- SNIP -->
  <tr class="prop">
    <td valign="top" class="name">
      <label for="payload">Upload File:</label>
    </td>
    <td valign="top">
      <input type="file" id="payload" name="payload"/>
    </td>
    <input type="text" value="${currentFolder}" name="path" />
    <input type="submit" value="upload" />	
  </tr>
</g:uploadForm>