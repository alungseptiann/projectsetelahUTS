package id.ac.ubaya.informatika.a160418055_todoapp.model

import androidx.room.*

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg todo:Todo)

    @Query("SELECT * FROM todo WHERE isDone = 0 ORDER BY priority DESC")
    suspend fun selectAllTodo():List<Todo>

    @Query("SELECT * FROM todo WHERE uuid = :id")
    suspend fun selectTodo(id:Int):Todo

    @Query("UPDATE todo SET title=:title, notes=:notes, priority=:priority WHERE uuid=:uuid")
    suspend fun update(title:String, notes:String, priority:Int, uuid:Int)

    @Query("UPDATE todo SET isDone = 1 WHERE uuid= :id")
    suspend fun update(id:Int)

    @Delete
    suspend fun deleteTodo(todo:Todo)
}