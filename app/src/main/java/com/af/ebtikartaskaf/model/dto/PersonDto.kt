package com.af.ebtikartaskaf.model.dto

import java.io.Serializable
//Asem Alfaqeh
//shared class ////////
class PersonDto : Serializable {

    var personImagePath: String? = null
    var personName: String? = null

    override fun toString(): String {
        return "PersonDto{" +
                "personImagePath='" + personImagePath + '\'' +
                ", personName='" + personName + '\'' +
                '}'
    }
}