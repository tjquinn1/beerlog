package com.example

class Hop {

    String name
    String origin
    String hopType
    Float alpha
    Float beta


    static constraints = {
        name()
        origin()
        hopType inList: ['pellet', 'loose']
        alpha()
        beta()
    }


    String toString() {
        name
    }
}
