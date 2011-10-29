package torch

class CommitteeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	static permissions = ["Committee.all", "Committee.create", "Committee.update", "Committee.delete"]
	
	def beforeInterceptor = [action:this.&clearPermissions]
	
	boolean canCreate(Member user){
		if(session?.user?.role == "admin"){
			return true
		}
		
		def userPermissions = SitePermissions.getPermissionsForUser(user)
		
		return(userPermissions?.contains("Committee.create"))
	}
	
	boolean canUpdate(Member user){
		if(session?.user?.role == "admin"){
			return true
		}
		
		def userPermissions = SitePermissions.getPermissionsForUser(user)
		
		return(userPermissions?.contains("Committee.update"))
	}
	
	boolean canDelete(Member user){
		if(session?.user?.role == "admin"){
			return true
		}
		
		def userPermissions = SitePermissions.getPermissionsForUser(user)
		
		return(userPermissions?.contains("Committee.delete"))
	}
	
	def clearPermissions(){
		def perms = [:]
		session?.CommitteePermission=perms
	}
	
    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
		if(canCreate(session?.user)){
			session?.CommitteePermission?.canCreateNew = true
		}
		
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [committeeInstanceList: Committee.list(params), committeeInstanceTotal: Committee.count()]
    }

    def create = {
		//permission check
		if( !(canCreate(session?.user))){
			flash.message = "You don't have permission to do that."
			redirect(uri:"/")
			return false
		}
		
        def committeeInstance = new Committee()
        committeeInstance.properties = params
        return [committeeInstance: committeeInstance]
    }

    def save = {
		//permission check
		if( !(canCreate(session?.user))){
			flash.message = "You don't have permission to do that."
			redirect(uri:"/")
			return false
		}
		
        def committeeInstance = new Committee(params)
        if (committeeInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'committee.label', default: 'Committee'), committeeInstance.id])}"
            redirect(action: "show", id: committeeInstance.id)
        }
        else {
            render(view: "create", model: [committeeInstance: committeeInstance])
        }
    }

    def show = {
        def committeeInstance = Committee.get(params.id)
        if (!committeeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'committee.label', default: 'Committee'), params.id])}"
            redirect(action: "list")
        }
        else {
			//set permissions
			if(canUpdate(session?.user)){
				session?.CommitteePermission?.canEdit = true
			}
			if(canDelete(session?.user)){
				session?.CommitteePermission?.canDelete = true
			}
			
			if(canCreate(session?.user)){
				session?.CommitteePermission?.canCreateNew = true
			}
			
            [committeeInstance: committeeInstance]
        }
    }

    def edit = {
        def committeeInstance = Committee.get(params.id)
        if (!committeeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'committee.label', default: 'Committee'), params.id])}"
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
				session?.CommitteePermission?.canEdit = true
			}
			if(canDelete(session?.user)){
				session?.CommitteePermission?.canDelete = true
			}
			
			if(canCreate(session?.user)){
				session?.CommitteePermission?.canCreateNew = true
			}
            return [committeeInstance: committeeInstance]
        }
    }

    def update = {
        def committeeInstance = Committee.get(params.id)
        if (committeeInstance) {
			//permissions check
			if( !(canUpdate(session?.user))){
				flash.message = "You don't have permission to do that."
				redirect(uri:"/")
				return false
			}
			
            if (params.version) {
                def version = params.version.toLong()
                if (committeeInstance.version > version) {
                    
                    committeeInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'committee.label', default: 'Committee')] as Object[], "Another user has updated this Committee while you were editing")
                    render(view: "edit", model: [committeeInstance: committeeInstance])
                    return
                }
            }
            committeeInstance.properties = params
            if (!committeeInstance.hasErrors() && committeeInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'committee.label', default: 'Committee'), committeeInstance.id])}"
                redirect(action: "show", id: committeeInstance.id)
            }
            else {
                render(view: "edit", model: [committeeInstance: committeeInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'committee.label', default: 'Committee'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def committeeInstance = Committee.get(params.id)
        if (committeeInstance) {
			//permissions check
			if( !(canDelete(session?.user) )){
				flash.message = "You don't have permission to do that."
				redirect(uri:"/")
				return false
			}
			
            try {
                committeeInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'committee.label', default: 'Committee'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'committee.label', default: 'Committee'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'committee.label', default: 'Committee'), params.id])}"
            redirect(action: "list")
        }
    }
}
