package torch

import java.util.List;


class EventController{

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	static permissions = ["event.all-all", 
		"event.meeting-all", "event.meeting-create", "event.meeting-delete", "event.meeting-update",
		"event.drill-all", "event.drill-create", "event.drill-delete", "event.drill-update",
		"event.fundraiser-all", "event.fundraiser-create", "event.fundraiser-delete", "event.drill-update"
		];
	
	
	def beforeInterceptor = [action:this.&clearPermissions]
	
	boolean canCreate(Member user){
		if(session?.user?.role == "admin"){
			return true
		}
		
		def userPermissions = SitePermissions.getPermissionsForUser(user)
		
		return (userPermissions?.contains("event.all-all") ||
			userPermissions?.contains("event.meeting-all") || userPermissions?.contains("event.meeting-create") ||
			userPermissions?.contains("event.drill-all") || userPermissions?.contains("event.drill-create") ||
			userPermissions?.contains("event.fundraiser-all") || userPermissions?.contains("event.fundraiser-create"))
	}
	
	boolean canUpdate(Member user, String type){
		if(session?.user?.role == "admin"){
			return true
		}
		
		def userPermissions = SitePermissions.getPermissionsForUser(user)
		
		if(userPermissions.contains("event.all-all")){
			return true;
		}
		
		switch(type){
			case "meeting":
				return (userPermissions.contains("event.meeting-all") || userPermissions.contains("event.meeting-update"))
				break;
			case "drill":
				return (userPermissions.contains("event.drill-all") || userPermissions.contains("event.drill-update"))
				break;
			case "fundraiser":
				return (userPermissions.contains("event.fundraiser-all") || userPermissions.contains("event.fundraiser-update"))
				break;
		}
		return false;
	}
	
	boolean canDelete(Member user, String type){
		if(session?.user?.role == "admin"){
			return true
		}
		
		def userPermissions = SitePermissions.getPermissionsForUser(user)
		
		if(userPermissions.contains("event.all-all")){
			return true;
		}
		
		switch(type){
			case "meeting":
				return (userPermissions.contains("event.meeting-all") || userPermissions.contains("event.meeting-delete"))
				break;
			case "drill":
				return (userPermissions.contains("event.drill-all") || userPermissions.contains("event.drill-delete"))
				break;
			case "fundraiser":
				return (userPermissions.contains("event.fundraiser-all") || userPermissions.contains("event.fundraiser-delete"))
				break;
		}
		return false;
	}
	
	def clearPermissions(){
		def perms = [:]
		session?.eventPermission=perms
	}
	
    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
		def user = session.user
		
		//permissions:
		if (canCreate(user)){
			session?.eventPermission?.canCreateNew = true
		}
		
		
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [eventInstanceList: Event.list(params), eventInstanceTotal: Event.count()]
    }

    def create = {
		//permission check
		if( !(canCreate(session?.user))){
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
		if( !(canCreate(session?.user))){
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
			if(canUpdate(session?.user, eventInstance.type)){
				session?.eventPermission?.canEdit = true
			}
			if(canDelete(session?.user, eventInstance.type)){
				session?.eventPermission?.canDelete = true
			}
			
			if(canCreate(session?.user)){
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
			
			//set permissions
			if(canUpdate(session?.user, eventInstance.type)){
				session?.eventPermission?.canEdit = true
			}
			if(canDelete(session?.user, eventInstance.type)){
				session?.eventPermission?.canDelete = true
			}
			
			if(canCreate(session?.user)){
				session?.eventPermission?.canCreateNew = true
			}
			
            return [eventInstance: eventInstance]
        }
    }

	
    def update = {
        def eventInstance = Event.get(params.id)
        if (eventInstance) {
			//permissions check
			if( !(canUpdate(session?.user, eventInstance?.type))){
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
		def eventInstance = Event.get(params.id)
		
        if (eventInstance) {
			//permissions check
			if( !(canDelete(session?.user, eventInstance?.type) )){
				flash.message = "You don't have permission to do that."
				redirect(uri:"/")
				return false
			}
			
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
