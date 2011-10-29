
<%@ page import="torch.Certification" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'certification.label', default: 'Certification')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <g:if test="${session?.CertificationPermission.canCreateNew }">
            	<span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
			</g:if>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'certification.id.label', default: 'Id')}" />
                        
                            <th><g:message code="certification.member.label" default="Member" /></th>
                        
                            <th><g:message code="certification.type.label" default="Type" /></th>
                        
                            <g:sortableColumn property="expires" title="${message(code: 'certification.expires.label', default: 'Expires')}" />
                        
                            <g:sortableColumn property="issued" title="${message(code: 'certification.issued.label', default: 'Issued')}" />
                        
                            <g:sortableColumn property="onFile" title="${message(code: 'certification.onFile.label', default: 'On File')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${certificationInstanceList}" status="i" var="certificationInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${certificationInstance.id}">${fieldValue(bean: certificationInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: certificationInstance, field: "member")}</td>
                        
                            <td>${fieldValue(bean: certificationInstance, field: "type")}</td>
                        
                            <td><g:formatDate date="${certificationInstance.expires}" /></td>
                        
                            <td><g:formatDate date="${certificationInstance.issued}" /></td>
                        
                            <td><g:formatBoolean boolean="${certificationInstance.onFile}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${certificationInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
