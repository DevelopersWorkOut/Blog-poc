package com.poc.blog.service

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("dev")
@SpringBootTest
@ExtendWith(MockitoExtension::class) //junit과 mockito를 연동하기 위해 @ExtendWith를 명시해줍니다.
class blogServiceTest {
    @Test
    fun test0(){

    }
}