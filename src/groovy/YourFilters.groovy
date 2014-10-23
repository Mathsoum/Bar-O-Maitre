/**
 * Created by T430 on 23/10/2014.
 */
class YourFilters {
    static nonAuthenticatedActions = [
            [controller:'authentication', action:'*']
    ]
    def filters = {
        accessFilter(controller:'*', action:'*') {
            before = {
                boolean needsAuth = !nonAuthenticatedActions.find {
                    (it.controller == controllerName) && ((it.action == '*')
                            || (it.action == actionName))
                }
                if (needsAuth) {
                    return applicationContext.authenticationService.filterRequest(
                            request, response,
                            "${request.contextPath}/authentication/index" )
                } else return true
            }
        }
    }
}