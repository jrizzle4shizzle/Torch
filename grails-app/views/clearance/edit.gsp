

<%@ page import="torch.Clearance" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'clearance.label', default: 'Clearance')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <g:if test="${session?.ClearancePermission.canCreateNew }">            
            	<span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        	</g:if>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${clearanceInstance}">
            <div class="errors">
                <g:renderErrors bean="${clearanceInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${clearanceInstance?.id}" />
                <g:hiddenField name="version" value="${clearanceInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="member"><g:message code="clearance.member.label" default="Member" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: clearanceInstance, field: 'member', 'errors')}">
                                    <g:select name="member.id" from="${torch.Member.list()}" optionKey="id" value="${clearanceInstance?.member?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="type"><g:message code="clearance.type.label" default="Type" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: clearanceInstance, field: 'type', 'errors')}">
                                    <g:select name="type.id" from="${torch.ApparatusType.list()}" optionKey="id" value="${clearanceInstance?.type?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="training"><g:message code="clearance.training.label" default="Training" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: clearanceInstance, field: 'training', 'errors')}">
                                    <g:checkBox name="training" value="${clearanceInstance?.training}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="ride"><g:message code="clearance.ride.label" default="Ride" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: clearanceInstance, field: 'ride', 'errors')}">
                                    <g:checkBox name="ride" value="${clearanceInstance?.ride}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="driverTraining"><g:message code="clearance.driverTraining.label" default="Driver Training" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: clearanceInstance, field: 'driverTraining', 'errors')}">
                                    <g:checkBox name="driverTraining" value="${clearanceInstance?.driverTraining}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="sitePermissions"><g:message code="clearance.sitePermissions.label" default="Site Permissions" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: clearanceInstance, field: 'sitePermissions', 'errors')}">
                                    
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="drive"><g:message code="clearance.drive.label" default="Drive" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: clearanceInstance, field: 'drive', 'errors')}">
                                    <g:checkBox name="drive" value="${clearanceInstance?.drive}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
		
		            <g:if test="${session?.ClearancePermission.canDelete }">
                    	<span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                	</g:if>
                </div>
            </g:form>
        </div>
    </body>
</html>
