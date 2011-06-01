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
	String role = "member"
	
	
    static constraints = {
		login(unique:true)
		password(password:true)
		lastName(nullable:true)
		firstName(nullable:true)
		homeAddressStreet(nullable:true)
		homeAddressCity(nullable:true)
		homeAddressState(nullable:true)
		homeAddressZip(nullable:true)
		homePhoneNumber(nullable:true)
		mobilePhoneNumber(nullable:true)
		notes(nullable:true)
		rank(nullable:true)
		membershipType(nullable:true)
		committees(nullable:true)
		birthDay(nullable:true)
		aacoRidingDatabase(nullable:true)
		badgeNumber(nullable:true)
		dues(nullable:true)
		lifeInsurance(nullable:true)
		ssn(nullable:true)
		sex(nullable:true)
		race(nullable:true)
		driversLicenseNumber(nullable:true)
		driversLicenseClass(nullable:true)
		driversLicenseState(nullable:true)
		driversLicenseExpiration(nullable:true)
		active(nullable:true)
		administrative(nullable:true)
		membershipDate(nullable:true)
		role(inList:["member", "admin"])
    }
	
	String toString(){
		lastName + ", "+firstName
	}
}
