package id.ac.ubaya.informatika.a160418055_todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import id.ac.ubaya.informatika.a160418055_todoapp.model.Todo
import id.ac.ubaya.informatika.a160418055_todoapp.model.TodoDatabase
import id.ac.ubaya.informatika.a160418055_todoapp.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListTodoViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    val todoLD = MutableLiveData<List<Todo>>()
    private var job = Job()

    fun refresh(){
        launch {
            val db = Room.databaseBuilder(getApplication(), TodoDatabase::class.java, "tododb").build()
            todoLD.value = db.todoDao().selectAllTodo()
        }
    }

    fun clearTask(id:Int){
        launch {
//            val db = Room.databaseBuilder(getApplication(), TodoDatabase::class.java, "tododb").build()
            val db = buildDB(getApplication())
            db.todoDao().update(id)
            todoLD.value = db.todoDao().selectAllTodo()
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}