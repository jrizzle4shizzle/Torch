
<%@ page import="torch.Clearance" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'clearance.label', default: 'Clearance')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
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
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="clearance.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: clearanceInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="clearance.member.label" default="Member" /></td>
                            
                            <td valign="top" class="value"><g:link controller="member" action="show" id="${clearanceInstance?.member?.id}">${clearanceInstance?.member?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="clearance.type.label" default="Type" /></td>
                            
                            <td valign="top" class="value"><g:link controller="apparatusType" action="show" id="${clearanceInstance?.type?.id}">${clearanceInstance?.type?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="clearance.training.label" default="Training" /></td>
                            
                            <td valign="top" class="value"><g:formatBoolean boolean="${clearanceInstance?.training}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="clearance.ride.label" default="Ride" /></td>
                            
                            <td valign="top" class="value"><g:formatBoolean boolean="${clearanceInstance?.ride}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="clearance.driverTraining.label" default="Driver Training" /></td>
                            
                            <td valign="top" class="value"><g:formatBoolean boolean="${clearanceInstance?.driverTraining}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="clearance.sitePermissions.label" default="Site Permissions" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: clearanceInstance, field: "sitePermissions")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="clearance.drive.label" default="Drive" /></td>
                            
                            <td valign="top" class="value"><g:formatBoolean boolean="${clearanceInstance?.drive}" /></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${clearanceInstance?.id}" />
                    <g:if test="${session?.ClearancePermission.canEdit }">
                    	<span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    </g:if>
                    <g:if test="${session?.ClearancePermission.canDelete }">
                    	<span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                	</g:if>
                </g:form>
            </div>
        </div>
    </body>
</html>
