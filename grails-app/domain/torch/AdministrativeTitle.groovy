package torch

class AdministrativeTitle {

	String name
	
    static constraints = {
		name(unique:true)
    }
	
	String toString(){
		name
	}
}
