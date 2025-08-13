package com.example.e_library.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_library.common.Book
import com.example.e_library.common.BookCategory
import com.example.e_library.common.ResultState
import com.example.e_library.domain.repo.AllBookRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllBookViewModel @Inject constructor( private val bookRepo: AllBookRepo): ViewModel(){

    private val _state = MutableStateFlow<ResultState<String>>(ResultState.Idle)
    val state : StateFlow<ResultState<String>> = _state

    //in the ViewModel, you observe the ResultState from repository and update the ItemState:

    fun bringAllBook(){
        viewModelScope.launch{
            _state.value= ResultState.Loading
            try {
                val result=bookRepo.getAllBooks()
                _state.value= result as ResultState<String>
            }catch (e: Exception){
                _state.value= ResultState.Error("Error")
            }
        }
    }

    fun bringCategory(){
        viewModelScope.launch{
            bookRepo.getAllCategory().collect{ result ->

                when(result){
                    is ResultState.Loading -> {
                        _state.value = ItemState(isLoading = true)
                    }

                    is ResultState.Error -> {
                        _state.value = ItemState(error = result.exception.localizedMessage)
                    }

                    is ResultState.Success -> {

                        _state.value = ItemState(category = result.data)

                    }


                }

            }
        }
    }

    fun bringAllBooksByCategory(category: String){
        viewModelScope.launch{
            bookRepo.getAllBooksByCategory(category).collect { result ->
                when(result){

                    is ResultState.Loading -> {
                        _state.value = ItemState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _state.value = ItemState(items = result.data)

                    }

                    is ResultState.Error ->{
                        _state.value = ItemState(error = result.exception.localizedMessage)
                    }

                }
            }
        }
    }

}

//data class ItemState(
//    val isLoading : Boolean = false,
//    val error : String = "",
//    val items : List<Book> = emptyList(),
//    val category: List<BookCategory> = emptyList()
//)