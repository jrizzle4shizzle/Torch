package torch

class CertificationTypeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	static permissions = ["CertificationType.all", "CertificationType.create", "CertificationType.update", "CertificationType.delete"]
	
	def beforeInterceptor = [action:this.&clearPermissions]
	
	boolean canCreate(Member user){
		if(session?.user?.role == "admin"){
			return true
		}
		
		def userPermissions = SitePermissions.getPermissionsForUser(user)
		
		return(userPermissions?.contains("CertificationType.create"))
	}
	
	boolean canUpdate(Member user){
		if(session?.user?.role == "admin"){
			return true
		}
		
		def userPermissions = SitePermissions.getPermissionsForUser(user)
		
		return(userPermissions?.contains("CertificationType.update"))
	}
	
	boolean canDelete(Member user){
		if(session?.user?.role == "admin"){
			return true
		}
		
		def userPermissions = SitePermissions.getPermissionsForUser(user)
		
		return(userPermissions?.contains("CertificationType.delete"))
	}
	
	def clearPermissions(){
		def perms = [:]
		session?.CertificationTypePermission=perms
	}
	
    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
		if(canCreate(session?.user)){
			session?.CertificationTypePermission?.canCreateNew = true
		}
		
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [certificationTypeInstanceList: CertificationType.list(params), certificationTypeInstanceTotal: CertificationType.count()]
    }

    def create = {
		//permission check
		if( !(canCreate(session?.user))){
			flash.message = "You don't have permission to do that."
			redirect(uri:"/")
			return false
		}
		
        def certificationTypeInstance = new CertificationType()
        certificationTypeInstance.properties = params
        return [certificationTypeInstance: certificationTypeInstance]
    }

    def save = {
		//permission check
		if( !(canCreate(session?.user))){
			flash.message = "You don't have permission to do that."
			redirect(uri:"/")
			return false
		}
		
        def certificationTypeInstance = new CertificationType(params)
        if (certificationTypeInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'certificationType.label', default: 'CertificationType'), certificationTypeInstance.id])}"
            redirect(action: "show", id: certificationTypeInstance.id)
        }
        else {
            render(view: "create", model: [certificationTypeInstance: certificationTypeInstance])
        }
    }

    def show = {
        def certificationTypeInstance = CertificationType.get(params.id)
        if (!certificationTypeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'certificationType.label', default: 'CertificationType'), params.id])}"
            redirect(action: "list")
        }
        else {
			//set permissions
			if(canUpdate(session?.user)){
				session?.CertificationTypePermission?.canEdit = true
			}
			if(canDelete(session?.user)){
				session?.CertificationTypePermission?.canDelete = true
			}
			
			if(canCreate(session?.user)){
				session?.CertificationTypePermission?.canCreateNew = true
			}
			
            [certificationTypeInstance: certificationTypeInstance]
        }
    }

    def edit = {
        def certificationTypeInstance = CertificationType.get(params.id)
        if (!certificationTypeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'certificationType.label', default: 'CertificationType'), params.id])}"
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
				session?.CertificationTypePermission?.canEdit = true
			}
			if(canDelete(session?.user)){
				session?.CertificationTypePermission?.canDelete = true
			}
			
			if(canCreate(session?.user)){
				session?.CertificationTypePermission?.canCreateNew = true
			}
            return [certificationTypeInstance: certificationTypeInstance]
        }
    }

    def update = {
        def certificationTypeInstance = CertificationType.get(params.id)
        if (certificationTypeInstance) {
			//permissions check
			if( !(canUpdate(session?.user))){
				flash.message = "You don't have permission to do that."
				redirect(uri:"/")
				return false
			}
			
            if (params.version) {
                def version = params.version.toLong()
                if (certificationTypeInstance.version > version) {
                    
                    certificationTypeInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'certificationType.label', default: 'CertificationType')] as Object[], "Another user has updated this CertificationType while you were editing")
                    render(view: "edit", model: [certificationTypeInstance: certificationTypeInstance])
                    return
                }
            }
            certificationTypeInstance.properties = params
            if (!certificationTypeInstance.hasErrors() && certificationTypeInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'certificationType.label', default: 'CertificationType'), certificationTypeInstance.id])}"
                redirect(action: "show", id: certificationTypeInstance.id)
            }
            else {
                render(view: "edit", model: [certificationTypeInstance: certificationTypeInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'certificationType.label', default: 'CertificationType'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def certificationTypeInstance = CertificationType.get(params.id)
        if (certificationTypeInstance) {
			//permissions check
			if( !(canDelete(session?.user) )){
				flash.message = "You don't have permission to do that."
				redirect(uri:"/")
				return false
			}
			
            try {
                certificationTypeInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'certificationType.label', default: 'CertificationType'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'certificationType.label', default: 'CertificationType'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'certificationType.label', default: 'CertificationType'), params.id])}"
            redirect(action: "list")
        }
    }
}
