
<%@ page import="torch.PositionType" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'position.label', default: 'Position')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <g:if test="${session?.PositionPermission.canCreateNew }">
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'position.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'position.name.label', default: 'Name')}" />
                        
                            <g:sortableColumn property="termLength" title="${message(code: 'position.termLength.label', default: 'Term Length')}" />
                        
                            <g:sortableColumn property="elected" title="${message(code: 'position.elected.label', default: 'Elected')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${positionInstanceList}" status="i" var="positionInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${positionInstance.id}">${fieldValue(bean: positionInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: positionInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: positionInstance, field: "termLength")}</td>
                        
                            <td><g:formatBoolean boolean="${positionInstance.elected}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${positionInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
