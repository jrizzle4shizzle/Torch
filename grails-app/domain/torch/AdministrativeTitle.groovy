package torch

class AdministrativeTitle {
	static hasMany = [sitePermissions:String]
	
	String name
	
	static permissions = ["event.all", "event.create", "event.delete"];
	
    static constraints = {
		name(unique:true)
		sitePermissions()
    }
	
	String toString(){
		name
	}
}
