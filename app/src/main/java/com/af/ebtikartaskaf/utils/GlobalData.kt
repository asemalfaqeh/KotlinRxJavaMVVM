package com.af.ebtikartaskaf.utils

import com.af.ebtikartaskaf.model.dto.PersonDto
import java.util.*

// save data //
object GlobalData {
    const val PERSON_DTO = "person_dto" // person_dto key //
    var personDtos: ArrayList<PersonDto>? = null
}