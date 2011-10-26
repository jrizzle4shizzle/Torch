package torch

class ApparatusType {

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
