package com.poc.blog.service

import com.poc.blog.entity.Post
import com.poc.blog.exception.BaseException
import com.poc.blog.exception.BaseResponseCode
import com.poc.blog.repository.PostRepository
import org.springframework.stereotype.Service

//data class Post(val id: Int,val title: String, val body: String)

@Service
class BlogService (val postRepository:PostRepository) {
    //val posts: MutableList<Post> = mutableListOf<Post>()

    fun readPost(id: String): Post {
        /*if (posts.any { it.id == id }) {
            return posts[id]
        } else {
            throw BaseException(BaseResponseCode.POST_NOT_FOUND)
        }*/
        return postRepository.findById(id).orElse(null) ?: throw BaseException(BaseResponseCode.POST_NOT_FOUND)
    }

    fun readPostList(): List<Post> {
        return postRepository.findAll()
    }

    fun createPost(title: String, body: String): Boolean {
        val post = Post(title, body)
        //posts.add(post)
        postRepository.save(post)
        return true
    }

    fun updatePost(id: String, title: String, body: String): Boolean {
        val post = postRepository.findById(id).orElse(null)?: throw BaseException(BaseResponseCode.POST_NOT_FOUND)
//Post(title, body)
        //posts[id] = post
        post.title = title
        post.body = body
        return true
    }

    fun deletePost(id: String): Boolean {
        //posts.removeAt(id)
        postRepository.deleteById(id)
        return true
    }
}