package com.example.e_library.common

data class Book(
    //var id: Int ,
    var name: String = "",
    var description: String = "",
    var author: String = "",
    var category: String = "",
    var imageUrl: String = "",
    var pdfUrl: String = ""
)