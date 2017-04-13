package com.example

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class GrainController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Grain.list(params), model:[grainCount: Grain.count()]
    }

    def show(Grain grain) {
        respond grain
    }

    def create() {
        respond new Grain(params)
    }

    @Transactional
    def save(Grain grain) {
        if (grain == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (grain.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond grain.errors, view:'create'
            return
        }

        grain.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'grain.label', default: 'Grain'), grain.id])
                redirect grain
            }
            '*' { respond grain, [status: CREATED] }
        }
    }

    def edit(Grain grain) {
        respond grain
    }

    @Transactional
    def update(Grain grain) {
        if (grain == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (grain.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond grain.errors, view:'edit'
            return
        }

        grain.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'grain.label', default: 'Grain'), grain.id])
                redirect grain
            }
            '*'{ respond grain, [status: OK] }
        }
    }

    @Transactional
    def delete(Grain grain) {

        if (grain == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        grain.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'grain.label', default: 'Grain'), grain.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'grain.label', default: 'Grain'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
