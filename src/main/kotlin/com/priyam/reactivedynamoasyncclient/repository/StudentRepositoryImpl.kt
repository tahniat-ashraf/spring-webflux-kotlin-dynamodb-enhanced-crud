package com.priyam.reactivedynamoasyncclient.repository

import com.priyam.reactivedynamoasyncclient.dao.Student
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.env.Environment
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest

@Repository
class StudentRepositoryImpl(
    environment: Environment,
    dynamoDbEnhancedClient: DynamoDbEnhancedAsyncClient
) : StudentRepository {

    private val tableName: String = environment.activeProfiles[0] + "-student-test"
    private val mappedTable: DynamoDbAsyncTable<Student> =
        dynamoDbEnhancedClient.table(tableName, TableSchema.fromBean(Student::class.java))
    val logger: Logger = LoggerFactory.getLogger(StudentRepositoryImpl::class.java)


    override fun findAllStudents(): Flux<Student> {

        logger.info("=> findAllStudents :: tableName {}", tableName)

        return Flux.from(mappedTable.scan(ScanEnhancedRequest.builder().consistentRead(true).build()).items())

    }

    override fun getStudent(studentId: Int): Mono<Student> {

        logger.info("=> getStudent :: id {} tableName {}", studentId, tableName)

        val key = Key.builder().partitionValue(studentId).build()
        return Mono.fromFuture(mappedTable.getItem(key))
            .doOnError { logger.error("Exception while retrieving Student information - $it") }

    }

    override fun saveStudent(student: Student): Mono<Student> {

        return Mono.fromFuture(mappedTable.putItem(student))
            .map { student }
            .doOnError { logger.error("Exception while saving Customer information - $it") }

    }
}