package torch

class SecurityFilters {

    def filters = {
        all(controller:'*', action:'*') {
            before = {
				if(!session.user && !(actionName.equals('login') || actionName.equals('authenticate'))){
					redirect(controller:"member", action:"login")
					return false
				  }
            }
            
        }
    }
}
