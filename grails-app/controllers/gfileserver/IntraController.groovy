package gfileserver

import java.nio.file.attribute.PosixFilePermissions
import java.util.jar.Attributes

import grails.converters.JSON
import org.apache.commons.io.FileUtils
import org.springframework.security.access.annotation.Secured

@Secured(['ROLE_ALLACCESS'])
class IntraController {

    def index() { }
	
	def copyDir = {
		def srcdir = new File(params.origin?:"c:/tmp") // path to src
		
		render (template: "intraCopy", model:[intraOriginDir:"$srcdir"])
	}
	def exeCopy = {
		def srcdir = new File(params.origin?:"c:/tmp")
		def trgt = params.target?:"c:/testUtil"
		def destdir = new File(trgt) // path to dest
		def error
		try{ 
			//FilesUtil ne crée pas le dossier destination
			def destRootDir = new File("$trgt/${srcdir.name}")
			destRootDir.mkdir()
			destRootDir.setWritable(true)
//			def st2 = PosixFilePermissions.fromString("rwxrwxrwx");
//			Attributes.setPosixFilePermissions(destRootDir, st2);
			def preserveFileStamps = true
			FileUtils.copyDirectory(srcdir, destRootDir, preserveFileStamps)
		}
		catch (Exception e){
			error = e
			log.error(e)
		}
		render "dircopy from \"$srcdir\" to \"$destdir\" : $error"
	}
	def progressCopy(){
		def src = params.origin
		// taille du rep source
		long tailleRepSrc = FileUtils.sizeOfDirectory(new File(src))
		// taille du rep dest
		long tailleRepDest = FileUtils.sizeOfDirectory(new File(params.target?:"c:/testUtil"))
		// pourcentage de copie
		float percent = tailleRepDest / tailleRepSrc * 100
		// on affiche les infos
		System.out.println("Toto : "+percent)
		System.out.println("Src : "+tailleRepSrc)
		System.out.println("Dest : "+tailleRepDest)
		System.out.println("******************************")
		def res = [src:tailleRepSrc, dest:tailleRepDest, percent:percent]

		render  res as JSON
	}
}
