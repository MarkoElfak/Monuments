package rs.elfak.mosis.markoilic.monuments.data.model

data class RegisterObject(
    var username: String = "",
    var email: String = "",
    var dateOfBirth: String = "",
    var password: String = "",
    var imageURL: String = "https://firebasestorage.googleapis.com/v0/b/monuments-7e49d.appspot.com/o/profilePictures%2Fdefault.jpg?alt=media&token=e40e4f04-880a-492a-9684-da056eb53ddf",
    var friendList: List<String> = emptyList()
)
