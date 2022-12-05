package pt.ipp.estg.assistenteviagens.utils.searchButton

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {

    private val _searchWidgetStage: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetStage

    private val _searchTextStage: MutableState<String> = mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextStage

    fun updateSearchWidgetStage(newValue: SearchWidgetState) {
        _searchWidgetStage.value = newValue
    }

    fun updateSearchTextStage(newValue: String) {
        _searchTextStage.value = newValue
    }

}