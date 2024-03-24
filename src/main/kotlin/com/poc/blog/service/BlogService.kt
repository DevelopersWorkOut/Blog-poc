package com.poc.blog.service

import com.poc.blog.exception.BaseException
import com.poc.blog.exception.BaseResponseCode
import org.springframework.stereotype.Service

data class Post(val id: Int,val title: String, val body: String)

@Service
class BlogService {
    val posts: MutableList<Post> = mutableListOf<Post>()

    fun readPost(id: Int): Post {
        if (posts.any { it.id == id }) {
            return posts[id]
        } else {
            throw BaseException(BaseResponseCode.POST_NOT_FOUND)
        }
    }

    fun readPostList(): List<Post> {
        return posts
    }

    fun createPost(title: String, body: String): Boolean {
        val post = Post(posts.size,title, body)
        posts.add(post)
        return true
    }

    fun updatePost(id: Int, title: String, body: String): Boolean {
        val post = Post(posts.size,title, body)
        posts[id] = post
        return true
    }

    fun deletePost(id: Int): Boolean {
        posts.removeAt(id)
        return true
    }
}