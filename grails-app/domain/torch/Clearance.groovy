package torch

class Clearance {

	
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
    }
	
	String toString(){
		name
	}
}
