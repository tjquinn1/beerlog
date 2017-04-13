package com.example

class HomeController {

    def index() {
        List<Beer> allBeers = Beer.list()
        [beers:allBeers]

    }


}
