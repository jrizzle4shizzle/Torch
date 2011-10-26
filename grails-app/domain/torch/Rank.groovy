package torch

class Rank {
	static hasMany = [sitePermissions:String]
	
	String name
	
	Integer precidence
	
	Boolean officer
	
	Boolean nonRiding
	
    static constraints = {
		name(unique:true)
		precidence(unique:true)
		officer()
		nonRiding()
		sitePermissions()
    }
	
	String toString(){
		return name
	}
}
