package com.example.e_library.presentation


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_library.common.Book
import com.example.e_library.common.BookCategory
import com.example.e_library.common.ResultState
import com.example.e_library.domain.repo.AllBookRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllBookViewModel @Inject constructor(val bookRepo: AllBookRepo): ViewModel(){

    private val _state : MutableState<ItemState> = mutableStateOf(ItemState())
    val state : MutableState<ItemState> = _state

    //in the ViewModel, you observe the ResultState from repository and update the ItemState:

    fun bringAllBook(){
        viewModelScope.launch{

            bookRepo.getAllBooks().collect{ result ->

                when(result){

                    is ResultState.Loading -> {
                        _state.value = ItemState(isLoading = true)
                    }

                    is ResultState.Error -> {
                        _state.value = ItemState(error = result.exception.localizedMessage)
                    }

                    is ResultState.Success -> {
                        _state.value = ItemState(items = result.data)
                    }
                }
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

data class ItemState(
    val isLoading : Boolean = false,
    val error : String = "",
    val items : List<Book> = emptyList(),
    val category: List<BookCategory> = emptyList()
)