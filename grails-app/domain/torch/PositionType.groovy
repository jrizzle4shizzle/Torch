package torch

class PositionType {

    static hasMany = [sitePermissions:String]
	
	String name
	
	Integer termLength
	
	Boolean elected
	
    static constraints = {
		name(unique:true)
		termLength()
		elected()
		sitePermissions()
    }
	
	String toString(){
		return name
	}
}
