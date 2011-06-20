package torch

class Rank {
	static hasMany = [sitePermissions:String]
	
	String name
	
    static constraints = {
		name(unique:true)
		sitePermissions()
    }
	
	String toString(){
		return name
	}
}
