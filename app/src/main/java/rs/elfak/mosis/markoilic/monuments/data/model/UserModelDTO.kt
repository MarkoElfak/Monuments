package rs.elfak.mosis.markoilic.monuments.data.model

data class UserModelDTO(
    var username: String = "",
    var email: String = "",
    var dateOfBirth: String = "",
    var imageURL: String = "",
    var friendList: List<String> = emptyList()
) {
    constructor(user: UserModel) : this(
        user.username,
        user.email,
        user.dateOfBirth,
        user.imageURL,
        user.friendList
    )
}
