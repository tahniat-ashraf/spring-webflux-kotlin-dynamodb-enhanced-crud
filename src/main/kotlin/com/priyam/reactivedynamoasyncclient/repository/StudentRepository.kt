package com.priyam.reactivedynamoasyncclient.repository

import com.priyam.reactivedynamoasyncclient.dao.Student
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface StudentRepository {

    fun findAllStudents(): Flux<Student>

    fun getStudent(studentId: Int): Mono<Student>

    fun saveStudent(student: Student): Mono<Student>

}