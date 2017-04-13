package com.example


class Beer {

    String name
    String style


    String toString() {
        name
    }

    static hasMany = [recipe : Recipe]


    static constraints = {
        recipe nullable: true
    }
}
