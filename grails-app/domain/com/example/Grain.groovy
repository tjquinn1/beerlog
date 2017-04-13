package com.example

class Grain {

    String name
    String origin

    static belongsTo = Recipe
    static hasMany = [recipes : Recipe]

    static constraints = {
    }
}
