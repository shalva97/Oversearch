package data.models

data class Profile(
    val player: Player,
    val endorsement: Int,
    val endorsementIcon: String,
//    val ratings: List<Rating>, // TODO
    val gamesWon: Int,
    val gamesLost: Int,
    val private: Boolean,
)

