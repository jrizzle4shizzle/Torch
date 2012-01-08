

<%@ page import="torch.ElectedPosition" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'electedPosition.label', default: 'ElectedPosition')}" />
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
            <g:hasErrors bean="${electedPositionInstance}">
            <div class="errors">
                <g:renderErrors bean="${electedPositionInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="electedPosition.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: electedPositionInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${electedPositionInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="member"><g:message code="electedPosition.member.label" default="Member" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: electedPositionInstance, field: 'member', 'errors')}">
                                    <g:select name="member.id" from="${torch.Member.list()}" optionKey="id" value="${electedPositionInstance?.member?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="type"><g:message code="electedPosition.type.label" default="Type" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: electedPositionInstance, field: 'type', 'errors')}">
                                    <g:select name="type.id" from="${torch.PositionType.list()}" optionKey="id" value="${electedPositionInstance?.type?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="start"><g:message code="electedPosition.start.label" default="Start" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: electedPositionInstance, field: 'start', 'errors')}">
                                    <g:datePicker name="start" precision="day" value="${electedPositionInstance?.start}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="finish"><g:message code="electedPosition.finish.label" default="Finish" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: electedPositionInstance, field: 'finish', 'errors')}">
                                    <g:datePicker name="finish" precision="day" value="${electedPositionInstance?.finish}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="termEnd"><g:message code="electedPosition.termEnd.label" default="Term End" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: electedPositionInstance, field: 'termEnd', 'errors')}">
                                    <g:datePicker name="termEnd" precision="day" value="${electedPositionInstance?.termEnd}"  />
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
