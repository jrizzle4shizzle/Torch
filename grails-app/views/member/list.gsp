
<%@ page import="torch.Member" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'member.label', default: 'Member')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'member.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="aacoRidingDatabase" title="${message(code: 'member.aacoRidingDatabase.label', default: 'Aaco Riding Database')}" />
                        
                            <g:sortableColumn property="administrative" title="${message(code: 'member.administrative.label', default: 'Administrative')}" />
                        
                            <g:sortableColumn property="badgeNumber" title="${message(code: 'member.badgeNumber.label', default: 'Badge Number')}" />
                        
                            <g:sortableColumn property="birthDay" title="${message(code: 'member.birthDay.label', default: 'Birth Day')}" />
                        
                            <g:sortableColumn property="driversLicenseClass" title="${message(code: 'member.driversLicenseClass.label', default: 'Drivers License Class')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${memberInstanceList}" status="i" var="memberInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${memberInstance.id}">${fieldValue(bean: memberInstance, field: "id")}</g:link></td>
                        
                            <td><g:formatBoolean boolean="${memberInstance.aacoRidingDatabase}" /></td>
                        
                            <td><g:formatBoolean boolean="${memberInstance.administrative}" /></td>
                        
                            <td>${fieldValue(bean: memberInstance, field: "badgeNumber")}</td>
                        
                            <td>${fieldValue(bean: memberInstance, field: "birthDay")}</td>
                        
                            <td>${fieldValue(bean: memberInstance, field: "driversLicenseClass")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${memberInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
