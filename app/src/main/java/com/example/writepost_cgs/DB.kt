package com.example.writepost_cgs

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.*
import androidx.room.OnConflictStrategy
import java.io.ByteArrayOutputStream

@Entity(tableName = "PostHistory")
class PostHistory(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "content")
    val content: String,
    @ColumnInfo(name = "Image")
    val image: ByteArray
) {
    //    constructor(title:String, content: String, image: Uri): this(0,title,content,image)
}

@Dao
interface PostHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(post: PostHistory)

    @Query("SELECT * FROM PostHistory")
    fun getAll(): List<PostHistory>
}

@Database(entities = [PostHistory::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppDatabases : RoomDatabase() {
    abstract fun PostHistoryDao(): PostHistoryDao
}

class Converter {
    @TypeConverter
    fun toByteArray(bitmap: Bitmap): ByteArray {
        val outpuStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outpuStream)
        return outpuStream.toByteArray()
    }

    @TypeConverter
    fun toBitmap(bytes: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }
}

class PostAmount {
    companion object {
        var postAmount: Int = 0

        fun addPost() {
            postAmount++
        }
    }
}