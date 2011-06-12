package torch

class Event {

	static hasMany = [rsvp:Member, attended:Member]
	String name
	Date date
	
	
    static constraints = {
		name(unique:true)
		date
		rsvp
		attended
    }
}
