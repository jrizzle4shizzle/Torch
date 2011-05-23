
<%@ page import="torch.Member" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'member.label', default: 'Member')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="member.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: memberInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="member.aacoRidingDatabase.label" default="Aaco Riding Database" /></td>
                            
                            <td valign="top" class="value"><g:formatBoolean boolean="${memberInstance?.aacoRidingDatabase}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="member.administrative.label" default="Administrative" /></td>
                            
                            <td valign="top" class="value"><g:formatBoolean boolean="${memberInstance?.administrative}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="member.badgeNumber.label" default="Badge Number" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: memberInstance, field: "badgeNumber")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="member.birthDay.label" default="Birth Day" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: memberInstance, field: "birthDay")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="member.driversLicenseClass.label" default="Drivers License Class" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: memberInstance, field: "driversLicenseClass")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="member.driversLicenseExpiration.label" default="Drivers License Expiration" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${memberInstance?.driversLicenseExpiration}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="member.driversLicenseNumber.label" default="Drivers License Number" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: memberInstance, field: "driversLicenseNumber")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="member.driversLicenseState.label" default="Drivers License State" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: memberInstance, field: "driversLicenseState")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="member.dues.label" default="Dues" /></td>
                            
                            <td valign="top" class="value"><g:formatBoolean boolean="${memberInstance?.dues}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="member.firstName.label" default="First Name" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: memberInstance, field: "firstName")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="member.homeAddressCity.label" default="Home Address City" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: memberInstance, field: "homeAddressCity")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="member.homeAddressState.label" default="Home Address State" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: memberInstance, field: "homeAddressState")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="member.homeAddressStreet.label" default="Home Address Street" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: memberInstance, field: "homeAddressStreet")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="member.homeAddressZip.label" default="Home Address Zip" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: memberInstance, field: "homeAddressZip")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="member.homePhoneNumber.label" default="Home Phone Number" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: memberInstance, field: "homePhoneNumber")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="member.lastName.label" default="Last Name" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: memberInstance, field: "lastName")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="member.lifeInsurance.label" default="Life Insurance" /></td>
                            
                            <td valign="top" class="value"><g:formatBoolean boolean="${memberInstance?.lifeInsurance}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="member.membershipDate.label" default="Membership Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${memberInstance?.membershipDate}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="member.membershipType.label" default="Membership Type" /></td>
                            
                            <td valign="top" class="value"><g:link controller="membershipType" action="show" id="${memberInstance?.membershipType?.id}">${memberInstance?.membershipType?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="member.mobinePhoneNumber.label" default="Mobine Phone Number" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: memberInstance, field: "mobinePhoneNumber")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="member.notes.label" default="Notes" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: memberInstance, field: "notes")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="member.race.label" default="Race" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: memberInstance, field: "race")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="member.rank.label" default="Rank" /></td>
                            
                            <td valign="top" class="value"><g:link controller="rank" action="show" id="${memberInstance?.rank?.id}">${memberInstance?.rank?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="member.sex.label" default="Sex" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: memberInstance, field: "sex")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="member.ssn.label" default="Ssn" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: memberInstance, field: "ssn")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="member.userName.label" default="User Name" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: memberInstance, field: "userName")}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${memberInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>