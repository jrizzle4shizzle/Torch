

<%@ page import="torch.Apparatus" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'apparatus.label', default: 'Apparatus')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${apparatusInstance}">
            <div class="errors">
                <g:renderErrors bean="${apparatusInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="apparatus.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: apparatusInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${apparatusInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="type"><g:message code="apparatus.type.label" default="Type" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: apparatusInstance, field: 'type', 'errors')}">
                                    <g:select name="type.id" from="${torch.ApparatusType.list()}" optionKey="id" value="${apparatusInstance?.type?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="maxRiding"><g:message code="apparatus.maxRiding.label" default="Max Riding" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: apparatusInstance, field: 'maxRiding', 'errors')}">
                                    <g:textField name="maxRiding" value="${fieldValue(bean: apparatusInstance, field: 'maxRiding')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="minRiding"><g:message code="apparatus.minRiding.label" default="Min Riding" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: apparatusInstance, field: 'minRiding', 'errors')}">
                                    <g:textField name="minRiding" value="${fieldValue(bean: apparatusInstance, field: 'minRiding')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="modelAndYear"><g:message code="apparatus.modelAndYear.label" default="Model And Year" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: apparatusInstance, field: 'modelAndYear', 'errors')}">
                                    <g:textField name="modelAndYear" value="${apparatusInstance?.modelAndYear}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
