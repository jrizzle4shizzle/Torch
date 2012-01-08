
<%@ page import="torch.ElectedPosition" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'electedPosition.label', default: 'ElectedPosition')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <g:if test="${session?.ElectedPositionPermission.canCreateNew }">
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
                            <td valign="top" class="name"><g:message code="electedPosition.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: electedPositionInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="electedPosition.name.label" default="Name" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: electedPositionInstance, field: "name")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="electedPosition.member.label" default="Member" /></td>
                            
                            <td valign="top" class="value"><g:link controller="member" action="show" id="${electedPositionInstance?.member?.id}">${electedPositionInstance?.member?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="electedPosition.type.label" default="Type" /></td>
                            
                            <td valign="top" class="value"><g:link controller="positionType" action="show" id="${electedPositionInstance?.type?.id}">${electedPositionInstance?.type?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="electedPosition.start.label" default="Start" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${electedPositionInstance?.start}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="electedPosition.finish.label" default="Finish" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${electedPositionInstance?.finish}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="electedPosition.termEnd.label" default="Term End" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${electedPositionInstance?.termEnd}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="electedPosition.sitePermissions.label" default="Site Permissions" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: electedPositionInstance, field: "sitePermissions")}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${electedPositionInstance?.id}" />
                    <g:if test="${session?.ElectedPositionPermission.canEdit }">
                    	<span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    </g:if>
                    <g:if test="${session?.ElectedPositionPermission.canDelete }">
                    	<span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                	</g:if>
                </g:form>
            </div>
        </div>
    </body>
</html>
