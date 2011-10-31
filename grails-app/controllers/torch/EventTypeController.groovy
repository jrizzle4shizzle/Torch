package torch

class EventTypeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	static permissions = ["EventType.all", "EventType.create", "EventType.update", "EventType.delete"]
	
	def beforeInterceptor = [action:this.&clearPermissions]
	
	boolean canCreate(Member user){
		if(session?.user?.role == "admin"){
			return true
		}
		
		def userPermissions = SitePermissions.getPermissionsForUser(user)
		
		return(userPermissions?.contains("EventType.create"))
	}
	
	boolean canUpdate(Member user){
		if(session?.user?.role == "admin"){
			return true
		}
		
		def userPermissions = SitePermissions.getPermissionsForUser(user)
		
		return(userPermissions?.contains("EventType.update"))
	}
	
	boolean canDelete(Member user){
		if(session?.user?.role == "admin"){
			return true
		}
		
		def userPermissions = SitePermissions.getPermissionsForUser(user)
		
		return(userPermissions?.contains("EventType.delete"))
	}
	
	def clearPermissions(){
		def perms = [:]
		session?.EventTypePermission=perms
	}
	
    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
		if(canCreate(session?.user)){
			session?.EventTypePermission?.canCreateNew = true
		}
		
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [eventTypeInstanceList: EventType.list(params), eventTypeInstanceTotal: EventType.count()]
    }

    def create = {
		//permission check
		if( !(canCreate(session?.user))){
			flash.message = "You don't have permission to do that."
			redirect(uri:"/")
			return false
		}
		
        def eventTypeInstance = new EventType()
        eventTypeInstance.properties = params
        return [eventTypeInstance: eventTypeInstance]
    }

    def save = {
		//permission check
		if( !(canCreate(session?.user))){
			flash.message = "You don't have permission to do that."
			redirect(uri:"/")
			return false
		}
		
        def eventTypeInstance = new EventType(params)
        if (eventTypeInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'eventType.label', default: 'EventType'), eventTypeInstance.id])}"
            redirect(action: "show", id: eventTypeInstance.id)
        }
        else {
            render(view: "create", model: [eventTypeInstance: eventTypeInstance])
        }
    }

    def show = {
        def eventTypeInstance = EventType.get(params.id)
        if (!eventTypeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'eventType.label', default: 'EventType'), params.id])}"
            redirect(action: "list")
        }
        else {
			//set permissions
			if(canUpdate(session?.user)){
				session?.EventTypePermission?.canEdit = true
			}
			if(canDelete(session?.user)){
				session?.EventTypePermission?.canDelete = true
			}
			
			if(canCreate(session?.user)){
				session?.EventTypePermission?.canCreateNew = true
			}
			
            [eventTypeInstance: eventTypeInstance]
        }
    }

    def edit = {
        def eventTypeInstance = EventType.get(params.id)
        if (!eventTypeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'eventType.label', default: 'EventType'), params.id])}"
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
				session?.EventTypePermission?.canEdit = true
			}
			if(canDelete(session?.user)){
				session?.EventTypePermission?.canDelete = true
			}
			
			if(canCreate(session?.user)){
				session?.EventTypePermission?.canCreateNew = true
			}
            return [eventTypeInstance: eventTypeInstance]
        }
    }

    def update = {
        def eventTypeInstance = EventType.get(params.id)
        if (eventTypeInstance) {
			//permissions check
			if( !(canUpdate(session?.user))){
				flash.message = "You don't have permission to do that."
				redirect(uri:"/")
				return false
			}
			
            if (params.version) {
                def version = params.version.toLong()
                if (eventTypeInstance.version > version) {
                    
                    eventTypeInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'eventType.label', default: 'EventType')] as Object[], "Another user has updated this EventType while you were editing")
                    render(view: "edit", model: [eventTypeInstance: eventTypeInstance])
                    return
                }
            }
            eventTypeInstance.properties = params
            if (!eventTypeInstance.hasErrors() && eventTypeInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'eventType.label', default: 'EventType'), eventTypeInstance.id])}"
                redirect(action: "show", id: eventTypeInstance.id)
            }
            else {
                render(view: "edit", model: [eventTypeInstance: eventTypeInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'eventType.label', default: 'EventType'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def eventTypeInstance = EventType.get(params.id)
        if (eventTypeInstance) {
			//permissions check
			if( !(canDelete(session?.user) )){
				flash.message = "You don't have permission to do that."
				redirect(uri:"/")
				return false
			}
			
            try {
                eventTypeInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'eventType.label', default: 'EventType'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'eventType.label', default: 'EventType'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'eventType.label', default: 'EventType'), params.id])}"
            redirect(action: "list")
        }
    }
}
