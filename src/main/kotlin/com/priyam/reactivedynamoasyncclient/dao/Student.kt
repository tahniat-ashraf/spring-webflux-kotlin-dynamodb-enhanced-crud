package com.priyam.reactivedynamoasyncclient.dao

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey

@DynamoDbBean
data class Student(

    @get:DynamoDbPartitionKey var id: Int = 0,
    var firstName: String? = null,
    var lastName: String? = null
)