package bar.o.maitre


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BarController {

    static scaffold = true
}
