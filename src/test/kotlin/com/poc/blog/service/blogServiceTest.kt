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
    fun readOnePostToFail(){
        val id:String = 1.toString() //Long = 1
        Mockito.`when`(postRepository.findById(id)).thenReturn(Optional.empty())
        var e = assertThrows<BaseException> {
            blogService.readPost(id)
        }
        assertEquals(BaseResponseCode.POST_NOT_FOUND, e.baseResponseCode)
    }

    @Test
    fun  readOnePostToSuccess(){
        val id:String = 1.toString() //Long = 1
        Mockito.`when`(postRepository.findById(id)).thenReturn(Optional.empty())
        var rs = blogService.readPost(id)

        Assertions.assertEquals("title",rs.title)
        Assertions.assertEquals("body",rs.body)
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
        var result = blogService.createPost("t1","b1")
        assertEquals(result, true)

    }

    @Test
    fun modifyFail(){
        val id:Long = 1
    }

    @Test
    fun modifySuccess(){
        val id:Long = 1
    }
}