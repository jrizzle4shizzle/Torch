
<%@ page import="torch.Clearance" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'clearance.label', default: 'Clearance')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <g:if test="${session?.ClearancePermission.canCreateNew }">
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'clearance.id.label', default: 'Id')}" />
                        
                            <th><g:message code="clearance.member.label" default="Member" /></th>
                        
                            <th><g:message code="clearance.type.label" default="Type" /></th>
                        
                            <g:sortableColumn property="training" title="${message(code: 'clearance.training.label', default: 'Training')}" />
                        
                            <g:sortableColumn property="ride" title="${message(code: 'clearance.ride.label', default: 'Ride')}" />
                        
                            <g:sortableColumn property="driverTraining" title="${message(code: 'clearance.driverTraining.label', default: 'Driver Training')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${clearanceInstanceList}" status="i" var="clearanceInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${clearanceInstance.id}">${fieldValue(bean: clearanceInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: clearanceInstance, field: "member")}</td>
                        
                            <td>${fieldValue(bean: clearanceInstance, field: "type")}</td>
                        
                            <td><g:formatBoolean boolean="${clearanceInstance.training}" /></td>
                        
                            <td><g:formatBoolean boolean="${clearanceInstance.ride}" /></td>
                        
                            <td><g:formatBoolean boolean="${clearanceInstance.driverTraining}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${clearanceInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
