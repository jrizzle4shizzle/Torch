
<%@ page import="torch.Certification" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'certification.label', default: 'Certification')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
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
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="certification.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: certificationInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="certification.member.label" default="Member" /></td>
                            
                            <td valign="top" class="value"><g:link controller="member" action="show" id="${certificationInstance?.member?.id}">${certificationInstance?.member?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="certification.type.label" default="Type" /></td>
                            
                            <td valign="top" class="value"><g:link controller="certificationType" action="show" id="${certificationInstance?.type?.id}">${certificationInstance?.type?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="certification.expires.label" default="Expires" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${certificationInstance?.expires}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="certification.issued.label" default="Issued" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${certificationInstance?.issued}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="certification.onFile.label" default="On File" /></td>
                            
                            <td valign="top" class="value"><g:formatBoolean boolean="${certificationInstance?.onFile}" /></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${certificationInstance?.id}" />
                    <g:if test="${session?.CertificationPermission.canEdit }">
                    	<span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    </g:if>
                    <g:if test="${session?.CertificationPermission.canDelete }">
                    	<span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                	</g:if>
                </g:form>
            </div>
        </div>
    </body>
</html>
