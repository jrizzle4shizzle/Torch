package torch

class SitePermissions {
		
	static List<String> permissions
	
	
	public static List<String> getPermissions(){
		//get permissions from known controllers
		if(permissions == null){
			permissions = []
			permissions.addAll EventController.permissions
			
		}
		return permissions
	}
	
	
	public static List<String> getPermissionsForUser(torch.Member member){
		member.refresh()
		def permissionsList = []
		if(member.administrativeTitle?.sitePermissions){
			permissionsList.addAll member.administrativeTitle?.sitePermissions
		}
		return permissionsList
	}
	

}
