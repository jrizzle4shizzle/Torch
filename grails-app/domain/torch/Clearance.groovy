package torch

class Clearance {

    static hasMany = [sitePermissions:String]
	
	ApparatusType type
	Boolean ride
	Boolean drive
	Boolean training
	Boolean driverTraining
	Member member
	
    static constraints = {
		member()
		type()
		training()
		ride()
		driverTraining()
		training()
		sitePermissions()
    }
	
	String toString(){
		name
	}
}
