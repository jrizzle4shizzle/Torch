package torch

class SitePermissions {
		
	static List<String> permissions
	
	
	public static List<String> getPermissions(){
		//get permissions from known controllers
		if(permissions == null){
			permissions = []
			permissions.addAll PositionController.permissions
			permissions.addAll CommitteeController.permissions
			permissions.addAll EventController.permissions
			permissions.addAll MemberController.permissions
			permissions.addAll MembershipTypeController.permissions
			permissions.addAll RankController.permissions
		}
		
		return permissions
	}
	
	/**
	 * return the full list of permissions for a given user
	 * @param member
	 * @return
	 */
	public static List<String> getPermissionsForUser(torch.Member member){
		member.refresh()
		def permissionsList = []
		
		//if(member.administrativeTitle?.sitePermissions){
		//	permissionsList.addAll member.administrativeTitle?.sitePermissions
		//}
		
		if(member.membershipType?.sitePermissions){
			permissionsList.addAll member.membershipType.sitePermissions
		}
		
		if(member.rank?.sitePermissions){
			permissionsList.addAll member.rank.sitePermissions
		}
		
		return permissionsList
	}
	

}
