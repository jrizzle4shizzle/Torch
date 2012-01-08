package torch

class ElectedPosition {

    static hasMany = [sitePermissions:String]
	
	String name
	
	Member member
	
	PositionType type
	
	Date start
	
	Date finish
	
	Date termEnd
	
    static constraints = {
		name(unique:true)
		member()
		type()
		start()
		finish()
		termEnd()
		sitePermissions()
    }
	
	String toString(){
		return name
	}
}
