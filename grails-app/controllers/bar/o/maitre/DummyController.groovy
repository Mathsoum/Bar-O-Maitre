package bar.o.maitre

import grails.plugin.springsecurity.annotation.Secured

class DummyController {

    @Secured(['ROLE_ADMIN'])
    def index()
    {
        render 'Secure access bro'
    }
}
