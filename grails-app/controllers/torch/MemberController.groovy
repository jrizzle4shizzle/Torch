package torch

class MemberController extends PrivateController{

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]	
	
	def beforeInterceptor = [action:this.&clearPermissions]
	
	def clearPermissions(){
		def perms = [:]
		session?.memberPermission=perms
	}
	
    def index = {
        redirect(action: "list", params: params)
    }
	

    def list = {
		//admin permissions
		if (session?.user?.role == "admin"){
			session?.memberPermission?.canCreateNew = true
			session?.memberPermission?.canDelete = true
		}
		
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [memberInstanceList: Member.list(params), memberInstanceTotal: Member.count()]
    }

    def create = {
		if( !(session?.user?.role == "admin") ){
			flash.message = "You must be an administrator to perform that task."
			redirect(action:"login")
			return false
		}
		
        def memberInstance = new Member()
        memberInstance.properties = params
        return [memberInstance: memberInstance]
    }

    def save = {
        def memberInstance = new Member(params)
		memberInstance.setPassword(params.password?.encodeAsHash())
        if (memberInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'member.label', default: 'Member'), memberInstance.id])}"
            redirect(action: "show", id: memberInstance.id)
        }
        else {
            render(view: "create", model: [memberInstance: memberInstance])
        }
    }

    def show = {
        def memberInstance = Member.get(params.id)
        if (!memberInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'member.label', default: 'Member'), params.id])}"
            redirect(action: "list")
        }
        else {
			//set admin permissions
			if(session?.user?.role == 'admin'){
				session?.memberPermission?.showNotes = true
				session?.memberPermission?.showDues = true
				session?.memberPermission?.showDemographics = true
				session?.memberPermission?.showLogin = true
				session?.memberPermission?.showRidingStatus = true
				session?.memberPermission?.showLifeInsuranceStatus = true
				session?.memberPermission?.showSSN = true
				session?.memberPermission?.canEdit = true
				session?.memberPermission?.showDriversLiscense = true
				session?.memberPermission?.canDelete = true
			}
			
			//set own profile permissions
			if(session?.user.login == memberInstance.login){
				session?.memberPermission?.showLogin = true
				session?.memberPermission?.showDues = true
				session?.memberPermission?.showRidingStatus = true
				session?.memberPermission?.showLifeInsuranceStatus = true
				session?.memberPermission?.showSSN = true
				session?.memberPermission?.showDriversLiscense = true
				session?.memberPermission?.canEdit = true
				
			}
			
            [memberInstance: memberInstance]
        }
    }

    def edit = {
		
        def memberInstance = Member.get(params.id)
        if (!memberInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'member.label', default: 'Member'), params.id])}"
            redirect(action: "list")
        }
        else {
			//only you or an admin can edit your profile
			if( !(session?.user?.role == "admin") && !(session?.user?.login == memberInstance.login)){
				flash.message = "You must be an administrator to perform that task."
				redirect(action:"login")
				return false
			}
			
			//permission: canEditAdmin
			if(session?.user?.role == "admin"){
				session?.permissions?.canEditAdmin = true
			}
			
            return [memberInstance: memberInstance]
        }
    }

    def update = {
        def memberInstance = Member.get(params.id)
        if (memberInstance) {
			//only you or an admin can edit your profile
			if( !(session?.user?.role == "admin") && !(session?.user == memberInstance)){
				flash.message = "You must be an administrator to perform that task."
				redirect(action:"login")
				return false
			}
			
            if (params.version) {
                def version = params.version.toLong()
                if (memberInstance.version > version) {
                    
                    memberInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'member.label', default: 'Member')] as Object[], "Another user has updated this Member while you were editing")
                    render(view: "edit", model: [memberInstance: memberInstance])
                    return
                }
            }
            memberInstance.properties = params
			memberInstance.setPassword(params.password?.encodeAsHash())
            if (!memberInstance.hasErrors() && memberInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'member.label', default: 'Member'), memberInstance.id])}"
                redirect(action: "show", id: memberInstance.id)
            }
            else {
                render(view: "edit", model: [memberInstance: memberInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'member.label', default: 'Member'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
		//only admins can delete
		if( !(session?.user?.role == "admin") ){
			flash.message = "You must be an administrator to perform that task."
			redirect(action:"login")
			return false
		}
		
        def memberInstance = Member.get(params.id)
        if (memberInstance) {
            try {
                memberInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'member.label', default: 'Member'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'member.label', default: 'Member'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'member.label', default: 'Member'), params.id])}"
            redirect(action: "list")
        }
    }
	
	def login = {}
	
	def authenticate = {
		def user = Member.findByLoginAndPassword(params.login, params.password.encodeAsHash())
		if(user){
			println("successful login!")
			
			def perms = [:]
			
			session.user = user
			session.permission = perms
			flash.message = "Hello ${user.firstName} ${user.lastName}!"
			redirect(controller:"member", action:"list")
		}else{
		  flash.message = "Sorry, ${params.login}. Please try again."
		  redirect(action:"login")
		}
	  }
	  
	  def logout = {
		flash.message = "Goodbye ${session.user.firstName}"
		session.user = null
		redirect(action:"login")
	  }
}
