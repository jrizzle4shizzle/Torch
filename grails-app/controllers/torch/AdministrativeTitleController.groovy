package torch

class AdministrativeTitleController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [administrativeTitleInstanceList: AdministrativeTitle.list(params), administrativeTitleInstanceTotal: AdministrativeTitle.count()]
    }

    def create = {
        def administrativeTitleInstance = new AdministrativeTitle()
        administrativeTitleInstance.properties = params
        return [administrativeTitleInstance: administrativeTitleInstance]
    }

    def save = {
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
            return [administrativeTitleInstance: administrativeTitleInstance]
        }
    }

    def update = {
        def administrativeTitleInstance = AdministrativeTitle.get(params.id)
        if (administrativeTitleInstance) {
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
