package bar.o.maitre

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.acl.AclClass
import grails.plugin.springsecurity.acl.AclEntry
import grails.plugin.springsecurity.acl.AclObjectIdentity
import grails.plugin.springsecurity.acl.AclSid
import grails.plugin.springsecurity.annotation.Secured
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission

import static org.springframework.http.HttpStatus.*
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
        boolean userCanModify = barInstance.admin == springSecurityService.currentUser
        //TODO A user can modify a bar regarding his rank !!

        render(view: "show", model: [barInstance: barInstance,
                                     userCanModify: userCanModify])
    }

    def create() {
        respond new Bar(params)
    }

    @Transactional
    def save(Bar barInstance) {


        if (barInstance == null) {
            notFound()
            return
        }


        Member currentUser = (Member) springSecurityService.currentUser
        barInstance.setAdmin(currentUser)
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

    //TODO Add role using hasAnyRole in place of hasRole
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasPermission(barInstance, 'admin')")
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
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Bar.label', default: 'Bar'), barInstance.id])
                redirect barInstance
            }
            '*' { respond barInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Bar barInstance) {
        if (barInstance.admin != springSecurityService.currentUser)
        {
            System.out.println("Impossible de delete")
            redirect barInstance
        } else {
            if (barInstance == null) {
                notFound()
                return
            }

            barInstance.delete flush: true

            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'default.deleted.message', args: [message(code: 'Bar.label', default: 'Bar'), barInstance.id])
                    redirect action: "index", method: "GET"
                }
                '*' { render status: NO_CONTENT }
            }
        }
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
