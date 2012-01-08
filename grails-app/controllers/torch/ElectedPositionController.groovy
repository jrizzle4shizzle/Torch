package torch

class ElectedPositionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	static permissions = ["Position.all", "Position.create", "Position.update", "Position.delete"]
	
	def beforeInterceptor = [action:this.&clearPermissions]
	
	boolean canCreate(Member user){
		if(session?.user?.role == "admin"){
			return true
		}
		
		def userPermissions = SitePermissions.getPermissionsForUser(user)
		
		return(userPermissions?.contains("Position.create"))
	}
	
	boolean canUpdate(Member user){
		if(session?.user?.role == "admin"){
			return true
		}
		
		def userPermissions = SitePermissions.getPermissionsForUser(user)
		
		return(userPermissions?.contains("Position.update"))
	}
	
	boolean canDelete(Member user){
		if(session?.user?.role == "admin"){
			return true
		}
		
		def userPermissions = SitePermissions.getPermissionsForUser(user)
		
		return(userPermissions?.contains("Position.delete"))
	}
	
	def clearPermissions(){
		def perms = [:]
		session?.PositionPermission=perms
	}
	
    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
		if(canCreate(session?.user)){
			session?.PositionPermission?.canCreateNew = true
		}
		
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [positionInstanceList: ElectedPosition.list(params), positionInstanceTotal: ElectedPosition.count()]
    }

    def create = {
		//permission check
		if( !(canCreate(session?.user))){
			flash.message = "You don't have permission to do that."
			redirect(uri:"/")
			return false
		}
		
        def positionInstance = new ElectedPosition()
        positionInstance.properties = params
        return [positionInstance: positionInstance]
    }

    def save = {
		//permission check
		if( !(canCreate(session?.user))){
			flash.message = "You don't have permission to do that."
			redirect(uri:"/")
			return false
		}
		
        def positionInstance = new ElectedPosition(params)
        if (positionInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'position.label', default: 'Position'), positionInstance.id])}"
            redirect(action: "show", id: positionInstance.id)
        }
        else {
            render(view: "create", model: [positionInstance: positionInstance])
        }
    }

    def show = {
        def positionInstance = ElectedPosition.get(params.id)
        if (!positionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'position.label', default: 'Position'), params.id])}"
            redirect(action: "list")
        }
        else {
			//set permissions
			if(canUpdate(session?.user)){
				session?.PositionPermission?.canEdit = true
			}
			if(canDelete(session?.user)){
				session?.PositionPermission?.canDelete = true
			}
			
			if(canCreate(session?.user)){
				session?.PositionPermission?.canCreateNew = true
			}
			
            [positionInstance: positionInstance]
        }
    }

    def edit = {
        def positionInstance = ElectedPosition.get(params.id)
        if (!positionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'position.label', default: 'Position'), params.id])}"
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
				session?.PositionPermission?.canEdit = true
			}
			if(canDelete(session?.user)){
				session?.PositionPermission?.canDelete = true
			}
			
			if(canCreate(session?.user)){
				session?.PositionPermission?.canCreateNew = true
			}
            return [positionInstance: positionInstance]
        }
    }

    def update = {
        def positionInstance = ElectedPosition.get(params.id)
        if (positionInstance) {
			//permissions check
			if( !(canUpdate(session?.user))){
				flash.message = "You don't have permission to do that."
				redirect(uri:"/")
				return false
			}
			
            if (params.version) {
                def version = params.version.toLong()
                if (positionInstance.version > version) {
                    
                    positionInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'position.label', default: 'Position')] as Object[], "Another user has updated this Position while you were editing")
                    render(view: "edit", model: [positionInstance: positionInstance])
                    return
                }
            }
            positionInstance.properties = params
            if (!positionInstance.hasErrors() && positionInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'position.label', default: 'Position'), positionInstance.id])}"
                redirect(action: "show", id: positionInstance.id)
            }
            else {
                render(view: "edit", model: [positionInstance: positionInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'position.label', default: 'Position'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def positionInstance = ElectedPosition.get(params.id)
        if (positionInstance) {
			//permissions check
			if( !(canDelete(session?.user) )){
				flash.message = "You don't have permission to do that."
				redirect(uri:"/")
				return false
			}
			
            try {
                positionInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'position.label', default: 'Position'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'position.label', default: 'Position'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'position.label', default: 'Position'), params.id])}"
            redirect(action: "list")
        }
    }
}
