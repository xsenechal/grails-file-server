package gfileserver

import org.example.User

class ShareFile {
	String name
	String AbsolutePath
	Boolean isFolder = false
	Integer dlCount = 0
	User user
    static constraints = {
    }
}
