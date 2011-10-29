
<%@ page import="torch.Apparatus" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'apparatus.label', default: 'Apparatus')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <g:if test="${session?.ApparatusPermission.canCreateNew }">
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
                            <td valign="top" class="name"><g:message code="apparatus.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: apparatusInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="apparatus.name.label" default="Name" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: apparatusInstance, field: "name")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="apparatus.type.label" default="Type" /></td>
                            
                            <td valign="top" class="value"><g:link controller="apparatusType" action="show" id="${apparatusInstance?.type?.id}">${apparatusInstance?.type?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="apparatus.maxRiding.label" default="Max Riding" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: apparatusInstance, field: "maxRiding")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="apparatus.minRiding.label" default="Min Riding" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: apparatusInstance, field: "minRiding")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="apparatus.modelAndYear.label" default="Model And Year" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: apparatusInstance, field: "modelAndYear")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="apparatus.sitePermissions.label" default="Site Permissions" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: apparatusInstance, field: "sitePermissions")}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${apparatusInstance?.id}" />
                    <g:if test="${session?.ApparatusPermission.canEdit }">
                    	<span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    </g:if>
                    <g:if test="${session?.ApparatusPermission.canDelete }">
                    	<span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                	</g:if>
                </g:form>
            </div>
        </div>
    </body>
</html>
