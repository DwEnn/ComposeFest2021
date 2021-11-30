package com.codelabs.state.examples

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HelloCodelabViewModel: ViewModel() {

    // Livedata holds state which is observed by the UI
    // state flows down from viewModel
    private val _name = MutableLiveData("")
    val name : LiveData<String>
        get() = _name

    // onNameChanged is an event we're defining that the UI can invoke
    // (events flow up from UI)
    fun onNameChanged(newName: String) {
        _name.value = newName
    }
}
