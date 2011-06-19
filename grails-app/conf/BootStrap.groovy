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
				
				admin.save()
				
				def dipshit = new Member()
				dipshit.setLogin("user")
				dipshit.setPassword(adminPass.encodeAsHash())
				dipshit.setFirstName("User")
				dipshit.setLastName("Alpha")
				dipshit.setRole("member")
				
				dipshit.save()
				
				def dipshit2 = new Member()
				dipshit2.setLogin("user2")
				dipshit2.setPassword(adminPass.encodeAsHash())
				dipshit2.setFirstName("User")
				dipshit2.setLastName("Bravo")
				dipshit2.setRole("member")
				
				dipshit2.save()
				
			break
			
			case "production":
			break
			
			default:
				println("other: "+GrailsUtil.environment)
			break
		}
		
    }
	
    def destroy = {
    }
}
