package id.anantyan.todolistviapulsa.presentation.ui.list;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import id.anantyan.todolistviapulsa.common.LiveEvent
import id.anantyan.todolistviapulsa.common.State
import id.anantyan.todolistviapulsa.data.remote.model.ResponseModel
import id.anantyan.todolistviapulsa.domain.TodoDeleteUseCase
import id.anantyan.todolistviapulsa.domain.TodoFetchAllUseCase
import id.anantyan.todolistviapulsa.domain.TodoFetchUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

@HiltViewModel
class ListViewModel @Inject constructor(
    private val todoFetchAllUseCase: TodoFetchAllUseCase,
    private val todoFetchUseCase: TodoFetchUseCase,
    private val todoDeleteUseCase: TodoDeleteUseCase
) : ViewModel() {
    private var _fetchAll: MutableLiveData<State<List<ResponseModel>>> = MutableLiveData()
    private var _fetch: MutableLiveData<State<ResponseModel>> = MutableLiveData()
    private var _delete: MutableLiveData<State<ResponseModel>> = MutableLiveData()

    val fetchAll: LiveData<State<List<ResponseModel>>>
        get() = _fetchAll

    val fetch: LiveData<State<ResponseModel>>
        get() = _fetch

    val delete: LiveData<State<ResponseModel>>
        get() = _delete

    fun fetchAll() {
        viewModelScope.launch {
            _fetchAll.postValue(State.Loading())
            try {
                val value = todoFetchAllUseCase.invoke()
                _fetchAll.postValue(State.Success(value))
            } catch (e: Exception) {
                _fetchAll.postValue(State.Error(null, e.message.toString()))
            }
        }
    }

    fun fetch(id: String) {
        viewModelScope.launch {
            _fetch.postValue(State.Loading())
            try {
                val value = todoFetchUseCase.invoke(id)
                _fetch.postValue(State.Success(value))
            } catch (e: Exception) {
                _fetch.postValue(State.Error(null, e.message.toString()))
            }
        }
    }

    fun delete(id: String) {
        viewModelScope.launch {
            _delete.postValue(State.Loading())
            try {
                val value = todoDeleteUseCase.invoke(id)
                _delete.postValue(State.Success(value))
            } catch (e: Exception) {
                _delete.postValue(State.Error(null, e.message.toString()))
            }
        }
    }
}