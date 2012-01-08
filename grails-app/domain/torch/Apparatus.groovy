package torch

class Apparatus {
	
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
    }
	
	String toString(){
		name
	}
}
