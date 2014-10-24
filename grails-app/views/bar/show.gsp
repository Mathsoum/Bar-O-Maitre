
<%@ page import="bar.o.maitre.Bar" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'bar.label', default: 'Bar')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-bar" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-bar" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list bar">
			
				<g:if test="${barInstance?.barName}">
				<li class="fieldcontain">
					<span id="barName-label" class="property-label"><g:message code="bar.barName.label" default="Bar Name" /></span>
					
						<span class="property-value" aria-labelledby="barName-label"><g:fieldValue bean="${barInstance}" field="barName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${barInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="bar.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${barInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${barInstance?.address}">
				<li class="fieldcontain">
					<span id="address-label" class="property-label"><g:message code="bar.address.label" default="Address" /></span>
					
						<span class="property-value" aria-labelledby="address-label"><g:fieldValue bean="${barInstance}" field="address"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${barInstance?.type}">
				<li class="fieldcontain">
					<span id="type-label" class="property-label"><g:message code="bar.type.label" default="Type" /></span>
					
						<span class="property-value" aria-labelledby="type-label"><g:fieldValue bean="${barInstance}" field="type"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${barInstance?.price}">
				<li class="fieldcontain">
					<span id="price-label" class="property-label"><g:message code="bar.price.label" default="Price" /></span>
					
						<span class="property-value" aria-labelledby="price-label"><g:fieldValue bean="${barInstance}" field="price"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:barInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${barInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
