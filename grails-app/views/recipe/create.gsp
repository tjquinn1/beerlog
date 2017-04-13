<%@ page import="com.example.Beer" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'recipe.label', default: 'Recipe')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-recipe" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-recipe" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.recipe}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.recipe}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form action="save" controller="recipe" method="post">
                <fieldset class="form">
                    Name:
                    <g:field type="text" name="name"/>
                    Grain Amount:
                    <g:field type="text" name="grainAmount"/>
                    Yeast:
                    <g:field type="text" name="yeast"/>
                    Boil Time:
                    <g:field type="text" name="boilTime"/>
                    Hop Amount:
                    <g:field type="text" name="hopAmount"/>
                    OG:
                    <g:field type="text" name="og"/>
                    FG:
                    <g:field type="text" name="fg"/>
                    OG Temp:
                    <g:field type="text" name="ogTemp"/>
                    FG Temp:
                    <g:field type="text" name="fgTemp"/>
                    Ferment Time:
                    <g:field type="text" name="fermTime"/>
                    Hops:
                    <g:select name="hop.id" from="${com.example.Hop.list()}" optionKey="id"/>

                    <g:hiddenField name="beer.id" value="${params.beerid}"/>


                </fieldset>

                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>