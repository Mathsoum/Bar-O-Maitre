
<%@ page import="bar.o.maitre.MemberRank; bar.o.maitre.Member" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'member.label', default: 'Member')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-member" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-member" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list member">
			
				<g:if test="${memberInstance?.username}">
				<li class="fieldcontain">
					<span id="username-label" class="property-label"><g:message code="member.username.label" default="Username" /></span>
					
						<span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${memberInstance}" field="username"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${memberInstance?.firstName}">
				<li class="fieldcontain">
					<span id="firstName-label" class="property-label"><g:message code="member.firstName.label" default="First Name" /></span>
					
						<span class="property-value" aria-labelledby="firstName-label"><g:fieldValue bean="${memberInstance}" field="firstName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${memberInstance?.lastName}">
				<li class="fieldcontain">
					<span id="lastName-label" class="property-label"><g:message code="member.lastName.label" default="Last Name" /></span>
					
						<span class="property-value" aria-labelledby="lastName-label"><g:fieldValue bean="${memberInstance}" field="lastName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${memberInstance?.mail}">
				<li class="fieldcontain">
					<span id="mail-label" class="property-label"><g:message code="member.mail.label" default="Mail" /></span>
					
						<span class="property-value" aria-labelledby="mail-label"><g:fieldValue bean="${memberInstance}" field="mail"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${memberInstance?.birthDate}">
				<li class="fieldcontain">
					<span id="birthDate-label" class="property-label"><g:message code="member.birthDate.label" default="Birth Date" /></span>
					
						<span class="property-value" aria-labelledby="birthDate-label"><g:formatDate date="${memberInstance?.birthDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${memberInstance?.accountExpired}">
				<li class="fieldcontain">
					<span id="accountExpired-label" class="property-label"><g:message code="member.accountExpired.label" default="Account Expired" /></span>
					
						<span class="property-value" aria-labelledby="accountExpired-label"><g:formatBoolean boolean="${memberInstance?.accountExpired}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${memberInstance?.accountLocked}">
				<li class="fieldcontain">
					<span id="accountLocked-label" class="property-label"><g:message code="member.accountLocked.label" default="Account Locked" /></span>
					
						<span class="property-value" aria-labelledby="accountLocked-label"><g:formatBoolean boolean="${memberInstance?.accountLocked}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${memberInstance?.enabled}">
				<li class="fieldcontain">
					<span id="enabled-label" class="property-label"><g:message code="member.enabled.label" default="Enabled" /></span>
					
						<span class="property-value" aria-labelledby="enabled-label"><g:formatBoolean boolean="${memberInstance?.enabled}" /></span>
					
				</li>
				</g:if>

                    <li class="fieldcontain">
                        <span id="rank-label" class="property-label"><g:message code="member.rank.label" default="Rank" /></span>

                        <span class="property-value" aria-labelledby="rank-label">${bar.o.maitre.MemberRank.findByMember(memberInstance).getRank().getAuthority()}</span>

                    </li>

        <g:if test="${memberInstance?.passwordExpired}">
          <li class="fieldcontain">
            <span id="passwordExpired-label" class="property-label"><g:message code="member.passwordExpired.label" default="Password Expired" /></span>

            <span class="property-value" aria-labelledby="passwordExpired-label"><g:formatBoolean boolean="${memberInstance?.passwordExpired}" /></span>

          </li>
        </g:if>

        <g:if test="${memberInstance?.friends}">
          <li class="fieldcontain">
            <span id="add-friends" class="property-label">Friends</span>

            <span class="property-value" aria-labelledby="passwordExpired-label">
            <ul style="list-style-type: none">
            <g:each in="${memberInstance?.friends}">
              <li>
                <p><g:link action="show" resource="${it}">${ it.username }</g:link>
                   <g:link action="delete_friend" resource="${it}">Delete that friend</g:link>
                </p>
              </li>
            </g:each>
            </ul>
            </span>
          </li>
        </g:if>
			
			</ol>
			<g:form url="[resource:memberInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${memberInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
          <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
          <g:link class="add_friend" action="add_as_friend" resource="${memberInstance}">Add to my friend list</g:link>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
