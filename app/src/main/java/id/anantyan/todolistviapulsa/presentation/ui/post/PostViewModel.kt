package id.anantyan.todolistviapulsa.presentation.ui.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.anantyan.todolistviapulsa.common.LiveEvent
import id.anantyan.todolistviapulsa.common.State
import id.anantyan.todolistviapulsa.data.remote.model.RequestModel
import id.anantyan.todolistviapulsa.data.remote.model.ResponseModel
import id.anantyan.todolistviapulsa.domain.TodoFetchUseCase
import id.anantyan.todolistviapulsa.domain.TodoPostUseCase
import id.anantyan.todolistviapulsa.domain.TodoPutUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Arya Rezza Anantya on 09/06/24.
 */
@HiltViewModel
class PostViewModel @Inject constructor(
    private val todoFetchUseCase: TodoFetchUseCase,
    private val todoPostUseCase: TodoPostUseCase,
    private val todoPutUseCase: TodoPutUseCase,
) : ViewModel() {
    private var _fetch: MutableLiveData<State<ResponseModel>> = MutableLiveData()
    private var _post: MutableLiveData<State<ResponseModel>> = MutableLiveData()
    private var _put: MutableLiveData<State<ResponseModel>> = MutableLiveData()

    val fetch: LiveData<State<ResponseModel>>
        get() = _fetch

    val post: LiveData<State<ResponseModel>>
        get() = _post
    val put: LiveData<State<ResponseModel>>
        get() = _put

    fun fetch(id: String?) {
        if (!id.isNullOrEmpty()) {
            viewModelScope.launch {
                _fetch.postValue(State.Loading())
                try {
                    _fetch.postValue(State.Success(todoFetchUseCase(id)))
                } catch (e: Exception) {
                    _fetch.postValue(State.Error(null, e.message.toString()))
                }
            }
        }
    }

    fun postput(id: String?, field: RequestModel) {
        if (id.isNullOrEmpty()) {
            post(field)
        } else {
            put(id, field)
        }
    }

    private fun post(field: RequestModel) {
        viewModelScope.launch {
            _post.postValue(State.Loading())
            try {
                _post.postValue(State.Success(todoPostUseCase(field)))
            } catch (e: Exception) {
                _post.postValue(State.Error(null, e.message.toString()))
            }
        }
    }

    private fun put(id: String, field: RequestModel) {
        viewModelScope.launch {
            _put.postValue(State.Loading())
            try {
                _put.postValue(State.Success(todoPutUseCase(id, field)))
            } catch (e: Exception) {
                _put.postValue(State.Error(null, e.message.toString()))
            }
        }
    }
}