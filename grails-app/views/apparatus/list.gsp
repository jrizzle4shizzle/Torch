
<%@ page import="torch.Apparatus" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'apparatus.label', default: 'Apparatus')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <g:if test="${session?.ApparatusPermission.canCreateNew }">
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'apparatus.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'apparatus.name.label', default: 'Name')}" />
                        
                            <th><g:message code="apparatus.type.label" default="Type" /></th>
                        
                            <g:sortableColumn property="maxRiding" title="${message(code: 'apparatus.maxRiding.label', default: 'Max Riding')}" />
                        
                            <g:sortableColumn property="minRiding" title="${message(code: 'apparatus.minRiding.label', default: 'Min Riding')}" />
                        
                            <g:sortableColumn property="modelAndYear" title="${message(code: 'apparatus.modelAndYear.label', default: 'Model And Year')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${apparatusInstanceList}" status="i" var="apparatusInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${apparatusInstance.id}">${fieldValue(bean: apparatusInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: apparatusInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: apparatusInstance, field: "type")}</td>
                        
                            <td>${fieldValue(bean: apparatusInstance, field: "maxRiding")}</td>
                        
                            <td>${fieldValue(bean: apparatusInstance, field: "minRiding")}</td>
                        
                            <td>${fieldValue(bean: apparatusInstance, field: "modelAndYear")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${apparatusInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
