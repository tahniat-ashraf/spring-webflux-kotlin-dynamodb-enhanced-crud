#!/usr/bin/env bash
for i in $(ls tables/table-*.json);
    do
        tableJsonName=$(echo $i | cut -d'-' -f 2)
        tableName=$(echo $tableJsonName | cut -d'.' -f 1)
        echo "Deleting table : "$tableName
        aws dynamodb delete-table --table-name dev-mapp-$tableName --region "ap-southeast-1" --endpoint-url http://localhost:4569;
done;