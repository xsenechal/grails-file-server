package gfileserver


import grails.plugins.springsecurity.Secured

import org.example.Role;
import org.example.User
import org.example.UserRole


class FileIOController {
//http://pleac.sourceforge.net/pleac_groovy/directories.html
	def springSecurityService
	@Secured(['ROLE_ALLACCESS','ROLE_USER'])
	def index() {
		def user
		if(springSecurityService.isLoggedIn()) {
			user = User.get(springSecurityService.principal.id)
			UserRole usrRole = UserRole.findByUser(user)
			Role role = usrRole.role
			if (role.authority.equals('ROLE_ALLACCESS'))
				redirect action:"list"
			else if (role.authority.equals('ROLE_USER'))
			redirect action:"listU"
		}
	}

	@Secured(['ROLE_ALLACCESS'])
   def addFile = {
	   def currentPath = params.path
	   println currentPath
	   //handle uploaded file
	   def uploadedFile = request.getFile('payload')
	   if(!uploadedFile.empty){
		 println "Class: ${uploadedFile.class}"
		 println "Name: ${uploadedFile.name}"
		 println "OriginalFileName: ${uploadedFile.originalFilename}"
		 println "Size: ${uploadedFile.size}"
		 println "ContentType: ${uploadedFile.contentType}"
		 uploadedFile.transferTo( new File( currentPath, uploadedFile.originalFilename))
		 
	   }
   	redirect action:"list", params: [dir: currentPath]
   }
   @Secured(['ROLE_ALLACCESS'])
  def write = {
		def directory = 'C:/'
		def folderName = 'testFolder'
		def c
	   
		def txtFileInfo = []
	   
		String a = "Today is a new day"
		String b = "Tomorrow is the future"
		String d = "Yesterday is the past"
	   
		txtFileInfo << a
		txtFileInfo << b
		txtFileInfo << d
//           c = createFolder(directory, folderName) //this simply creates a folder to drop the txt file in
		writeToFile(directory, "garbage", ".txt", txtFileInfo)
		render "fichier ecrit"
	  }
  @Secured(['ROLE_ALLACCESS','ROLE_USER'])
  def view = {
	  File file = new File(params.path)
	  if (file.exists()){
			 //get mime type from the file and
			 FileNameMap fileNameMap = URLConnection.getFileNameMap();
			 def contentType = fileNameMap.getContentTypeFor(file.name)
			 if (contentType)
					response.setContentType(contentType)
			 else
//                         response.setContentType("application/octet-stream")
			 //println contentType
			
			
//                  response.setContentType("application/octet-stream")
//                  response.setHeader("Content-disposition", "attachment;filename=\"${file.getName()}\"") This avoid the display in the browser
			 response.contentLength = file.length()
			 response.outputStream << file.newInputStream()
			 response.outputStream.flush();
			 return true;
		  }
	}
  	@Secured(['ROLE_ALLACCESS','ROLE_USER'])
	  def downloadF = {
		  File file = new File(params.path)
		  if (file.exists()){
				 //get mime type from the file and
//				 FileNameMap fileNameMap = URLConnection.getFileNameMap();
				response.setContentType("application/octet-stream")
				response.setHeader("Content-disposition", "attachment;filename=\"${file.getName()}\"")// This avoid the display in the browser
				 response.contentLength = file.length()
				 response.outputStream << file.newInputStream()
				 response.outputStream.flush();
				 return true;
		  }
	}
	  @Secured(['ROLE_ALLACCESS','ROLE_USER'])
	  def breadCrumb ={
			def dir = params.dir
			def file = new File(dir)
			def lst = []
			lst.add(file)
			def tmpFile = file.parentFile
			while (tmpFile != null){
				   lst.add(tmpFile)
				   tmpFile = tmpFile.parentFile
			}
//           def res = ""
//           lst.each {
//                  res += "<p>nom : ${it.getName()} + path : ${it.getAbsolutePath()} </p>"
//           }
			def sortedLst = lst.reverse()
			def resLst = []
			resLst.add([name:"${grailsApplication.config.app.defaultRoot}", path:"${grailsApplication.config.app.defaultRoot}"])//"c:" + File.separator])
			sortedLst.each{
				   resLst.add([name:it.getName(), path:it.getAbsolutePath()])
			}
			resLst.last().path = ""
//           render res
			[breadCrumbList : resLst]
	  }
	  @Secured(['ROLE_ALLACCESS'])
	  def list = {
			def dir = params.dir ?: "${grailsApplication.config.app.defaultRoot}"//'c://'
			def lstFile = []
			def lstDir = []

//			candidateFiles = new File(basedir).listFiles()
//			allDigits = { it.name =~ /^\d+$/ }
			def isDir = { it.isDirectory() }
			def isFile = { it.isFile() }
//			dirs = candidateFiles.findAll(isDir).findAll(allDigits)*.canonicalPath.sort()
			
			def theFolder = new File(dir).listFiles().findAll(isDir).canonicalPath.sort()
			theFolder.each {
				def f= new File (it)
				lstDir << [fname:f.name, fpath:f.absolutePath]
			}
			
			def theFiles = new File(dir).listFiles().findAll(isFile).canonicalPath.sort()
			theFiles.each {
				def f= new File (it)
				lstFile << [fname:f.name, fpath:f.absolutePath]
			}
//			def lstFile = new File(dir)
//			new File(dir).eachFile() { file->
//				   if (file.isFile()) {
//						  lstFile << [fname:file.getName(), fpath:file.getAbsolutePath()] // + file.getName()
//				   }
//				   else{
//						  lstDir << [fname:file.getName(), fpath:file.getAbsolutePath()]
//				   }
//			}
			[list:"list",fileInstanceList: lstFile, dirInstanceList: lstDir, currentFolder:dir]
	  }
	  @Secured(['ROLE_USER'])
	  def listU = {
		  def user = User.get(springSecurityService.principal.id)
		  def files = ShareFile.findAllByUser(user)
		  def lstFile = []
		  files.each {
			  lstFile.add([fname: it.name, fpath:it.AbsolutePath])
		  }
		  render (view: "list", model:[list:"listU",fileInstanceList: lstFile])
	  }
	  public void writeToFile(def directory, def fileName, def extension, def infoList) {
			File file = new File("$directory/$fileName$extension")
	 
			infoList.each {
				   file << ("${it}\n")
			}
	  }
}