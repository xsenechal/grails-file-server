package gfileserver



import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.access.annotation.Secured

@Secured(['ROLE_ALLACCESS'])
class ShareFileController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [shareFileInstanceList: ShareFile.list(params), shareFileInstanceTotal: ShareFile.count()]
    }

    def create() {
        [shareFileInstance: new ShareFile(params)]
    }

    def save() {
        def shareFileInstance = new ShareFile(params) 
        if (!shareFileInstance.save(flush: true)) {
            render(view: "create", model: [shareFileInstance: shareFileInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'shareFile.label', default: 'ShareFile'), shareFileInstance.id])
        redirect(action: "show", id: shareFileInstance.id)
    }

    def show() {
        def shareFileInstance = ShareFile.get(params.id)
        if (!shareFileInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'shareFile.label', default: 'ShareFile'), params.id])
            redirect(action: "list")
            return
        }

        [shareFileInstance: shareFileInstance]
    }

    def edit() {
        def shareFileInstance = ShareFile.get(params.id)
        if (!shareFileInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'shareFile.label', default: 'ShareFile'), params.id])
            redirect(action: "list")
            return
        }

        [shareFileInstance: shareFileInstance]
    }

    def update() {
        def shareFileInstance = ShareFile.get(params.id)
        if (!shareFileInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'shareFile.label', default: 'ShareFile'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (shareFileInstance.version > version) {
                shareFileInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'shareFile.label', default: 'ShareFile')] as Object[],
                          "Another user has updated this ShareFile while you were editing")
                render(view: "edit", model: [shareFileInstance: shareFileInstance])
                return
            }
        }

        shareFileInstance.properties = params

        if (!shareFileInstance.save(flush: true)) {
            render(view: "edit", model: [shareFileInstance: shareFileInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'shareFile.label', default: 'ShareFile'), shareFileInstance.id])
        redirect(action: "show", id: shareFileInstance.id)
    }

    def delete() {
        def shareFileInstance = ShareFile.get(params.id)
        if (!shareFileInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'shareFile.label', default: 'ShareFile'), params.id])
            redirect(action: "list")
            return
        }

        try {
            shareFileInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'shareFile.label', default: 'ShareFile'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'shareFile.label', default: 'ShareFile'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
