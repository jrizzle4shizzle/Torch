

<%@ page import="torch.Committee" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'committee.label', default: 'Committee')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${committeeInstance}">
            <div class="errors">
                <g:renderErrors bean="${committeeInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${committeeInstance?.id}" />
                <g:hiddenField name="version" value="${committeeInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name"><g:message code="committee.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: committeeInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${committeeInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="chair"><g:message code="committee.chair.label" default="Chair" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: committeeInstance, field: 'chair', 'errors')}">
                                    <g:select name="chair.id" from="${torch.Member.list()}" optionKey="id" value="${committeeInstance?.chair?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="members"><g:message code="committee.members.label" default="Members" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: committeeInstance, field: 'members', 'errors')}">
                                    <g:select name="members" from="${torch.Member.list()}" multiple="yes" optionKey="id" size="5" value="${committeeInstance?.members*.id}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
