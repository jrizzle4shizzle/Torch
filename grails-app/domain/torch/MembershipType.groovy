package torch

class MembershipType {
	static hasMany = [sitePermissions:String]
	
	String name
	
    static constraints = {
		name(unique:true)
		sitePermissions()
    }
	
	String toString(){
		name
	}
}
