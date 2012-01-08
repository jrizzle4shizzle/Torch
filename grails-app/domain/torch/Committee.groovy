package torch

class Committee {
	
	static hasMany = [members:Member]
	
	String name
	
	ElectedPosition chair
	
		
    static constraints = {
		name(unique:true)
		chair()
		members()
    }
	
	String toString(){
		name
	}
}
