
<%@ page import="torch.ElectedPosition" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'electedPosition.label', default: 'ElectedPosition')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <g:if test="${session?.ElectedPositionPermission.canCreateNew }">
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'electedPosition.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'electedPosition.name.label', default: 'Name')}" />
                        
                            <th><g:message code="electedPosition.member.label" default="Member" /></th>
                        
                            <th><g:message code="electedPosition.type.label" default="Type" /></th>
                        
                            <g:sortableColumn property="start" title="${message(code: 'electedPosition.start.label', default: 'Start')}" />
                        
                            <g:sortableColumn property="finish" title="${message(code: 'electedPosition.finish.label', default: 'Finish')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${electedPositionInstanceList}" status="i" var="electedPositionInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${electedPositionInstance.id}">${fieldValue(bean: electedPositionInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: electedPositionInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: electedPositionInstance, field: "member")}</td>
                        
                            <td>${fieldValue(bean: electedPositionInstance, field: "type")}</td>
                        
                            <td><g:formatDate date="${electedPositionInstance.start}" /></td>
                        
                            <td><g:formatDate date="${electedPositionInstance.finish}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${electedPositionInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
