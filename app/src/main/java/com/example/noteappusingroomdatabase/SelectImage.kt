package com.example.noteappusingroomdatabase

interface SelectImage {

    //choose Image from gallery and set it on Imageview
    fun registerResult()

    //Access to the gallery by Intent
    fun pickImage()
}