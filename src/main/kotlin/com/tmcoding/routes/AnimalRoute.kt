package com.tmcoding.routes

import com.tmcoding.data.Animal
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.tmcoding.utils.appendToFile
import com.tmcoding.utils.selectAnElementRandomly
import java.io.*

//
//private const val BASE_URL = "http://192.168.0.11:8850"
//
//private val animals = listOf(
//    Animal("Henry", "Henry is docile and very friendly.", "$BASE_URL/animalImages/tiger.webp"),
//    Animal("Jack", "Do not look at Jack in eyes too longer...", "$BASE_URL/animalImages/bear.webp"),
//    Animal("Corri", "She is so cute! I love Corri.", "$BASE_URL/animalImages/ladybug.webp"),
//    Animal("Garfield", "He is the clown of the team!", "$BASE_URL/animalImages/puffer-fish.webp"),
//    Animal("Timmy", "This dude is very intelligent", "$BASE_URL/animalImages/elephant.webp")
//)

fun Route.randomAnimal(){
    get("/randomanimal") {
        call.respond(
            HttpStatusCode.OK,
            selectAnElementRandomly("animals.json")
        )
    }
}


fun PartData.FileItem.save(path: String, fileName: String): String {
    val fileBytes = streamProvider().readBytes()
    val folder = File(path)
    folder.mkdirs()
    File("$path$fileName").writeBytes(fileBytes)
    return fileName
}

fun Route.uploadNewAnimal() {
//    var name = ""
//    var desc = ""
//    var url = ""

    val newAnimal = Animal()

    post("/newAnimal") {
        val multipart = call.receiveMultipart()
        multipart.forEachPart { part ->
            when (part) {
                is PartData.FormItem -> {
                    if (part.name == "name") {
                        newAnimal.name = part.value
                        println("multipart name: " + part.value)
                    } else if (part.name == "description") {
                        newAnimal.description = part.value
                        println("multipart desc: " + part.value)

                    }
                }

                is PartData.FileItem -> {
                    if (part.name == "image") {
                        newAnimal.imageUrl = part.originalFileName.toString()
                        println("multipart filename: " + newAnimal.imageUrl)

                        part.save("src/main/resources/static/animalImages/", newAnimal.imageUrl!!)
                    }
                }

                else -> Unit
            }
        }
        call.respond(HttpStatusCode.OK)
        appendToFile("animals.json", newAnimal)
    }

}