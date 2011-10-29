package torch

class Certification {
	
	
	Member member
	
	CertificationType type
	
	Date expires
	
	Date issued
	
	Boolean onFile
		
    static constraints = {
		member()
		type()
		expires()
		issued()
		onFile()
    }
	
	String toString(){
		member + " : "+ type.toString()
	}
}
