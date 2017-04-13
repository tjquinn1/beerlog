package com.example

class Recipe {

    String name
    Float grainAmount
    String yeast
    float boilTime
    Float hopAmount
    float og
    float fg
    float ogTemp
    float fgTemp
    float fermTime
    Beer beer
    Hop hop

    static hasMany = [hop: Hop]
    static belongsTo = [ beer: Beer]

    static constraints = {
        beer nullable: true
    }

    String toString() {
        name
    }
 }