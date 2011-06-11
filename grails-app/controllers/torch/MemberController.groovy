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
		if( !(session?.user?.role == "admin") ){
			flash.message = "You must be an administrator to perform that task."
			redirect(action:"login")
			return false
		}
		
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
				session?.memberPermission?.showDriversLiscense = true
				session?.memberPermission?.showRole = true
				
				session?.memberPermission?.canEdit = true
				session?.memberPermission?.canDelete = true
				session?.memberPermission?.canCreateNew = true
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
			
			//set admin permissions
			if(session?.user?.role == 'admin'){
				session?.memberPermission?.editLogin = true
				session?.memberPermission?.editPassword = true
				session?.memberPermission?.editName = true
				session?.memberPermission?.editAddress = true
				session?.memberPermission?.editPhoneNumbers = true
				session?.memberPermission?.editNotes = true
				session?.memberPermission?.editRank = true
				session?.memberPermission?.editMembershipType = true
				session?.memberPermission?.editCommittees = true
				session?.memberPermission?.editBirthday = true
				session?.memberPermission?.editBadgeNumber = true
				session?.memberPermission?.editDues = true
				session?.memberPermission?.editDemographics = true
				session?.memberPermission?.editRidingStatus = true
				session?.memberPermission?.editLifeInsuranceStatus = true
				session?.memberPermission?.editSSN = true
				session?.memberPermission?.editDriversLicense = true
				session?.memberPermission?.editActive = true
				session?.memberPermission?.editAdministrativeMember = true
				session?.memberPermission?.editMembershipDate = true
				session?.memberPermission?.editRole = true
				
				session?.memberPermission?.canEdit = true
				session?.memberPermission?.canDelete = true
				session?.memberPermission?.canCreateNew = true
			}
						
			//set own profile permissions
			if(session?.user.login == memberInstance.login){
				session?.memberPermission?.editPassword = true
				session?.memberPermission?.editName = true
				session?.memberPermission?.editAddress = true
				session?.memberPermission?.editPhoneNumbers = true
				session?.memberPermission?.editBirthday = true
				session?.memberPermission?.editDriversLicense = true
				
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
			//TODO: break this up
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
			//redirect(controller:"member", action:"list")
			redirect(uri:"/")
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
