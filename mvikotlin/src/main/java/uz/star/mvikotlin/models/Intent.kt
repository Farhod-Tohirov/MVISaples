package uz.star.mvikotlin.models

import uz.star.data.PostData

sealed interface Intent {
    data object LoadPostList: Intent
    data class ReadButtonClick(val postData: PostData) : Intent
    data object PostClicked:Intent
}