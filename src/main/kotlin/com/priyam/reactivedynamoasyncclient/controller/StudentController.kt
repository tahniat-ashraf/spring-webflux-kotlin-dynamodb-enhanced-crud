package com.priyam.reactivedynamoasyncclient.controller

import com.priyam.reactivedynamoasyncclient.dao.Student
import com.priyam.reactivedynamoasyncclient.repository.StudentRepository
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping(path = ["/student"], produces = [APPLICATION_JSON_VALUE])
class StudentController(
    private val studentRepository: StudentRepository
) {

    @GetMapping
    fun findAllStudents(): Flux<Student> {
        return studentRepository.findAllStudents()
    }

    @GetMapping(path = ["/{id}"])
    fun findStudentById(@PathVariable id: Int): Mono<Student> {
        return studentRepository.getStudent(id);
    }

    @PostMapping
    fun saveStudent(@RequestBody student: Student): Mono<Student> {
        return studentRepository.saveStudent(student)
    }
}