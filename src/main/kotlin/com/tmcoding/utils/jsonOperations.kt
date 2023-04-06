package com.tmcoding.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.tmcoding.data.Animal
import java.io.*

fun appendToFile(path: String, newAnimal: Animal){
    val gson = GsonBuilder().setPrettyPrinting().create()

    // Lecture du fichier JSON
    val inputFile = File(path)
    val outputFile = File("animals_updated.json")

    val animalListType = object : TypeToken<List<Animal>>() {}.type
    val animalList: MutableList<Animal> =
        if (inputFile.exists()) {
            BufferedReader(FileReader(inputFile)).use { reader ->
                gson.fromJson(reader, animalListType)
            }
        } else {
            mutableListOf()
        }

    // Ajout d'un nouvel élément à la liste
    animalList.add(newAnimal)

    // Écriture de la liste mise à jour dans un nouveau fichier JSON
    BufferedWriter(FileWriter(outputFile)).use { writer ->
        gson.toJson(animalList, writer)
    }

    // Remplacement de l'ancien fichier par le nouveau fichier
    inputFile.delete()
    outputFile.renameTo(inputFile)
}


fun selectAnElementRandomly(path: String): Animal {
    val gson = Gson()

    // Lecture du fichier JSON
    val inputFile = File(path)
    val animalListType = object : TypeToken<List<Animal>>() {}.type
    val animalList: List<Animal> = if (inputFile.exists()) {
        inputFile.bufferedReader().use { reader ->
            gson.fromJson(reader, animalListType)
        }
    } else {
        emptyList()
    }

    println(animalList)

    // return élément Animal aléatoire ou Animal vide si null

    return animalList.randomOrNull() ?: Animal("", "", "")
}
