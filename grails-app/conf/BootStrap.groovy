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
				admin.setFirstName("Administrator")
				admin.setLastName("admin")
				admin.setRole("admin")
				
				admin.save()
				
				def dipshit = new Member()
				dipshit.setLogin("dipshit")
				dipshit.setPassword(adminPass.encodeAsHash())
				dipshit.setFirstName("Dip")
				dipshit.setLastName("Shit")
				dipshit.setRole("member")
				
				dipshit.save()
				
				def dipshit2 = new Member()
				dipshit2.setLogin("dipshit2")
				dipshit2.setPassword(adminPass.encodeAsHash())
				dipshit2.setFirstName("Dip")
				dipshit2.setLastName("Shit2")
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
