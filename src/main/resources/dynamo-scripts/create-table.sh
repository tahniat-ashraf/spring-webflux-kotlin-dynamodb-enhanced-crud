#!/usr/bin/env bash
for i in $(ls tables/table-*.json);
    do
        echo "Creating table : "$i
        aws dynamodb create-table --cli-input-json file://$i --region "ap-southeast-1" --endpoint-url http://localhost:4569;
done;