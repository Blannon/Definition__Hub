package com.blannon_network.Respond

import com.blannon_network.Respond.Definition

data class Meaning(
    val antonyms: List<Any>,
    val definitions: List<Definition>,
    val partOfSpeech: String,
    val synonyms: List<Any>
)