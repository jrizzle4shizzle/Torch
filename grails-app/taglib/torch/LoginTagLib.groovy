package torch

class LoginTagLib {
	def loginControl = {
		if(session.user){
		  out << "Hello ${session.user.firstName} ${session.user.lastName} "
		  out << """[${link(action:"logout", controller:"member"){"Logout"}}]"""
		} else {
		  out << """[${link(action:"login", controller:"member"){"Login"}}]"""
		}
	  }
}
