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
				admin.setRole("admin")
				
				admin.save()
				println("admin login="+admin.getLogin())
				println("admin password="+admin.getPassword())
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
