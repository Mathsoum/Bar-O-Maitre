package bar.o.maitre

import grails.test.mixin.Mock

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.NOT_FOUND

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BarController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    SpringSecurityService springSecurityService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Bar.list(params), model: [barInstanceCount: Bar.count()]
    }

    def show(Bar barInstance) {
        if (barInstance == null) {
            notFound()
            return
        }

        boolean userCanModify = barInstance.admin == springSecurityService.currentUser
        boolean userCanLike = springSecurityService.isLoggedIn()
        //TODO A user can modify a bar regarding his rank !!

        render(view: "show", model: [barInstance: barInstance,
                                     userCanModify: userCanModify,
                                     userCanLike: userCanLike])
    }

    @Secured("ROLE_ADMIN")
    def create() {
        respond new Bar(params)
    }

    @Transactional
    def save(Bar barInstance) {
        if (barInstance == null) {
            notFound()
            return
        }

        barInstance.admin = springSecurityService.currentUser

        barInstance.validate()

        if (barInstance.hasErrors()) {
            respond barInstance.errors, view: 'create'
            return
        }

        barInstance.save flush: true

        request.withFormat {
            form multipartForm {
               flash.message = message(code: 'default.created.message', args: [message(code: 'bar.label', default: 'Bar'), barInstance.id])
                redirect barInstance
            }
            '*' { respond barInstance, [status: CREATED] }
        }
    }

    def edit(Bar barInstance) {
        // TODO Secured by ACL bro !
        respond barInstance
    }

    @Transactional
    def update(Bar barInstance) {
        if (barInstance == null) {
            notFound()
            return
        }

        if (barInstance.hasErrors()) {
            respond barInstance.errors, view: 'edit'
            return
        }

        barInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'bar.label', default: 'Bar'), barInstance.barName])
                redirect barInstance
            }
            '*' { respond barInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Bar barInstance) {
        if (barInstance == null) {
            notFound()
            return
        }

        //if (barInstance.admin == springSecurityService.currentUser) {

        barInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'bar.label', default: 'Bar'), barInstance.id])
                redirect barInstance
            }
            '*' { respond barInstance, [status: OK] }
        }
        //}
    }

    def like(Bar barInstance) {
        Member currentUser = ((Member) springSecurityService.currentUser)
        if (!barInstance.likers.contains(currentUser)){
            barInstance.addToLikers(currentUser)
            flash.message = "+1 Like"
        } else {
            flash.message = "Vous avez déjà liké ce bar"
        }
        barInstance.validate()
        barInstance.save(true)
        redirect action: "show", id: barInstance.id, method: "GET"
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'bar.label', default: 'Bar'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
