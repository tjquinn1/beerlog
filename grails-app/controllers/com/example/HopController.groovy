package com.example

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class HopController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Hop.list(params), model:[hopCount: Hop.count()]
    }

    def show(Hop hop) {
        respond hop
    }

    def create() {
        respond new Hop(params)
    }

    @Transactional
    def save(Hop hop) {
        if (hop == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (hop.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond hop.errors, view:'create'
            return
        }

        hop.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'hop.label', default: 'Hop'), hop.id])
                redirect hop
            }
            '*' { respond hop, [status: CREATED] }
        }
    }

    def edit(Hop hop) {
        respond hop
    }

    @Transactional
    def update(Hop hop) {
        if (hop == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (hop.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond hop.errors, view:'edit'
            return
        }

        hop.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'hop.label', default: 'Hop'), hop.id])
                redirect hop
            }
            '*'{ respond hop, [status: OK] }
        }
    }

    @Transactional
    def delete(Hop hop) {

        if (hop == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        hop.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'hop.label', default: 'Hop'), hop.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'hop.label', default: 'Hop'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
