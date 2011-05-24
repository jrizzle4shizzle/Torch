package torch

class MemberController {

    def scaffold = Member
	
	def login = {}
	
	def authenticate = {
		def user = Member.findByLoginAndPassword(params.login, params.password)
		if(user){
		  session.user = user
		  flash.message = "Hello ${user.firstName} ${user.lastName}!"
		  redirect(controller:"member", action:"list")
		}else{
		  flash.message = "Sorry, ${params.login}. Please try again."
		  redirect(action:"login")
		}
	  }
	  
	  def logout = {
		flash.message = "Goodbye ${session.user.firstName}"
		session.user = null
		redirect(controller:"entry", action:"list")
	  }
}
