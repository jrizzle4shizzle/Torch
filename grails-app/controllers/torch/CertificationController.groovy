package torch

class CertificationController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	static permissions = ["Certification.all", "Certification.create", "Certification.update", "Certification.delete", "Certification.updateFile"]
	
	def beforeInterceptor = [action:this.&clearPermissions]
	
	boolean canCreate(Member user){
		if(session?.user?.role == "admin"){
			return true
		}
		
		def userPermissions = SitePermissions.getPermissionsForUser(user)
		
		return(userPermissions?.contains("Certification.create"))
	}
	
	boolean canUpdate(Member user){
		if(session?.user?.role == "admin"){
			return true
		}
		
		def userPermissions = SitePermissions.getPermissionsForUser(user)
		
		return(userPermissions?.contains("Certification.update"))
	}
	
	boolean canDelete(Member user){
		if(session?.user?.role == "admin"){
			return true
		}
		
		def userPermissions = SitePermissions.getPermissionsForUser(user)
		
		return(userPermissions?.contains("Certification.delete"))
	}
	
	def clearPermissions(){
		def perms = [:]
		session?.CertificationPermission=perms
	}
	
    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
		if(canCreate(session?.user)){
			session?.CertificationPermission?.canCreateNew = true
		}
		
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [certificationInstanceList: Certification.list(params), certificationInstanceTotal: Certification.count()]
    }

    def create = {
		//permission check
		if( !(canCreate(session?.user))){
			flash.message = "You don't have permission to do that."
			redirect(uri:"/")
			return false
		}
		
        def certificationInstance = new Certification()
        certificationInstance.properties = params
        return [certificationInstance: certificationInstance]
    }

    def save = {
		//permission check
		if( !(canCreate(session?.user))){
			flash.message = "You don't have permission to do that."
			redirect(uri:"/")
			return false
		}
		
        def certificationInstance = new Certification(params)
        if (certificationInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'certification.label', default: 'Certification'), certificationInstance.id])}"
            redirect(action: "show", id: certificationInstance.id)
        }
        else {
            render(view: "create", model: [certificationInstance: certificationInstance])
        }
    }

    def show = {
        def certificationInstance = Certification.get(params.id)
        if (!certificationInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'certification.label', default: 'Certification'), params.id])}"
            redirect(action: "list")
        }
        else {
			//set permissions
			if(canUpdate(session?.user)){
				session?.CertificationPermission?.canEdit = true
			}
			if(canDelete(session?.user)){
				session?.CertificationPermission?.canDelete = true
			}
			
			if(canCreate(session?.user)){
				session?.CertificationPermission?.canCreateNew = true
			}
			
			if(canUpdateFile(session?.user)){
				session?.CertificationPermission?.canUpdateFile = true
			}
			
            [certificationInstance: certificationInstance]
        }
    }

    def edit = {
        def certificationInstance = Certification.get(params.id)
        if (!certificationInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'certification.label', default: 'Certification'), params.id])}"
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
				session?.CertificationPermission?.canEdit = true
			}
			if(canDelete(session?.user)){
				session?.CertificationPermission?.canDelete = true
			}
			
			if(canCreate(session?.user)){
				session?.CertificationPermission?.canCreateNew = true
			}
			
			if(canUpdateFile(session?.user)){
				session?.CertificationPermission?.canUpdateFile = true
			}
			
            return [certificationInstance: certificationInstance]
        }
    }

    def update = {
        def certificationInstance = Certification.get(params.id)
        if (certificationInstance) {
			//permissions check
			if( !(canUpdate(session?.user))){
				flash.message = "You don't have permission to do that."
				redirect(uri:"/")
				return false
			}
			
            if (params.version) {
                def version = params.version.toLong()
                if (certificationInstance.version > version) {
                    
                    certificationInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'certification.label', default: 'Certification')] as Object[], "Another user has updated this Certification while you were editing")
                    render(view: "edit", model: [certificationInstance: certificationInstance])
                    return
                }
            }
            certificationInstance.properties = params
            if (!certificationInstance.hasErrors() && certificationInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'certification.label', default: 'Certification'), certificationInstance.id])}"
                redirect(action: "show", id: certificationInstance.id)
            }
            else {
                render(view: "edit", model: [certificationInstance: certificationInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'certification.label', default: 'Certification'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def certificationInstance = Certification.get(params.id)
        if (certificationInstance) {
			//permissions check
			if( !(canDelete(session?.user) )){
				flash.message = "You don't have permission to do that."
				redirect(uri:"/")
				return false
			}
			
            try {
                certificationInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'certification.label', default: 'Certification'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'certification.label', default: 'Certification'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'certification.label', default: 'Certification'), params.id])}"
            redirect(action: "list")
        }
    }
}
