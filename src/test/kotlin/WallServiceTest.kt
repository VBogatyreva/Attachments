import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun add() {

        val post1 = WallService.add(Post(1, "Скоро Весна", Likes(12), Comments(1), 20, false, true, null, null))
        val post2 = WallService.add(Post(2, "Скоро Лето", Likes(32), Comments(15), 20, false, true, null, null))

        assertNotEquals(post1.id, post2.id)
    }

    @Test
    fun addPhoto() {

        val photo = Photo(1, "Добавлено фото 1")

        val post1 = WallService.add(Post(1, "Скоро Весна", Likes(12), Comments(1), 20, false, true, null, null))
        val post2 = WallService.add(Post(1, "Скоро Весна", Likes(12), Comments(1), 20, false, true, null, arrayOf(PhotoAttachment(photo))))

        assertNotEquals(post1.attachments, post2.attachments)
    }


    @Test
    fun updateExisting() {

        val service = WallService

        service.add(Post(1, "Скоро Весна", Likes(12), Comments(1), 20, false, true, null, null))
        service.add(Post(1, "Скоро Весна", Likes(32), Comments(1), 20, false, true, null, null))
        service.add(Post(1, "Скоро Весна", Likes(62), Comments(1), 20, false, true, null,null))

        val update = Post(1, "Скоро Весна", Likes(82), Comments(1), 20, false, true, null,null)

        val result = service.update(update)

        assertTrue(result)

    }

    @Test
    fun updateNotExisting() {

        val service = WallService

        service.add(Post(1, "Скоро Весна", Likes(12), Comments(1), 20, false, true, null, null))
        service.add(Post(2, "Скоро Лето", Likes(32), Comments(1), 23, false, true, null,null))
        service.add(Post(3, "Скоро Зима", Likes(2), Comments(1), 27, false, true, null, null))

        val update = Post(4, "Скоро ЧТО", Likes(0), Comments(0), 29, false, true, null,null)

        val result = service.update(update)

        assertFalse(result)



    }
}