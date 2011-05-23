package torch

class Member {
	
	String firstName
	String lastName
	String userName
	
	
	String homeAddressStreet
	String homeAddressCity
	String homeAddressState
	String homeAddressZip
	String homePhoneNumber
	String mobinePhoneNumber
	String notes
	Rank rank
	MembershipType membershipType
	List<Committee> committees
	String birthDay
	Boolean aacoRidingDatabase
	Integer badgeNumber
	Boolean dues
	Boolean lifeInsurance
	String ssn
	String sex
	String race
	String driversLicenseNumber
	String driversLicenseClass
	String driversLicenseState
	Date driversLicenseExpiration
	Boolean administrative
	Date membershipDate
	
	
    static constraints = {
    }
}
