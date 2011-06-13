
<%@ page import="torch.Event" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'event.label', default: 'Event')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        	
        	<g:if test="${session?.eventPermission.canCreateNew }">    
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
                                                
                            <g:sortableColumn property="name" title="${message(code: 'event.name.label', default: 'Name')}" />
                        
                            <g:sortableColumn property="date" title="${message(code: 'event.date.label', default: 'Date')}" />
                        
                            <th><g:message code="event.pointOfContact.label" default="Point Of Contact" /></th>
                        
                            <g:sortableColumn property="type" title="${message(code: 'event.type.label', default: 'Type')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${eventInstanceList}" status="i" var="eventInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${eventInstance.id}">${fieldValue(bean: eventInstance, field: "name")}</g:link></td>
                                                
                            <td><g:formatDate date="${eventInstance.date}" /></td>
                        
                            <td>${fieldValue(bean: eventInstance, field: "pointOfContact")}</td>
                        
                            <td>${fieldValue(bean: eventInstance, field: "type")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${eventInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
