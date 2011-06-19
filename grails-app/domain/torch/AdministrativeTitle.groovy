package torch

class AdministrativeTitle {
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
