
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StockDao {
    @Query("SELECT * FROM portfolio WHERE userId = :userId")
    fun getPortfolio(userId: Int): Flow<List<Portfolio>>

    @Insert
    suspend fun insertPortfolio(portfolio: Portfolio)

    @Query("DELETE FROM portfolio WHERE id = :id")
    suspend fun deletePortfolio(id: Int)
}
