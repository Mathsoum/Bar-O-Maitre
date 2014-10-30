package bar.o.maitre

import grails.plugin.springsecurity.SpringSecurityService

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MemberController {

    SpringSecurityService springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Member.list(params), model:[memberInstanceCount: Member.count()]
    }

    def show(Member memberInstance) {
        respond memberInstance
    }

    def create() {
        respond new Member(params)
    }

    def save(Member memberInstance) {
        if (memberInstance == null) {
            notFound()
            return
        }

        if (memberInstance.hasErrors()) {
            respond memberInstance.errors, view:'create'
            return
        }

        memberInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'member.label', default: 'Member'), memberInstance.id])
                redirect action:'show', id: memberInstance.id //memberInstance
            }
            '*' { respond memberInstance, [status: CREATED] }
        }
    }

    def edit(Member memberInstance) {
        respond memberInstance
    }

    @Transactional
    def update(Member memberInstance) {
        if (memberInstance == null) {
            notFound()
            return
        }

        if (memberInstance.hasErrors()) {
            respond memberInstance.errors, view:'edit'
            return
        }

        memberInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Member.label', default: 'Member'), memberInstance.id])
                redirect memberInstance
            }
            '*'{ respond memberInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Member memberInstance) {

        if (memberInstance == null) {
            notFound()
            return
        }

        memberInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Member.label', default: 'Member'), memberInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'member.label', default: 'Member'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def add_as_friend(Member memberInstance) {
        Member currentUser = ((Member) springSecurityService.currentUser)
        currentUser.addToFriends(memberInstance)
        currentUser.save(flush: true)

        flash.message = "Friend successfully added ! =)"
        redirect action: "index", method: "GET"
    }

    def delete_friend(Member memberInstance) {
        Member currentUser = ((Member) springSecurityService.currentUser)
        currentUser.removeFromFriends(memberInstance)
        currentUser.save(flush: true)

        redirect action: "show", method: "GET", params: [id: currentUser.id]
    }
}
