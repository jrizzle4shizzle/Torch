package torch

class Position {

    static hasMany = [sitePermissions:String]
	
	String name
	
	Member member
	
	PositionType type
	
	Date start
	
	Date end
	
	Date termEnd
	
    static constraints = {
		name(unique:true)
		member()
		type()
		start()
		end()
		termEnd()
		sitePermissions()
    }
	
	String toString(){
		return name
	}
}
