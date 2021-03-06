
<%@ page import="torch.Rank" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'rank.label', default: 'Rank')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <g:if test="${session?.RankPermission.canCreateNew }">
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'rank.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'rank.name.label', default: 'Name')}" />
                        
                            <g:sortableColumn property="precidence" title="${message(code: 'rank.precidence.label', default: 'Precidence')}" />
                        
                            <g:sortableColumn property="officer" title="${message(code: 'rank.officer.label', default: 'Officer')}" />
                        
                            <g:sortableColumn property="nonRiding" title="${message(code: 'rank.nonRiding.label', default: 'Non Riding')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${rankInstanceList}" status="i" var="rankInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${rankInstance.id}">${fieldValue(bean: rankInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: rankInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: rankInstance, field: "precidence")}</td>
                        
                            <td><g:formatBoolean boolean="${rankInstance.officer}" /></td>
                        
                            <td><g:formatBoolean boolean="${rankInstance.nonRiding}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${rankInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
