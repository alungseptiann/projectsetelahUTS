package id.ac.ubaya.informatika.a160418055_todoapp.util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import id.ac.ubaya.informatika.a160418055_todoapp.model.TodoDatabase

val DB_NAME = "tododb"

fun buildDB(context:Context):TodoDatabase{
    val db = Room.databaseBuilder(context,
    TodoDatabase::class.java, DB_NAME)
        .addMigrations(MIGRATION_2_3)
        .build()
    return db
}

val MIGRATION_2_3 = object: Migration(2,3){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE todo ADD COLUMN isDone INTEGER DEFAULT 0 NOT NULL")
    }
}