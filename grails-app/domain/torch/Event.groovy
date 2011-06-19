package torch

class Event {
	static hasMany = [attendees:Member]
	
	String name
	Date date
	String type
	Member pointOfContact
	
    static constraints = {
		name(unique:true)
		date()
		attendees()
		pointOfContact()
		type(inList:["drill", "meeting"])
    }
	
	
}
