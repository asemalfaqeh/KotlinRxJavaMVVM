package com.af.ebtikartaskaf.ui.fragments.personinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.af.ebtikartaskaf.model.dto.PersonDto

class PersonInfoViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val personDtoMutableLiveData = MutableLiveData<PersonDto>()
    fun getPersonDtoViewModel(personDto: PersonDto): LiveData<PersonDto> {
        personDtoMutableLiveData.postValue(personDto)
        return personDtoMutableLiveData
    }
}