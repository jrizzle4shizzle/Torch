package torch

class ApparatusTypeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	static permissions = ["ApparatusType.all", "ApparatusType.create", "ApparatusType.update", "ApparatusType.delete"]
	
	def beforeInterceptor = [action:this.&clearPermissions]
	
	boolean canCreate(Member user){
		if(session?.user?.role == "admin"){
			return true
		}
		
		def userPermissions = SitePermissions.getPermissionsForUser(user)
		
		return(userPermissions?.contains("ApparatusType.create"))
	}
	
	boolean canUpdate(Member user){
		if(session?.user?.role == "admin"){
			return true
		}
		
		def userPermissions = SitePermissions.getPermissionsForUser(user)
		
		return(userPermissions?.contains("ApparatusType.update"))
	}
	
	boolean canDelete(Member user){
		if(session?.user?.role == "admin"){
			return true
		}
		
		def userPermissions = SitePermissions.getPermissionsForUser(user)
		
		return(userPermissions?.contains("ApparatusType.delete"))
	}
	
	def clearPermissions(){
		def perms = [:]
		session?.ApparatusTypePermission=perms
	}
	
    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
		if(canCreate(session?.user)){
			session?.ApparatusTypePermission?.canCreateNew = true
		}
		
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [apparatusTypeInstanceList: ApparatusType.list(params), apparatusTypeInstanceTotal: ApparatusType.count()]
    }

    def create = {
		//permission check
		if( !(canCreate(session?.user))){
			flash.message = "You don't have permission to do that."
			redirect(uri:"/")
			return false
		}
		
        def apparatusTypeInstance = new ApparatusType()
        apparatusTypeInstance.properties = params
        return [apparatusTypeInstance: apparatusTypeInstance]
    }

    def save = {
		//permission check
		if( !(canCreate(session?.user))){
			flash.message = "You don't have permission to do that."
			redirect(uri:"/")
			return false
		}
		
        def apparatusTypeInstance = new ApparatusType(params)
        if (apparatusTypeInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'apparatusType.label', default: 'ApparatusType'), apparatusTypeInstance.id])}"
            redirect(action: "show", id: apparatusTypeInstance.id)
        }
        else {
            render(view: "create", model: [apparatusTypeInstance: apparatusTypeInstance])
        }
    }

    def show = {
        def apparatusTypeInstance = ApparatusType.get(params.id)
        if (!apparatusTypeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'apparatusType.label', default: 'ApparatusType'), params.id])}"
            redirect(action: "list")
        }
        else {
			//set permissions
			if(canUpdate(session?.user)){
				session?.ApparatusTypePermission?.canEdit = true
			}
			if(canDelete(session?.user)){
				session?.ApparatusTypePermission?.canDelete = true
			}
			
			if(canCreate(session?.user)){
				session?.ApparatusTypePermission?.canCreateNew = true
			}
			
            [apparatusTypeInstance: apparatusTypeInstance]
        }
    }

    def edit = {
        def apparatusTypeInstance = ApparatusType.get(params.id)
        if (!apparatusTypeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'apparatusType.label', default: 'ApparatusType'), params.id])}"
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
				session?.ApparatusTypePermission?.canEdit = true
			}
			if(canDelete(session?.user)){
				session?.ApparatusTypePermission?.canDelete = true
			}
			
			if(canCreate(session?.user)){
				session?.ApparatusTypePermission?.canCreateNew = true
			}
            return [apparatusTypeInstance: apparatusTypeInstance]
        }
    }

    def update = {
        def apparatusTypeInstance = ApparatusType.get(params.id)
        if (apparatusTypeInstance) {
			//permissions check
			if( !(canUpdate(session?.user))){
				flash.message = "You don't have permission to do that."
				redirect(uri:"/")
				return false
			}
			
            if (params.version) {
                def version = params.version.toLong()
                if (apparatusTypeInstance.version > version) {
                    
                    apparatusTypeInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'apparatusType.label', default: 'ApparatusType')] as Object[], "Another user has updated this ApparatusType while you were editing")
                    render(view: "edit", model: [apparatusTypeInstance: apparatusTypeInstance])
                    return
                }
            }
            apparatusTypeInstance.properties = params
            if (!apparatusTypeInstance.hasErrors() && apparatusTypeInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'apparatusType.label', default: 'ApparatusType'), apparatusTypeInstance.id])}"
                redirect(action: "show", id: apparatusTypeInstance.id)
            }
            else {
                render(view: "edit", model: [apparatusTypeInstance: apparatusTypeInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'apparatusType.label', default: 'ApparatusType'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def apparatusTypeInstance = ApparatusType.get(params.id)
        if (apparatusTypeInstance) {
			//permissions check
			if( !(canDelete(session?.user) )){
				flash.message = "You don't have permission to do that."
				redirect(uri:"/")
				return false
			}
			
            try {
                apparatusTypeInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'apparatusType.label', default: 'ApparatusType'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'apparatusType.label', default: 'ApparatusType'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'apparatusType.label', default: 'ApparatusType'), params.id])}"
            redirect(action: "list")
        }
    }
}
