package com.tmcoding.plugins

import com.tmcoding.routes.randomAnimal
import com.tmcoding.routes.uploadNewAnimal
import io.ktor.server.routing.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    
    routing {
        randomAnimal()
        // Static plugin. Try to access `/static/index.html`
        static {
            resources("static")
        }
    }
    routing {
        uploadNewAnimal()
    }
}
