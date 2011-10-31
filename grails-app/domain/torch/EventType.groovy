package torch

class EventType {

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
