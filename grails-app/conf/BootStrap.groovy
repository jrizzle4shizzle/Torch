import torch.Member
import grails.util.GrailsUtil

class BootStrap {

    def init = { servletContext ->
		switch(GrailsUtil.environment){
			
			case "development":
				println("development")
				def admin = new Member()
				admin.setLogin("admin")
				String adminPass = "password"
				admin.setPassword(adminPass.encodeAsHash())
				admin.setFirstName("Admin")
				admin.setLastName("istrator")
				admin.setRole("admin")
				admin.setActive(true)
				
				admin.save()
				
				def user = new Member()
				user.setLogin("user")
				user.setPassword(adminPass.encodeAsHash())
				user.setFirstName("User")
				user.setLastName("Alpha")
				user.setRole("member")
				user.setActive true
				
				user.save()
				
				def user2 = new Member()
				user2.setLogin("user2")
				user2.setPassword(adminPass.encodeAsHash())
				user2.setFirstName("User")
				user2.setLastName("Bravo")
				user2.setRole("member")
				user2.setActive true
				
				user2.save()
				
				def user3 = new Member()
				user3.setLogin("user3")
				user3.setPassword(adminPass.encodeAsHash())
				user3.setFirstName("User")
				user3.setLastName("Bravo")
				user3.setRole("member")
				user3.setActive false
				
				user3.save()
				
			break
			
			case "production":
				//def admin = new Member()
				//admin.setLogin("admin")
				//String adminPass = "sUp3rS3cr3t!"
				//admin.setPassword(adminPass.encodeAsHash())
				//admin.setFirstName("Admin")
				//admin.setLastName("istrator")
				//admin.setRole("admin")
				//admin.setActive(true)
				
				//admin.save()
			break
			
			default:
				println("other: "+GrailsUtil.environment)
			break
		}
		
    }
	
    def destroy = {
    }
}
