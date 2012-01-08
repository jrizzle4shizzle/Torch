package torch

class ApparatusType {

	
	String name
	
    static constraints = {
		name(unique:true)
    }
	
	String toString(){
		name
	}
}
