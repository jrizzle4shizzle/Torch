package torch

class EventController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def beforeInterceptor = [action:this.&clearPermissions]
	
	def clearPermissions(){
		def perms = [:]
		session?.eventPermission=perms
	}
	
    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
		//permissions:
		if (session?.user?.role == "admin"){
			session?.eventPermission?.canCreateNew = true
		}
		
		
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [eventInstanceList: Event.list(params), eventInstanceTotal: Event.count()]
    }

    def create = {
		//permission check
		if( !(session?.user?.role == "admin") ){
			flash.message = "You don't have permission to do that."
			redirect(uri:"/")
			return false
		}
		
        def eventInstance = new Event()
        eventInstance.properties = params
        return [eventInstance: eventInstance]
    }

    def save = {
		//permission check
		if( !(session?.user?.role == "admin") ){
			flash.message = "You don't have permission to do that."
			redirect(uri:"/")
			return false
		}
		
        def eventInstance = new Event(params)
        if (eventInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'event.label', default: 'Event'), eventInstance.id])}"
            redirect(action: "show", id: eventInstance.id)
        }
        else {
            render(view: "create", model: [eventInstance: eventInstance])
        }
    }

    def show = {
        def eventInstance = Event.get(params.id)
        if (!eventInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
            redirect(action: "list")
        }
        else {
			//set permissions
			if(session?.user?.role == 'admin'){
				session?.eventPermission?.canEdit = true
				session?.eventPermission?.canDelete = true
				session?.eventPermission?.canCreateNew = true
			}
            [eventInstance: eventInstance]
        }
    }

    def edit = {
        def eventInstance = Event.get(params.id)
        if (!eventInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
            redirect(action: "list")
        }
        else {
			//permission check
			if( !(session?.user?.role == "admin") ){
				flash.message = "You don't have permission to do that."
				redirect(uri:"/")
				return false
			}
			
			//set addl permissions
			if(session?.user?.role == 'admin'){
				session?.eventPermission?.canEdit = true
				session?.eventPermission?.canDelete = true
				session?.eventPermission?.canCreateNew = true
			}
			
            return [eventInstance: eventInstance]
        }
    }

    def update = {
        def eventInstance = Event.get(params.id)
        if (eventInstance) {
			//permissions check
			if( !(session?.user?.role == "admin") ){
				flash.message = "You don't have permission to do that."
				redirect(uri:"/")
				return false
			}
			
            if (params.version) {
                def version = params.version.toLong()
                if (eventInstance.version > version) {
                    
                    eventInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'event.label', default: 'Event')] as Object[], "Another user has updated this Event while you were editing")
                    render(view: "edit", model: [eventInstance: eventInstance])
                    return
                }
            }
            eventInstance.properties = params
            if (!eventInstance.hasErrors() && eventInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'event.label', default: 'Event'), eventInstance.id])}"
                redirect(action: "show", id: eventInstance.id)
            }
            else {
                render(view: "edit", model: [eventInstance: eventInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
		//permissions check
		if( !(session?.user?.role == "admin") ){
			flash.message = "You don't have permission to do that."
			redirect(uri:"/")
			return false
		}
		
        def eventInstance = Event.get(params.id)
        if (eventInstance) {
            try {
                eventInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
            redirect(action: "list")
        }
    }
}
