package com.poc.blog.service

import com.poc.blog.entity.Post
import com.poc.blog.exception.BaseException
import com.poc.blog.exception.BaseResponseCode
import com.poc.blog.repository.PostRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles
import java.util.*

@ActiveProfiles("dev")
@SpringBootTest
@ExtendWith(MockitoExtension::class) //junit과 mockito를 연동하기 위해 @ExtendWith를 명시해줍니다.
class blogServiceTest {
    @MockBean
    private lateinit var postRepository: PostRepository

    private lateinit var blogService: BlogService
    /*
    final val postRepository = mock(PostRepository::class.java)
    val blogService = BlogService(postRepository)
    */
    @Test
    fun test0(){

    }

    @BeforeEach
    fun init(){
        blogService = BlogService(postRepository)
    }

    @Test
    @DisplayName("없는 아이디 조회 시 익셉션 발생")
    fun readOnePostToFail(){
        val id:String = 1.toString() //Long = 1
        Mockito.`when`(postRepository.findById(id)).thenReturn(Optional.empty())
        var e = assertThrows<BaseException> {
            blogService.readPost(id)
        }
        assertEquals(BaseResponseCode.POST_NOT_FOUND, e.baseResponseCode)
    }

    @Test
    @DisplayName("존재하는 아이디 조회")
    fun  readOnePostToSuccess(){
        val id:String = 1.toString() //Long = 1
        Mockito.`when`(postRepository.findById(id)).thenReturn(Optional.of(Post(title = "title0", body = "body0")))
        var rs = blogService.readPost(id)

        Assertions.assertEquals("title0",rs.title)
        Assertions.assertEquals("body0",rs.body)
    }

    @Test
    @DisplayName("")
    fun readAll(){
        Mockito.`when`(postRepository.findAll()).thenReturn(listOf(Post(title="title1", body="body1"), Post(title="title2", body="body2")))
        val rs = postRepository.findAll()
        // postRepository의 findAll()이 몇 번 호출되었는지 검사
        // 만약 서비스에서 findAll() 호출 시 인자를 전달한다면 전돨되는 인자를 명시한다
        verify(postRepository, times(1)).findAll()
    }

    @Test
    fun createPost(){
        Mockito.`when`(postRepository.save(Mockito.any())).thenReturn(Post(title = "tt", body = "bb"))
        var result = blogService.createPost("t1","b1")
        assertEquals(result, true)

    }

    @Test
    @DisplayName("존재하지 않는 아이디 업데이트 시도 시 익셉션 발생")
    fun modifyFail(){
        val id:String = 1.toString()
        Mockito.`when`(postRepository.findById(id)).thenReturn(Optional.empty())

        val exception = assertThrows<BaseException> {
            blogService.updatePost(id, "title0", "body0")
        }

        assertEquals(BaseResponseCode.POST_NOT_FOUND, exception.baseResponseCode)
    }

    @Test
    @DisplayName("존재하는 아이디 업데이트")
    fun modifySuccess(){
        val id:String = 1.toString()
        Mockito.`when`(postRepository.findById(id)).thenReturn(Optional.of(Post(title="title1", body="body1")))

        val rst = blogService.updatePost(id, "title1", "body1")

        assertEquals(rst, true)
    }
}