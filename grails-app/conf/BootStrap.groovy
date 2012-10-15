import org.example.Role
import org.example.User
import org.example.UserRole

class BootStrap {

	def springSecurityService
	
    def init = { servletContext ->  
		def accessRole = Role.findByAuthority('ROLE_ALLACCESS') ?: new Role(authority: 'ROLE_ALLACCESS').save(failOnError: true)
		def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save(failOnError: true)
        def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(failOnError: true)
		
		//Ajout de admin
		def adminUser = User.findByUsername('admin') ?: new User(
			username: 'admin',
			password: springSecurityService.encodePassword('#$/1N:[!^$r2&r,!$06N4)'),
			enabled: true).save(failOnError: true)

		if (!adminUser.authorities.contains(adminRole)) {
			UserRole.create adminUser, adminRole
		}
		// Ajout de Moi
		def thexavone = User.findByUsername('thexavone') ?: new User(
			username: 'thexavone',
			password: springSecurityService.encodePassword('dj/V<42CDeZW~3p28LehR)'),
			enabled: true).save(failOnError: true)

		if (!thexavone.authorities.contains(accessRole)) {
			UserRole.create thexavone, accessRole
		}
		// Ajout de Maminou
		def aline = User.findByUsername('aline') ?: new User(
			username: 'aline',
			password: springSecurityService.encodePassword("G.y]JU{b[G6[?)#@%eU67"),
			enabled: true).save(failOnError: true)

		if (!aline.authorities.contains(userRole)) {
			UserRole.create aline, userRole
		}


    }
    def destroy = {
    }
}
