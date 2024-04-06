package com.poc.blog.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "postB")
class Post(title:String,body:String) {
    @Id
    @GeneratedValue
    var id: Long?=null // = TODO("initialize me")
    //var id: Long = TODO("initialize me")
    @Column
    var title: String = title

    @Column
    var body: String = body
    //맨위 테이블명 명명안하니까 오류나는거같음
}