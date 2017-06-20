# Message Processing Programming Test

This is a programming assignment of making message processing application.

The assignment implemented using Akka and provides JSON API for sending messages.

PUT request `/single-sale` expects OneOffSale object in the request body {"productType": "apples", "cost" : 0.9}

PUT request `/multiple-sale` expects BunchSale object in the request body {"productType": "apples", "cost" : 0.9, "itemsCount": 50}

PUT request `/adjust-price` expects AdjustPrice object in the request body {"productType": "apples", "operation" : "Add", "value": 5}

GET request `/report`

Please see the result of compilation and test run in the file test_run_result.txt

To run the code maven can be used.
```
mvn test
```
