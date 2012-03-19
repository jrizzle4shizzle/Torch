

<%@ page import="torch.PositionType" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'position.label', default: 'Position')}" />
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
            <g:hasErrors bean="${positionInstance}">
            <div class="errors">
                <g:renderErrors bean="${positionInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="position.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: positionInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${positionInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="termLength"><g:message code="position.termLength.label" default="Term Length" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: positionInstance, field: 'termLength', 'errors')}">
                                    <g:textField name="termLength" value="${fieldValue(bean: positionInstance, field: 'termLength')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="elected"><g:message code="position.elected.label" default="Elected" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: positionInstance, field: 'elected', 'errors')}">
                                    <g:checkBox name="elected" value="${positionInstance?.elected}" />
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