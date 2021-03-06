<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'logo.jpg')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'logo.jpg')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'logo.jpg')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
		<g:layoutHead/>
		<g:javascript library="application"/>		
		<r:layoutResources />

	</head>
	<body>
		<div id="grailsLogo" role="banner"><a href="${createLink(uri: '/')}"><img src="${resource(dir: 'images', file: 'logo.jpg')}" alt="Grails"/></a>
            <g:set var="springSecurity" bean="springSecurityService"/>
            <g:if test="${springSecurity.loggedIn}">
              Salut <g:link controller="member" action="show" id="${springSecurity.currentUser.id}">${springSecurity.currentUser.username}</g:link> -- <g:link controller="customLogout" action="index">Disconnect</g:link>
            </g:if>
            <g:else>
              <g:link controller="login" action="auth">Login</g:link> -- <g:link controller="member" action="create">Create account</g:link>
            </g:else>
                    <g:each var="c" in="${grailsApplication.domainClasses.sort { it.name } }">
                        <g:if test="${c.name == "Bar"}">
                            <g:link controller="${c.logicalPropertyName}">Liste des Bars</g:link>
                        </g:if>
                    </g:each>
        </div>

		<g:layoutBody/>
		<div class="footer" role="contentinfo"></div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
		<r:layoutResources />
	</body>
</html>
