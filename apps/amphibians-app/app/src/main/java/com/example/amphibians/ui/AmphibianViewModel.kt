/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.amphibians.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amphibians.network.Amphibian
import com.example.amphibians.network.AmphibianApi
import kotlinx.coroutines.launch

enum class AmphibianApiStatus {LOADING, ERROR, DONE}

class AmphibianViewModel : ViewModel() {

    // TODO: Create properties to represent MutableLiveData and LiveData for the API status
    private val _status = MutableLiveData<AmphibianApiStatus>()
    val status: LiveData<AmphibianApiStatus>
        get() = _status

    // TODO: Create properties to represent MutableLiveData and LiveData for a list of amphibian objects
    private val amphibian_list = MutableLiveData<List<Amphibian>>()
    val amphibian1: LiveData<List<Amphibian>>
        get() = amphibian_list

    // TODO: Create properties to represent MutableLiveData and LiveData for a single amphibian object.
    //  This will be used to display the details of an amphibian when a list item is clicked
    private val amphibian_list2 = MutableLiveData<Amphibian>()
    val amphibian2: LiveData<Amphibian>
        get() = amphibian_list2

    // TODO: Create a function that gets a list of amphibians from the API service and sets the
    //  status via a Coroutine
    fun getAmphibianList() {
        viewModelScope.launch {
            _status.value = AmphibianApiStatus.LOADING

            try {
                val result = AmphibianApi.retrofitService.getAmphibians()
                amphibian_list.value = result
                _status.value = AmphibianApiStatus.DONE
            } catch (e: Exception) {
                amphibian_list.value = emptyList()
                _status.value = AmphibianApiStatus.ERROR
            }
        }
    }

    // TODO: Implement the onAmphibianClicked() method to set the _amphibian property
    fun onAmphibianClicked(amphibian: Amphibian) {
        amphibian_list2.value = amphibian
    }
}
