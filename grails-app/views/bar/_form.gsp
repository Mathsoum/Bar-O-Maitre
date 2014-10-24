<%@ page import="bar.o.maitre.Member; bar.o.maitre.Bar" %>



<div class="fieldcontain ${hasErrors(bean: barInstance, field: 'barName', 'error')} required">
	<label for="barName">
		<g:message code="bar.barName.label" default="Bar Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="barName" required="" value="${barInstance?.barName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: barInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="bar.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="description" required="" value="${barInstance?.description}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: barInstance, field: 'address', 'error')} required">
	<label for="address">
		<g:message code="bar.address.label" default="Address" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="address" required="" value="${barInstance?.address}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: barInstance, field: 'type', 'error')} required">
	<label for="type">
		<g:message code="bar.type.label" default="Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="type" required="" value="${barInstance?.type}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: barInstance, field: 'price', 'error')} required">
	<label for="price">
		<g:message code="bar.price.label" default="Price" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="price" required="" value="${barInstance?.price}"/>

</div>


