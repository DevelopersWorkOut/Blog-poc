package com.poc.blog.controller

import com.poc.blog.service.BlogService
import com.poc.blog.service.Post
import org.springframework.web.bind.annotation.*

data class CreatePostReqDto (
        val title: String,
        val body: String
)
@RestController
@RequestMapping("/blog")
class MainController (val blogService: BlogService) {


    @GetMapping()
    fun readPostList():List<Post> {
        val list:List<String> = listOf("HELLO","WORLD")
        return blogService.readPostList()
    }

    @GetMapping("/{id}")
    fun readPost(@PathVariable("id") id:Int
                    , @RequestParam("page"
                    , required = false
                    , defaultValue = 0.toString()) page: Int)
    :Post {
        val list:List<String> = listOf("HELLO","WORLD")
        return blogService.readPost(id)
    }

    @PostMapping()
    fun createPost(@RequestBody() createPostReqDto: CreatePostReqDto): Boolean{
        return blogService.createPost(createPostReqDto.title, createPostReqDto.body)
    }
    @PostMapping("/{id}")
    fun updatePost(@PathVariable("id") id:Int, @RequestBody() createPostReqDto: CreatePostReqDto): Boolean{
        return blogService.updatePost(id, createPostReqDto.title, createPostReqDto.body)
    }

    @PostMapping("/del/{id}")
    fun deletePost(@PathVariable("id") id:Int): Boolean{
        return blogService.deletePost(id)
    }
    /* for aop test
    *  @PostMapping()
    fun createPost(@RequestBody() createPostReqDto: CreatePostReqDto) = NativeKotlinPerformence("test") {
        return@NativeKotlinPerformence blogService.createPost(createPostReqDto.title, createPostReqDto.body)
    }
    * */
}