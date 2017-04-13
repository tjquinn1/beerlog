package beerlog

import com.example.Beer
import com.example.Hop

class BootStrap {

    def init = { servletContext ->
    new Beer(name: "Hitachino", style: "White Ale").save()
    new Hop(name: "Hallertau", origin: "German", alpha: "4.4", beta: "4.4", hopType: "pellet").save()
        new Hop(name: "Sasion", origin: "German", alpha: "4.4", beta: "4.4", hopType: "pellet").save()
    }
    def destroy = {
    }
}
