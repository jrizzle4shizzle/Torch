

<%@ page import="torch.Rank" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'rank.label', default: 'Rank')}" />
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
            <g:hasErrors bean="${rankInstance}">
            <div class="errors">
                <g:renderErrors bean="${rankInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="rank.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rankInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${rankInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="precidence"><g:message code="rank.precidence.label" default="Precidence" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rankInstance, field: 'precidence', 'errors')}">
                                    <g:textField name="precidence" value="${fieldValue(bean: rankInstance, field: 'precidence')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="officer"><g:message code="rank.officer.label" default="Officer" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rankInstance, field: 'officer', 'errors')}">
                                    <g:checkBox name="officer" value="${rankInstance?.officer}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="nonRiding"><g:message code="rank.nonRiding.label" default="Non Riding" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: rankInstance, field: 'nonRiding', 'errors')}">
                                    <g:checkBox name="nonRiding" value="${rankInstance?.nonRiding}" />
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
