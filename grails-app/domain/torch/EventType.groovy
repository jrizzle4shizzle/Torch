package torch

class EventType {
	
	String name
	
    static constraints = {
		name(unique:true)
    }
	
	String toString(){
		name
	}
}
