
<%@ page import="bar.o.maitre.Bar" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'bar.label', default: 'Bar')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-bar" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create">Créer ton bar</g:link></li>
			</ul>
		</div>
		<div id="list-bar" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>
      <g:if test="${flash.error}">
        <div class="errors" role="status">${flash.error}</div>
      </g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="barName" title="${message(code: 'bar.barName.label', default: 'Bar Name')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'bar.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="address" title="${message(code: 'bar.address.label', default: 'Address')}" />
					
						<g:sortableColumn property="type" title="${message(code: 'bar.type.label', default: 'Type')}" />
					
						<g:sortableColumn property="price" title="${message(code: 'bar.price.label', default: 'Price')}" />

                        <g:sortableColumn property="admin" title="${message(code: 'bar.admin.label', default: 'Admin')}" />

					</tr>
				</thead>
				<tbody>
				<g:each in="${barInstanceList}" status="i" var="barInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${barInstance.id}">${fieldValue(bean: barInstance, field: "barName")}</g:link></td>
					
						<td>${fieldValue(bean: barInstance, field: "description")}</td>
					
						<td>${fieldValue(bean: barInstance, field: "address")}</td>
					
						<td>${fieldValue(bean: barInstance, field: "type")}</td>
					
						<td>${fieldValue(bean: barInstance, field: "price")}</td>

                        <td>${fieldValue(bean: barInstance, field: "admin")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${barInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
