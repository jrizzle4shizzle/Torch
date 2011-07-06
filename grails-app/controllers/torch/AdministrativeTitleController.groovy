package torch

class AdministrativeTitleController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	static permissions = ["administrativeTitle.all"]
	
	def beforeInterceptor = [action:this.&clearPermissions]
	
	def clearPermissions(){
		def perms = [:]
		session?.adminTitlePermission=perms
	}
	
    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
		//permissions:
		if (session?.user?.role == "admin"){
			session?.adminTitlePermission?.canCreateNew = true
		}
		
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [administrativeTitleInstanceList: AdministrativeTitle.list(params), administrativeTitleInstanceTotal: AdministrativeTitle.count()]
    }

    def create = {
		//permission check
		if( !(session?.user?.role == "admin") ){
			flash.message = "You don't have permission to do that."
			redirect(uri:"/")
			return false
		}
		
        def administrativeTitleInstance = new AdministrativeTitle()
        administrativeTitleInstance.properties = params
        return [administrativeTitleInstance: administrativeTitleInstance]
    }

    def save = {
		//permission check
		if( !(session?.user?.role == "admin") ){
			flash.message = "You don't have permission to do that."
			redirect(uri:"/")
			return false
		}
		
        def administrativeTitleInstance = new AdministrativeTitle(params)
        if (administrativeTitleInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'administrativeTitle.label', default: 'AdministrativeTitle'), administrativeTitleInstance.id])}"
            redirect(action: "show", id: administrativeTitleInstance.id)
        }
        else {
            render(view: "create", model: [administrativeTitleInstance: administrativeTitleInstance])
        }
    }

    def show = {
        def administrativeTitleInstance = AdministrativeTitle.get(params.id)
        if (!administrativeTitleInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'administrativeTitle.label', default: 'AdministrativeTitle'), params.id])}"
            redirect(action: "list")
        }
        else {
			//permissions:
			if (session?.user?.role == "admin"){
				session?.adminTitlePermission?.canEdit = true
				session?.adminTitlePermission?.canDelete = true
				session?.adminTitlePermission?.canCreateNew = true
			}
			
            [administrativeTitleInstance: administrativeTitleInstance]
        }
    }

    def edit = {
        def administrativeTitleInstance = AdministrativeTitle.get(params.id)
        if (!administrativeTitleInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'administrativeTitle.label', default: 'AdministrativeTitle'), params.id])}"
            redirect(action: "list")
        }
        else {
			//permission check
			if( !(session?.user?.role == "admin") ){
				flash.message = "You don't have permission to do that."
				redirect(uri:"/")
				return false
			}
			
			//permissions:
			if (session?.user?.role == "admin"){
				session?.adminTitlePermission?.canEdit = true
				session?.adminTitlePermission?.canDelete = true
				session?.adminTitlePermission?.canCreateNew = true
			}
			
            return [administrativeTitleInstance: administrativeTitleInstance]
        }
    }

    def update = {
        def administrativeTitleInstance = AdministrativeTitle.get(params.id)
        if (administrativeTitleInstance) {
			//permission check
			if( !(session?.user?.role == "admin") ){
				flash.message = "You don't have permission to do that."
				redirect(uri:"/")
				return false
			}
			
            if (params.version) {
                def version = params.version.toLong()
                if (administrativeTitleInstance.version > version) {
                    
                    administrativeTitleInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'administrativeTitle.label', default: 'AdministrativeTitle')] as Object[], "Another user has updated this AdministrativeTitle while you were editing")
                    render(view: "edit", model: [administrativeTitleInstance: administrativeTitleInstance])
                    return
                }
            }
            administrativeTitleInstance.properties = params
            if (!administrativeTitleInstance.hasErrors() && administrativeTitleInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'administrativeTitle.label', default: 'AdministrativeTitle'), administrativeTitleInstance.id])}"
                redirect(action: "show", id: administrativeTitleInstance.id)
            }
            else {
                render(view: "edit", model: [administrativeTitleInstance: administrativeTitleInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'administrativeTitle.label', default: 'AdministrativeTitle'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def administrativeTitleInstance = AdministrativeTitle.get(params.id)
        if (administrativeTitleInstance) {
			//permission check
			if( !(session?.user?.role == "admin") ){
				flash.message = "You don't have permission to do that."
				redirect(uri:"/")
				return false
			}
			
            try {
                administrativeTitleInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'administrativeTitle.label', default: 'AdministrativeTitle'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'administrativeTitle.label', default: 'AdministrativeTitle'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'administrativeTitle.label', default: 'AdministrativeTitle'), params.id])}"
            redirect(action: "list")
        }
    }
}
