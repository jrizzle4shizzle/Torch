package torch

class Committee {
	
	static hasMany = [members:Member]
	
	String name
	Member chair
	
		
    static constraints = {
		name(unique:true)
		chair()
		members()
    }
	
	String toString(){
		name
	}
}
