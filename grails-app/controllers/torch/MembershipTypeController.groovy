package torch

class MembershipTypeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	static permissions = ["MembershipType.all", "MembershipType.create", "MembershipType.update", "MembershipType.delete"]
	
	def beforeInterceptor = [action:this.&clearPermissions]
	
	boolean canCreate(Member user){
		if(session?.user?.role == "admin"){
			return true
		}
		
		def userPermissions = SitePermissions.getPermissionsForUser(user)
		
		return(userPermissions?.contains("MembershipType.create"))
	}
	
	boolean canUpdate(Member user){
		if(session?.user?.role == "admin"){
			return true
		}
		
		def userPermissions = SitePermissions.getPermissionsForUser(user)
		
		return(userPermissions?.contains("MembershipType.update"))
	}
	
	boolean canDelete(Member user){
		if(session?.user?.role == "admin"){
			return true
		}
		
		def userPermissions = SitePermissions.getPermissionsForUser(user)
		
		return(userPermissions?.contains("MembershipType.delete"))
	}
	
	def clearPermissions(){
		def perms = [:]
		session?.MembershipTypePermission=perms
	}
	
    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [membershipTypeInstanceList: MembershipType.list(params), membershipTypeInstanceTotal: MembershipType.count()]
    }

    def create = {
		//permission check
		if( !(canCreate(session?.user))){
			flash.message = "You don't have permission to do that."
			redirect(uri:"/")
			return false
		}
		
        def membershipTypeInstance = new MembershipType()
        membershipTypeInstance.properties = params
        return [membershipTypeInstance: membershipTypeInstance]
    }

    def save = {
		//permission check
		if( !(canCreate(session?.user))){
			flash.message = "You don't have permission to do that."
			redirect(uri:"/")
			return false
		}
		
        def membershipTypeInstance = new MembershipType(params)
        if (membershipTypeInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'membershipType.label', default: 'MembershipType'), membershipTypeInstance.id])}"
            redirect(action: "show", id: membershipTypeInstance.id)
        }
        else {
            render(view: "create", model: [membershipTypeInstance: membershipTypeInstance])
        }
    }

    def show = {
        def membershipTypeInstance = MembershipType.get(params.id)
        if (!membershipTypeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'membershipType.label', default: 'MembershipType'), params.id])}"
            redirect(action: "list")
        }
        else {
			//set permissions
			if(canUpdate(session?.user)){
				session?.MembershipTypePermission?.canEdit = true
			}
			if(canDelete(session?.user)){
				session?.MembershipTypePermission?.canDelete = true
			}
			
			if(canCreate(session?.user)){
				session?.MembershipTypePermission?.canCreateNew = true
			}
			
            [membershipTypeInstance: membershipTypeInstance]
        }
    }

    def edit = {
        def membershipTypeInstance = MembershipType.get(params.id)
        if (!membershipTypeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'membershipType.label', default: 'MembershipType'), params.id])}"
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
				session?.MembershipTypePermission?.canEdit = true
			}
			if(canDelete(session?.user)){
				session?.MembershipTypePermission?.canDelete = true
			}
			
			if(canCreate(session?.user)){
				session?.MembershipTypePermission?.canCreateNew = true
			}
            return [membershipTypeInstance: membershipTypeInstance]
        }
    }

    def update = {
        def membershipTypeInstance = MembershipType.get(params.id)
        if (membershipTypeInstance) {
			//permissions check
			if( !(canUpdate(session?.user))){
				flash.message = "You don't have permission to do that."
				redirect(uri:"/")
				return false
			}
			
            if (params.version) {
                def version = params.version.toLong()
                if (membershipTypeInstance.version > version) {
                    
                    membershipTypeInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'membershipType.label', default: 'MembershipType')] as Object[], "Another user has updated this MembershipType while you were editing")
                    render(view: "edit", model: [membershipTypeInstance: membershipTypeInstance])
                    return
                }
            }
            membershipTypeInstance.properties = params
            if (!membershipTypeInstance.hasErrors() && membershipTypeInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'membershipType.label', default: 'MembershipType'), membershipTypeInstance.id])}"
                redirect(action: "show", id: membershipTypeInstance.id)
            }
            else {
                render(view: "edit", model: [membershipTypeInstance: membershipTypeInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'membershipType.label', default: 'MembershipType'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def membershipTypeInstance = MembershipType.get(params.id)
        if (membershipTypeInstance) {
			//permissions check
			if( !(canDelete(session?.user) )){
				flash.message = "You don't have permission to do that."
				redirect(uri:"/")
				return false
			}
			
            try {
                membershipTypeInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'membershipType.label', default: 'MembershipType'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'membershipType.label', default: 'MembershipType'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'membershipType.label', default: 'MembershipType'), params.id])}"
            redirect(action: "list")
        }
    }
}
