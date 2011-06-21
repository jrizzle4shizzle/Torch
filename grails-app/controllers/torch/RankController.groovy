package torch

class RankController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	static permissions = ["Rank.all", "Rank.create", "Rank.update", "Rank.delete"]
	
	def beforeInterceptor = [action:this.&clearPermissions]
	
	boolean canCreate(Member user){
		if(session?.user?.role == "admin"){
			return true
		}
		
		def userPermissions = SitePermissions.getPermissionsForUser(user)
		
		return(userPermissions?.contains("Rank.create"))
	}
	
	boolean canUpdate(Member user){
		if(session?.user?.role == "admin"){
			return true
		}
		
		def userPermissions = SitePermissions.getPermissionsForUser(user)
		
		return(userPermissions?.contains("Rank.update"))
	}
	
	boolean canDelete(Member user){
		if(session?.user?.role == "admin"){
			return true
		}
		
		def userPermissions = SitePermissions.getPermissionsForUser(user)
		
		return(userPermissions?.contains("Rank.delete"))
	}
	
	def clearPermissions(){
		def perms = [:]
		session?.RankPermission=perms
	}
	
    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [rankInstanceList: Rank.list(params), rankInstanceTotal: Rank.count()]
    }

    def create = {
		//permission check
		if( !(canCreate(session?.user))){
			flash.message = "You don't have permission to do that."
			redirect(uri:"/")
			return false
		}
		
        def rankInstance = new Rank()
        rankInstance.properties = params
        return [rankInstance: rankInstance]
    }

    def save = {
		//permission check
		if( !(canCreate(session?.user))){
			flash.message = "You don't have permission to do that."
			redirect(uri:"/")
			return false
		}
		
        def rankInstance = new Rank(params)
        if (rankInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'rank.label', default: 'Rank'), rankInstance.id])}"
            redirect(action: "show", id: rankInstance.id)
        }
        else {
            render(view: "create", model: [rankInstance: rankInstance])
        }
    }

    def show = {
        def rankInstance = Rank.get(params.id)
        if (!rankInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'rank.label', default: 'Rank'), params.id])}"
            redirect(action: "list")
        }
        else {
			//set permissions
			if(canUpdate(session?.user)){
				session?.RankPermission?.canEdit = true
			}
			if(canDelete(session?.user)){
				session?.RankPermission?.canDelete = true
			}
			
			if(canCreate(session?.user)){
				session?.RankPermission?.canCreateNew = true
			}
			
            [rankInstance: rankInstance]
        }
    }

    def edit = {
        def rankInstance = Rank.get(params.id)
        if (!rankInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'rank.label', default: 'Rank'), params.id])}"
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
				session?.RankPermission?.canEdit = true
			}
			if(canDelete(session?.user)){
				session?.RankPermission?.canDelete = true
			}
			
			if(canCreate(session?.user)){
				session?.RankPermission?.canCreateNew = true
			}
            return [rankInstance: rankInstance]
        }
    }

    def update = {
        def rankInstance = Rank.get(params.id)
        if (rankInstance) {
			//permissions check
			if( !(canUpdate(session?.user))){
				flash.message = "You don't have permission to do that."
				redirect(uri:"/")
				return false
			}
			
            if (params.version) {
                def version = params.version.toLong()
                if (rankInstance.version > version) {
                    
                    rankInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'rank.label', default: 'Rank')] as Object[], "Another user has updated this Rank while you were editing")
                    render(view: "edit", model: [rankInstance: rankInstance])
                    return
                }
            }
            rankInstance.properties = params
            if (!rankInstance.hasErrors() && rankInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'rank.label', default: 'Rank'), rankInstance.id])}"
                redirect(action: "show", id: rankInstance.id)
            }
            else {
                render(view: "edit", model: [rankInstance: rankInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'rank.label', default: 'Rank'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def rankInstance = Rank.get(params.id)
        if (rankInstance) {
			//permissions check
			if( !(canDelete(session?.user) )){
				flash.message = "You don't have permission to do that."
				redirect(uri:"/")
				return false
			}
			
            try {
                rankInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'rank.label', default: 'Rank'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'rank.label', default: 'Rank'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'rank.label', default: 'Rank'), params.id])}"
            redirect(action: "list")
        }
    }
}
