package gr.padpad.marvellivedata.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MarvelTableDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertHero(marvelTable:  MarvelTable)

    @Query("DELETE FROM MarvelTable")
    fun deleteAll()

    @Query("SELECT isFavourite FROM MarvelTable WHERE id =:heroId")
    fun isHeroFavotite(heroId: Int): Boolean

    @Query("UPDATE MarvelTable SET isFavourite = NOT isFavourite WHERE id =:heroId")
    fun updateFavorite(heroId: Int)

    @Query("select * from MarvelTable")
    fun loadHeroes(): List<MarvelTable>
}