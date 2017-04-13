package com.example

import grails.test.mixin.*
import spock.lang.*

@TestFor(RecipeController)
@Mock(Recipe)
class RecipeControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        assert false, "TODO: Provide a populateValidParams() implementation for this generated test suite"
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.recipeList
            model.recipeCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.recipe!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def recipe = new Recipe()
            recipe.validate()
            controller.save(recipe)

        then:"The create view is rendered again with the correct model"
            model.recipe!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            recipe = new Recipe(params)

            controller.save(recipe)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/recipe/show/1'
            controller.flash.message != null
            Recipe.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def recipe = new Recipe(params)
            controller.show(recipe)

        then:"A model is populated containing the domain instance"
            model.recipe == recipe
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def recipe = new Recipe(params)
            controller.edit(recipe)

        then:"A model is populated containing the domain instance"
            model.recipe == recipe
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/recipe/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def recipe = new Recipe()
            recipe.validate()
            controller.update(recipe)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.recipe == recipe

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            recipe = new Recipe(params).save(flush: true)
            controller.update(recipe)

        then:"A redirect is issued to the show action"
            recipe != null
            response.redirectedUrl == "/recipe/show/$recipe.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/recipe/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def recipe = new Recipe(params).save(flush: true)

        then:"It exists"
            Recipe.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(recipe)

        then:"The instance is deleted"
            Recipe.count() == 0
            response.redirectedUrl == '/recipe/index'
            flash.message != null
    }
}
