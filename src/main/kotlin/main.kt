data class Post(
    val id: Int,
    val text: String = "",
    val likes: Likes = Likes(0),
    val comments: Comments = Comments(0),
    val date: Int,
    val canDeleted: Boolean?,
    val canEdited: Boolean?,
    val canPined: Boolean?,
    val attachments: Array<Attachment>?
)

data class Likes(
    val countLikes: Int
)
data class Comments(
    val countComments: Int
)

interface Attachment {
    val type: String
}

class PhotoAttachment (val photo: Photo) : Attachment {
    override val type: String = "photo"
}

class Photo(
    val idPhoto: Int,
    val text: String = "",
)

class VideoAttachment(val video: Video) : Attachment {
    override val type: String = "video"
}

class Video (
    val idVideo: Int,
    val title: String = "",
)

class LinkAttachment(val link: Link) : Attachment {
    override val type: String = "link"
}

class Link (
    val url: String,
    val title: String = "",
)

class NoteAttachment(val note: Note) : Attachment {
    override val type: String = "note"
}

class Note (
    val idNote: Int,
    val text: String = "",
)

class PollAttachment(val poll: Poll) : Attachment {
    override val type: String = "poll"
}

class Poll (
    val idPoll: Int,
    val question: String = "",
)


object WallService {
    private var posts = arrayOf<Post>()
    private var lastId = 0
    fun add(post: Post): Post {
        val newPost = post.copy(id = ++lastId, likes = post.likes.copy(), comments = post.comments.copy(), attachments = post.attachments?.copyOf())
        posts += newPost
        return newPost
    }

    fun update(newPost: Post): Boolean {
        for (index in posts.indices) {
            if (posts[index].id == newPost.id) {
                posts[index] = newPost.copy(likes = newPost.likes.copy(), comments = newPost.comments.copy(), attachments = newPost.attachments?.copyOf())
                return true
            }
        }
        return false
    }

    fun clear() {
        posts = emptyArray()
        lastId = 0
    }

    fun print() {
        for (post in posts) {
            print(post)
            println()
        }
        println()
    }
}

fun main() {
    WallService.add(Post(1, "Скоро Весна", Likes(12), Comments(1), 20, false, true, null, null))
    WallService.print()

    WallService.add(Post(2, "Скоро Лето", Likes(25), Comments(4), 21, false, true, null, null))
    WallService.print()

    WallService.update(Post(2, "Скоро Лето", Likes(58), Comments(8), 22, false, true, null, null))
    WallService.print()

    WallService.add(Post(3, "Скоро Зима", Likes(2), Comments(1), 25, false, true, null, null ))
    WallService.print()

    val photo1 = Photo( 1,"Добавлено фото 1")
    val photo2 = Photo( 2,"Добавлено фото 2")

    val video1 = Video(1, "Добавлено видео 1")
    val video2 = Video(2,"Добавлено видео 2")

    WallService.update(Post(2, "Скоро Зима", Likes(2), Comments(1), 25, false, true, null, arrayOf(PhotoAttachment(photo1))))
    WallService.print()


    WallService.update(Post(2, "Скоро Зима", Likes(2), Comments(1), 25, false, true, null, arrayOf(PhotoAttachment(photo2))))
    WallService.print()

    WallService.update(Post(3, "Скоро Зима", Likes(2), Comments(1), 25, false, true, null, arrayOf(VideoAttachment(video1))))
    WallService.print()

    WallService.update(Post(3, "Скоро Зима", Likes(2), Comments(1), 25, false, true, null, attachments = arrayOf(VideoAttachment(video2))))
    WallService.print()


}





