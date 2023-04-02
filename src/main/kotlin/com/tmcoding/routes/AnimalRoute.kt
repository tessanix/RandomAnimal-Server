package com.tmcoding.routes

import com.tmcoding.data.Animal
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private const val BASE_URL = "http://192.168.0.11:8100"

private val animals = listOf(
    Animal("Henry", "Henry is docile and very friendly.", "$BASE_URL/animals/tiger.webp"),
    Animal("Jack", "Do not look at Jack in eyes too longer...", "$BASE_URL/animals/bear.webp"),
    Animal("Corri", "She is so cute! I love Corri.", "$BASE_URL/animals/ladybug.webp"),
    Animal("Garfield", "He is the clown of the team!", "$BASE_URL/animals/puffer-fish.webp"),
    Animal("Timmy", "This dude is very intelligent", "$BASE_URL/animals/elephant.webp")
)

fun Route.randomAnimal(){
    get("/randomanimal") {
        call.respond(
            HttpStatusCode.OK,
            animals.random()
        )
    }
}