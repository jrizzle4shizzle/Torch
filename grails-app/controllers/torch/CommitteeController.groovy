package torch

class CommitteeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def beforeInterceptor = [action:this.&clearPermissions]
	
	def clearPermissions(){
		def perms = [:]
		session?.committeePermission=perms
	}
	
    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [committeeInstanceList: Committee.list(params), committeeInstanceTotal: Committee.count()]
    }

    def create = {
		
		//allows
		if(!session?.user?.role == 'admin'){
			flash.message = "You don't have permission to do that!"
			return false
		}
		
		
        def committeeInstance = new Committee()
        committeeInstance.properties = params
        return [committeeInstance: committeeInstance]
    }

    def save = {
		
		//allows
		if(!session?.user?.role == 'admin'){
			flash.message = "You don't have permission to do that!"
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
            return [committeeInstance: committeeInstance]
        }
    }

    def update = {
        def committeeInstance = Committee.get(params.id)
        if (committeeInstance) {
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
