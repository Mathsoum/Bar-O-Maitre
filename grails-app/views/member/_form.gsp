<%@ page import="bar.o.maitre.Member" %>



<div class="fieldcontain ${hasErrors(bean: memberInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="member.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${memberInstance?.username}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: memberInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="member.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" required="" value="${memberInstance?.password}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: memberInstance, field: 'firstName', 'error')} required">
	<label for="firstName">
		<g:message code="member.firstName.label" default="First Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstName" required="" value="${memberInstance?.firstName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: memberInstance, field: 'lastName', 'error')} required">
	<label for="lastName">
		<g:message code="member.lastName.label" default="Last Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastName" required="" value="${memberInstance?.lastName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: memberInstance, field: 'mail', 'error')} required">
	<label for="mail">
		<g:message code="member.mail.label" default="Mail" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="mail" required="" value="${memberInstance?.mail}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: memberInstance, field: 'birthDate', 'error')} required">
	<label for="birthDate">
		<g:message code="member.birthDate.label" default="Birth Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="birthDate" precision="day"  value="${memberInstance?.birthDate}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: memberInstance, field: 'accountExpired', 'error')} ">
	<label for="accountExpired">
		<g:message code="member.accountExpired.label" default="Account Expired" />
		
	</label>
	<g:checkBox name="accountExpired" value="${memberInstance?.accountExpired}" />

</div>

<div class="fieldcontain ${hasErrors(bean: memberInstance, field: 'accountLocked', 'error')} ">
	<label for="accountLocked">
		<g:message code="member.accountLocked.label" default="Account Locked" />
		
	</label>
	<g:checkBox name="accountLocked" value="${memberInstance?.accountLocked}" />

</div>

<div class="fieldcontain ${hasErrors(bean: memberInstance, field: 'enabled', 'error')} ">
	<label for="enabled">
		<g:message code="member.enabled.label" default="Enabled" />
		
	</label>
	<g:checkBox name="enabled" value="${memberInstance?.enabled}" />

</div>

<div class="fieldcontain ${hasErrors(bean: memberInstance, field: 'passwordExpired', 'error')} ">
	<label for="passwordExpired">
		<g:message code="member.passwordExpired.label" default="Password Expired" />
		
	</label>
	<g:checkBox name="passwordExpired" value="${memberInstance?.passwordExpired}" />

</div>

