package torch

class Event {
	static hasMany = [attendees:Member]
	
	EventType type
	String name
	Date date
	Member pointOfContact
	
    static constraints = {
		name(unique:true)
		date()
		attendees()
		pointOfContact()
		type()
    }
	
	
}
