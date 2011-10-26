package torch

class Apparatus {

    static hasMany = [sitePermissions:String]
	
	String name
	ApparatusType type
	Integer maxRiding
	Integer minRiding
	String modelAndYear
	
    static constraints = {
		name(unique:true)
		type()
		maxRiding()
		minRiding()
		modelAndYear()
		sitePermissions()
    }
	
	String toString(){
		name
	}
}
