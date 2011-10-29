

<%@ page import="torch.Certification" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'certification.label', default: 'Certification')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <g:if test="${session?.CertificationPermission.canCreateNew }">            
            	<span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        	</g:if>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${certificationInstance}">
            <div class="errors">
                <g:renderErrors bean="${certificationInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${certificationInstance?.id}" />
                <g:hiddenField name="version" value="${certificationInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="member"><g:message code="certification.member.label" default="Member" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: certificationInstance, field: 'member', 'errors')}">
                                    <g:select name="member.id" from="${torch.Member.list()}" optionKey="id" value="${certificationInstance?.member?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="type"><g:message code="certification.type.label" default="Type" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: certificationInstance, field: 'type', 'errors')}">
                                    <g:select name="type.id" from="${torch.CertificationType.list()}" optionKey="id" value="${certificationInstance?.type?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="expires"><g:message code="certification.expires.label" default="Expires" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: certificationInstance, field: 'expires', 'errors')}">
                                    <g:datePicker name="expires" precision="day" value="${certificationInstance?.expires}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="issued"><g:message code="certification.issued.label" default="Issued" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: certificationInstance, field: 'issued', 'errors')}">
                                    <g:datePicker name="issued" precision="day" value="${certificationInstance?.issued}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="onFile"><g:message code="certification.onFile.label" default="On File" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: certificationInstance, field: 'onFile', 'errors')}">
                                    <g:checkBox name="onFile" value="${certificationInstance?.onFile}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
		
		            <g:if test="${session?.CertificationPermission.canDelete }">
                    	<span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                	</g:if>
                </div>
            </g:form>
        </div>
    </body>
</html>
