

<%@ page import="torch.PositionType" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'position.label', default: 'Position')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <g:if test="${session?.PositionPermission.canCreateNew }">            
            	<span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        	</g:if>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${positionInstance}">
            <div class="errors">
                <g:renderErrors bean="${positionInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${positionInstance?.id}" />
                <g:hiddenField name="version" value="${positionInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name"><g:message code="position.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: positionInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${positionInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="termLength"><g:message code="position.termLength.label" default="Term Length" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: positionInstance, field: 'termLength', 'errors')}">
                                    <g:textField name="termLength" value="${fieldValue(bean: positionInstance, field: 'termLength')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="elected"><g:message code="position.elected.label" default="Elected" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: positionInstance, field: 'elected', 'errors')}">
                                    <g:checkBox name="elected" value="${positionInstance?.elected}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="sitePermissions"><g:message code="position.sitePermissions.label" default="Site Permissions" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: positionInstance, field: 'sitePermissions', 'errors')}">
                                    
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
		
		            <g:if test="${session?.PositionPermission.canDelete }">
                    	<span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                	</g:if>
                </div>
            </g:form>
        </div>
    </body>
</html>
