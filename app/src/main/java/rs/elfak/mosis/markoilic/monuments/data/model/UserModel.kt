package rs.elfak.mosis.markoilic.monuments.data.model

data class UserModel(
    var username: String = "",
    var email: String = "",
    var dateOfBirth: String = "",
    var imageURL: String = "",
    var friendList: List<String> = emptyList()
)
