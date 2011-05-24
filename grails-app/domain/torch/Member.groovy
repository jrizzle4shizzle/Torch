package torch

class Member {
	
	static hasMany = [committees: Committee]
	
	String login
	String password
	String firstName
	String lastName
	Boolean active
	String homeAddressStreet
	String homeAddressCity
	String homeAddressState
	String homeAddressZip
	String homePhoneNumber
	String mobilePhoneNumber
	String notes
	Rank rank
	MembershipType membershipType
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
		login(unique:true)
		password(password:true)
		lastName()
		firstName()
		homeAddressStreet()
		homeAddressCity()
		homeAddressState()
		homeAddressZip()
		homePhoneNumber()
		mobilePhoneNumber()
		notes()
		rank()
		membershipType()
		committees()
		birthDay()
		aacoRidingDatabase()
		badgeNumber()
		dues()
		lifeInsurance()
		ssn()
		sex()
		race()
		driversLicenseNumber()
		driversLicenseClass()
		driversLicenseState()
		driversLicenseExpiration()
		active()
		administrative()
		membershipDate()
    }
	
	String toString(){
		lastName + ", "+firstName
	}
}
