class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error/500')
        "404"(view:'/error/404')
        "/logout"(controller: 'customLogout')
	}
}
