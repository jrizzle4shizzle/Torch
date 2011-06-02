

<%@ page import="torch.Member" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'member.label', default: 'Member')}" />
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
            <g:hasErrors bean="${memberInstance}">
            <div class="errors">
                <g:renderErrors bean="${memberInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${memberInstance?.id}" />
                <g:hiddenField name="version" value="${memberInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="login"><g:message code="member.login.label" default="Login" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: memberInstance, field: 'login', 'errors')}">
                                    <g:textField name="login" value="${memberInstance?.login}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="password"><g:message code="member.password.label" default="Password" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: memberInstance, field: 'password', 'errors')}">
                                    <g:passwordField name="password" value="${memberInstance?.password}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="lastName"><g:message code="member.lastName.label" default="Last Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: memberInstance, field: 'lastName', 'errors')}">
                                    <g:textField name="lastName" value="${memberInstance?.lastName}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="firstName"><g:message code="member.firstName.label" default="First Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: memberInstance, field: 'firstName', 'errors')}">
                                    <g:textField name="firstName" value="${memberInstance?.firstName}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="homeAddressStreet"><g:message code="member.homeAddressStreet.label" default="Home Address Street" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: memberInstance, field: 'homeAddressStreet', 'errors')}">
                                    <g:textField name="homeAddressStreet" value="${memberInstance?.homeAddressStreet}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="homeAddressCity"><g:message code="member.homeAddressCity.label" default="Home Address City" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: memberInstance, field: 'homeAddressCity', 'errors')}">
                                    <g:textField name="homeAddressCity" value="${memberInstance?.homeAddressCity}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="homeAddressState"><g:message code="member.homeAddressState.label" default="Home Address State" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: memberInstance, field: 'homeAddressState', 'errors')}">
                                    <g:textField name="homeAddressState" value="${memberInstance?.homeAddressState}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="homeAddressZip"><g:message code="member.homeAddressZip.label" default="Home Address Zip" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: memberInstance, field: 'homeAddressZip', 'errors')}">
                                    <g:textField name="homeAddressZip" value="${memberInstance?.homeAddressZip}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="homePhoneNumber"><g:message code="member.homePhoneNumber.label" default="Home Phone Number" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: memberInstance, field: 'homePhoneNumber', 'errors')}">
                                    <g:textField name="homePhoneNumber" value="${memberInstance?.homePhoneNumber}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="mobilePhoneNumber"><g:message code="member.mobilePhoneNumber.label" default="Mobile Phone Number" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: memberInstance, field: 'mobilePhoneNumber', 'errors')}">
                                    <g:textField name="mobilePhoneNumber" value="${memberInstance?.mobilePhoneNumber}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="notes"><g:message code="member.notes.label" default="Notes" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: memberInstance, field: 'notes', 'errors')}">
                                    <g:textField name="notes" value="${memberInstance?.notes}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="rank"><g:message code="member.rank.label" default="Rank" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: memberInstance, field: 'rank', 'errors')}">
                                    <g:select name="rank.id" from="${torch.Rank.list()}" optionKey="id" value="${memberInstance?.rank?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="membershipType"><g:message code="member.membershipType.label" default="Membership Type" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: memberInstance, field: 'membershipType', 'errors')}">
                                    <g:select name="membershipType.id" from="${torch.MembershipType.list()}" optionKey="id" value="${memberInstance?.membershipType?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="committees"><g:message code="member.committees.label" default="Committees" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: memberInstance, field: 'committees', 'errors')}">
                                    <g:select name="committees" from="${torch.Committee.list()}" multiple="yes" optionKey="id" size="5" value="${memberInstance?.committees*.id}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="birthDay"><g:message code="member.birthDay.label" default="Birth Day" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: memberInstance, field: 'birthDay', 'errors')}">
                                    <g:textField name="birthDay" value="${memberInstance?.birthDay}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="aacoRidingDatabase"><g:message code="member.aacoRidingDatabase.label" default="Aaco Riding Database" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: memberInstance, field: 'aacoRidingDatabase', 'errors')}">
                                    <g:checkBox name="aacoRidingDatabase" value="${memberInstance?.aacoRidingDatabase}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="badgeNumber"><g:message code="member.badgeNumber.label" default="Badge Number" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: memberInstance, field: 'badgeNumber', 'errors')}">
                                    <g:textField name="badgeNumber" value="${fieldValue(bean: memberInstance, field: 'badgeNumber')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="dues"><g:message code="member.dues.label" default="Dues" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: memberInstance, field: 'dues', 'errors')}">
                                    <g:checkBox name="dues" value="${memberInstance?.dues}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="lifeInsurance"><g:message code="member.lifeInsurance.label" default="Life Insurance" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: memberInstance, field: 'lifeInsurance', 'errors')}">
                                    <g:checkBox name="lifeInsurance" value="${memberInstance?.lifeInsurance}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="ssn"><g:message code="member.ssn.label" default="Ssn" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: memberInstance, field: 'ssn', 'errors')}">
                                    <g:textField name="ssn" value="${memberInstance?.ssn}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="sex"><g:message code="member.sex.label" default="Sex" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: memberInstance, field: 'sex', 'errors')}">
                                    <g:textField name="sex" value="${memberInstance?.sex}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="race"><g:message code="member.race.label" default="Race" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: memberInstance, field: 'race', 'errors')}">
                                    <g:textField name="race" value="${memberInstance?.race}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="driversLicenseNumber"><g:message code="member.driversLicenseNumber.label" default="Drivers License Number" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: memberInstance, field: 'driversLicenseNumber', 'errors')}">
                                    <g:textField name="driversLicenseNumber" value="${memberInstance?.driversLicenseNumber}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="driversLicenseClass"><g:message code="member.driversLicenseClass.label" default="Drivers License Class" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: memberInstance, field: 'driversLicenseClass', 'errors')}">
                                    <g:textField name="driversLicenseClass" value="${memberInstance?.driversLicenseClass}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="driversLicenseState"><g:message code="member.driversLicenseState.label" default="Drivers License State" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: memberInstance, field: 'driversLicenseState', 'errors')}">
                                    <g:textField name="driversLicenseState" value="${memberInstance?.driversLicenseState}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="driversLicenseExpiration"><g:message code="member.driversLicenseExpiration.label" default="Drivers License Expiration" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: memberInstance, field: 'driversLicenseExpiration', 'errors')}">
                                    <g:datePicker name="driversLicenseExpiration" precision="day" value="${memberInstance?.driversLicenseExpiration}" default="none" noSelection="['': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="active"><g:message code="member.active.label" default="Active" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: memberInstance, field: 'active', 'errors')}">
                                    <g:checkBox name="active" value="${memberInstance?.active}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="administrative"><g:message code="member.administrative.label" default="Administrative" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: memberInstance, field: 'administrative', 'errors')}">
                                    <g:checkBox name="administrative" value="${memberInstance?.administrative}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="membershipDate"><g:message code="member.membershipDate.label" default="Membership Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: memberInstance, field: 'membershipDate', 'errors')}">
                                    <g:datePicker name="membershipDate" precision="day" value="${memberInstance?.membershipDate}" default="none" noSelection="['': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="role"><g:message code="member.role.label" default="Role" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: memberInstance, field: 'role', 'errors')}">
                                    <g:select name="role" from="${memberInstance.constraints.role.inList}" value="${memberInstance?.role}" valueMessagePrefix="member.role"  />
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
