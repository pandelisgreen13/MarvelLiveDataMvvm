package gr.padpad.marvellivedata.database.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MarvelTable")
class MarvelTable(
        @PrimaryKey
        var id: Int,
        var isFavourite: Boolean = false
)