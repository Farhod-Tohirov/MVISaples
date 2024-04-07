package uz.star.data

import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

object DataRepository {

    suspend fun getPostList(): List<PostData> {
        delay(2.seconds)
        val result = mutableListOf<PostData>()
        for (i in 0..10) result.add(PostData(i, "Title $i", "Subtitle $i", 0))
        return result
    }
}