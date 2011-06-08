
<%@ page import="torch.Committee" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'committee.label', default: 'Committee')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'committee.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'committee.name.label', default: 'Name')}" />
                        
                            <th><g:message code="committee.chair.label" default="Chair" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${committeeInstanceList}" status="i" var="committeeInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${committeeInstance.id}">${fieldValue(bean: committeeInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: committeeInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: committeeInstance, field: "chair")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${committeeInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
