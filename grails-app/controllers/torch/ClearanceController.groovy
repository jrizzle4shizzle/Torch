package torch

class ClearanceController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	static permissions = ["Clearance.all", "Clearance.create", "Clearance.update", "Clearance.delete"]
	
	def beforeInterceptor = [action:this.&clearPermissions]
	
	boolean canCreate(Member user){
		if(session?.user?.role == "admin"){
			return true
		}
		
		def userPermissions = SitePermissions.getPermissionsForUser(user)
		
		return(userPermissions?.contains("Clearance.create"))
	}
	
	boolean canUpdate(Member user){
		if(session?.user?.role == "admin"){
			return true
		}
		
		def userPermissions = SitePermissions.getPermissionsForUser(user)
		
		return(userPermissions?.contains("Clearance.update"))
	}
	
	boolean canDelete(Member user){
		if(session?.user?.role == "admin"){
			return true
		}
		
		def userPermissions = SitePermissions.getPermissionsForUser(user)
		
		return(userPermissions?.contains("Clearance.delete"))
	}
	
	def clearPermissions(){
		def perms = [:]
		session?.ClearancePermission=perms
	}
	
    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
		if(canCreate(session?.user)){
			session?.ClearancePermission?.canCreateNew = true
		}
		
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [clearanceInstanceList: Clearance.list(params), clearanceInstanceTotal: Clearance.count()]
    }

    def create = {
		//permission check
		if( !(canCreate(session?.user))){
			flash.message = "You don't have permission to do that."
			redirect(uri:"/")
			return false
		}
		
        def clearanceInstance = new Clearance()
        clearanceInstance.properties = params
        return [clearanceInstance: clearanceInstance]
    }

    def save = {
		//permission check
		if( !(canCreate(session?.user))){
			flash.message = "You don't have permission to do that."
			redirect(uri:"/")
			return false
		}
		
        def clearanceInstance = new Clearance(params)
        if (clearanceInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'clearance.label', default: 'Clearance'), clearanceInstance.id])}"
            redirect(action: "show", id: clearanceInstance.id)
        }
        else {
            render(view: "create", model: [clearanceInstance: clearanceInstance])
        }
    }

    def show = {
        def clearanceInstance = Clearance.get(params.id)
        if (!clearanceInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'clearance.label', default: 'Clearance'), params.id])}"
            redirect(action: "list")
        }
        else {
			//set permissions
			if(canUpdate(session?.user)){
				session?.ClearancePermission?.canEdit = true
			}
			if(canDelete(session?.user)){
				session?.ClearancePermission?.canDelete = true
			}
			
			if(canCreate(session?.user)){
				session?.ClearancePermission?.canCreateNew = true
			}
			
            [clearanceInstance: clearanceInstance]
        }
    }

    def edit = {
        def clearanceInstance = Clearance.get(params.id)
        if (!clearanceInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'clearance.label', default: 'Clearance'), params.id])}"
            redirect(action: "list")
        }
        else {
			//permission check
			if( !(session?.user?.role == "admin") ){
				flash.message = "You don't have permission to do that."
				redirect(uri:"/")
				return false
			}
			
			//set permissions
			if(canUpdate(session?.user)){
				session?.ClearancePermission?.canEdit = true
			}
			if(canDelete(session?.user)){
				session?.ClearancePermission?.canDelete = true
			}
			
			if(canCreate(session?.user)){
				session?.ClearancePermission?.canCreateNew = true
			}
            return [clearanceInstance: clearanceInstance]
        }
    }

    def update = {
        def clearanceInstance = Clearance.get(params.id)
        if (clearanceInstance) {
			//permissions check
			if( !(canUpdate(session?.user))){
				flash.message = "You don't have permission to do that."
				redirect(uri:"/")
				return false
			}
			
            if (params.version) {
                def version = params.version.toLong()
                if (clearanceInstance.version > version) {
                    
                    clearanceInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'clearance.label', default: 'Clearance')] as Object[], "Another user has updated this Clearance while you were editing")
                    render(view: "edit", model: [clearanceInstance: clearanceInstance])
                    return
                }
            }
            clearanceInstance.properties = params
            if (!clearanceInstance.hasErrors() && clearanceInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'clearance.label', default: 'Clearance'), clearanceInstance.id])}"
                redirect(action: "show", id: clearanceInstance.id)
            }
            else {
                render(view: "edit", model: [clearanceInstance: clearanceInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'clearance.label', default: 'Clearance'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def clearanceInstance = Clearance.get(params.id)
        if (clearanceInstance) {
			//permissions check
			if( !(canDelete(session?.user) )){
				flash.message = "You don't have permission to do that."
				redirect(uri:"/")
				return false
			}
			
            try {
                clearanceInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'clearance.label', default: 'Clearance'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'clearance.label', default: 'Clearance'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'clearance.label', default: 'Clearance'), params.id])}"
            redirect(action: "list")
        }
    }
}
