This is a programming assignment of making message processing application.

The assignment implemented using Akka and provides JSON API for sending messages.
PUT request `/one-off-message` expects OneOffSale object in the request body {"productType": "apples", "cost" : 0.9}
PUT request `/bunch-message` expects BunchSale object in the request body {"productType": "apples", "cost" : 0.9, "itemsCount": 50}
GET request `/report`

Please see the result of compilation and test run below.

C:\Projects\akka_test\MessageProcessing>mvn test 
[INFO] Scanning for projects...
[INFO]                                                                         
[INFO] ------------------------------------------------------------------------
[INFO] Building message-processing-technical-test 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ message-processing-technical-test ---
[WARNING] Using platform encoding (Cp1251 actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] Copying 0 resource
[INFO] 
[INFO] --- maven-compiler-plugin:3.5.1:compile (default-compile) @ message-processing-technical-test ---
[INFO] Changes detected - recompiling the module!
[WARNING] File encoding has not been set, using platform encoding Cp1251, i.e. build is platform dependent!
[INFO] Compiling 6 source files to C:\Projects\akka_test\MessageProcessing\target\classes
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ message-processing-technical-test ---
[WARNING] Using platform encoding (Cp1251 actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] skip non existing resourceDirectory C:\Projects\akka_test\MessageProcessing\src\test\resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.5.1:testCompile (default-testCompile) @ message-processing-technical-test ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ message-processing-technical-test ---
[INFO] Surefire report directory: C:\Projects\akka_test\MessageProcessing\target\surefire-reports

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running com.sfirsov.processing.HttpApiTest
[INFO] [06/19/2017 19:05:23.841] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/saleActor] message of:	 apples	 with the cost 879.26
[INFO] [06/19/2017 19:05:23.899] [SalesProcessingTest-akka.actor.default-dispatcher-4] [akka://SalesProcessingTest/user/saleActor] message of:	 55 carrots	 with the cost 634.70
[INFO] [06/19/2017 19:05:23.901] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/saleActor] message of:	 melons	 with the cost 727.54
[INFO] [06/19/2017 19:05:23.903] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/saleActor] message of:	 34 melons	 with the cost 398.83
[INFO] [06/19/2017 19:05:23.904] [SalesProcessingTest-akka.actor.default-dispatcher-4] [akka://SalesProcessingTest/user/saleActor] message of:	 oranges	 with the cost 567.52
[INFO] [06/19/2017 19:05:23.906] [SalesProcessingTest-akka.actor.default-dispatcher-4] [akka://SalesProcessingTest/user/saleActor] message of:	 carrots	 with the cost 945.72
[INFO] [06/19/2017 19:05:23.908] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/saleActor] message of:	 potatoes	 with the cost 781.66
[INFO] [06/19/2017 19:05:23.910] [SalesProcessingTest-akka.actor.default-dispatcher-4] [akka://SalesProcessingTest/user/saleActor] message of:	 melons	 with the cost 617.52
[INFO] [06/19/2017 19:05:23.912] [SalesProcessingTest-akka.actor.default-dispatcher-4] [akka://SalesProcessingTest/user/saleActor] message of:	 9 potatoes	 with the cost 866.55
[INFO] [06/19/2017 19:05:23.914] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/saleActor] message of:	 52 potatoes	 with the cost 397.62
[INFO] [06/19/2017 19:05:23.914] [SalesProcessingTest-akka.actor.default-dispatcher-5] [akka://SalesProcessingTest/user/reportActor] ---------------------------------
[INFO] [06/19/2017 19:05:23.914] [SalesProcessingTest-akka.actor.default-dispatcher-5] [akka://SalesProcessingTest/user/reportActor] Begin report for the last 10 sales:
[INFO] [06/19/2017 19:05:23.914] [SalesProcessingTest-akka.actor.default-dispatcher-5] [akka://SalesProcessingTest/user/reportActor] Product: apples; number of transactions: 1; number of sails: 1; total value: 879.26
[INFO] [06/19/2017 19:05:23.914] [SalesProcessingTest-akka.actor.default-dispatcher-5] [akka://SalesProcessingTest/user/reportActor] Product: carrots; number of transactions: 2; number of sails: 56; total value: 35854.13
[INFO] [06/19/2017 19:05:23.914] [SalesProcessingTest-akka.actor.default-dispatcher-5] [akka://SalesProcessingTest/user/reportActor] Product: melons; number of transactions: 3; number of sails: 36; total value: 15304.14
[INFO] [06/19/2017 19:05:23.914] [SalesProcessingTest-akka.actor.default-dispatcher-5] [akka://SalesProcessingTest/user/reportActor] Product: oranges; number of transactions: 1; number of sails: 1; total value: 567.52
[INFO] [06/19/2017 19:05:23.915] [SalesProcessingTest-akka.actor.default-dispatcher-5] [akka://SalesProcessingTest/user/reportActor] Product: potatoes; number of transactions: 3; number of sails: 62; total value: 34099.42
[INFO] [06/19/2017 19:05:23.915] [SalesProcessingTest-akka.actor.default-dispatcher-5] [akka://SalesProcessingTest/user/reportActor] end of report
[INFO] [06/19/2017 19:05:23.915] [SalesProcessingTest-akka.actor.default-dispatcher-5] [akka://SalesProcessingTest/user/reportActor] ---------------------------------
[INFO] [06/19/2017 19:05:23.917] [SalesProcessingTest-akka.actor.default-dispatcher-5] [akka://SalesProcessingTest/user/saleActor] message of:	 7 potatoes	 with the cost 994.48
[INFO] [06/19/2017 19:05:23.919] [SalesProcessingTest-akka.actor.default-dispatcher-4] [akka://SalesProcessingTest/user/saleActor] message of:	 43 apples	 with the cost 719.87
[INFO] [06/19/2017 19:05:23.921] [SalesProcessingTest-akka.actor.default-dispatcher-5] [akka://SalesProcessingTest/user/saleActor] message of:	 oranges	 with the cost 324.91
[INFO] [06/19/2017 19:05:23.924] [SalesProcessingTest-akka.actor.default-dispatcher-5] [akka://SalesProcessingTest/user/saleActor] message of:	 melons	 with the cost 697.38
[INFO] [06/19/2017 19:05:23.929] [SalesProcessingTest-akka.actor.default-dispatcher-4] [akka://SalesProcessingTest/user/saleActor] message of:	 26 melons	 with the cost 744.51
[INFO] [06/19/2017 19:05:23.933] [SalesProcessingTest-akka.actor.default-dispatcher-5] [akka://SalesProcessingTest/user/saleActor] message of:	 melons	 with the cost 521.90
[INFO] [06/19/2017 19:05:23.935] [SalesProcessingTest-akka.actor.default-dispatcher-4] [akka://SalesProcessingTest/user/saleActor] message of:	 16 apples	 with the cost 489.61
[INFO] [06/19/2017 19:05:23.937] [SalesProcessingTest-akka.actor.default-dispatcher-5] [akka://SalesProcessingTest/user/saleActor] message of:	 melons	 with the cost 358.64
[INFO] [06/19/2017 19:05:23.940] [SalesProcessingTest-akka.actor.default-dispatcher-4] [akka://SalesProcessingTest/user/saleActor] message of:	 carrots	 with the cost 204.95
[INFO] [06/19/2017 19:05:23.942] [SalesProcessingTest-akka.actor.default-dispatcher-5] [akka://SalesProcessingTest/user/saleActor] message of:	 melons	 with the cost 772.87
[INFO] [06/19/2017 19:05:23.942] [SalesProcessingTest-akka.actor.default-dispatcher-4] [akka://SalesProcessingTest/user/reportActor] ---------------------------------
[INFO] [06/19/2017 19:05:23.942] [SalesProcessingTest-akka.actor.default-dispatcher-4] [akka://SalesProcessingTest/user/reportActor] Begin report for the last 10 sales:
[INFO] [06/19/2017 19:05:23.942] [SalesProcessingTest-akka.actor.default-dispatcher-4] [akka://SalesProcessingTest/user/reportActor] Product: apples; number of transactions: 2; number of sails: 59; total value: 59841.39
[INFO] [06/19/2017 19:05:23.942] [SalesProcessingTest-akka.actor.default-dispatcher-4] [akka://SalesProcessingTest/user/reportActor] Product: carrots; number of transactions: 1; number of sails: 1; total value: 204.95
[INFO] [06/19/2017 19:05:23.943] [SalesProcessingTest-akka.actor.default-dispatcher-4] [akka://SalesProcessingTest/user/reportActor] Product: melons; number of transactions: 5; number of sails: 30; total value: 22452.43
[INFO] [06/19/2017 19:05:23.943] [SalesProcessingTest-akka.actor.default-dispatcher-4] [akka://SalesProcessingTest/user/reportActor] Product: oranges; number of transactions: 1; number of sails: 1; total value: 324.91
[INFO] [06/19/2017 19:05:23.943] [SalesProcessingTest-akka.actor.default-dispatcher-4] [akka://SalesProcessingTest/user/reportActor] Product: potatoes; number of transactions: 1; number of sails: 7; total value: 6961.33
[INFO] [06/19/2017 19:05:23.943] [SalesProcessingTest-akka.actor.default-dispatcher-4] [akka://SalesProcessingTest/user/reportActor] end of report
[INFO] [06/19/2017 19:05:23.943] [SalesProcessingTest-akka.actor.default-dispatcher-4] [akka://SalesProcessingTest/user/reportActor] ---------------------------------
[INFO] [06/19/2017 19:05:23.944] [SalesProcessingTest-akka.actor.default-dispatcher-4] [akka://SalesProcessingTest/user/saleActor] message of:	 26 potatoes	 with the cost 952.29
[INFO] [06/19/2017 19:05:23.945] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/saleActor] message of:	 27 potatoes	 with the cost 521.53
[INFO] [06/19/2017 19:05:23.947] [SalesProcessingTest-akka.actor.default-dispatcher-4] [akka://SalesProcessingTest/user/saleActor] message of:	 carrots	 with the cost 147.66
[INFO] [06/19/2017 19:05:23.949] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/saleActor] message of:	 40 potatoes	 with the cost 1000.50
[INFO] [06/19/2017 19:05:23.950] [SalesProcessingTest-akka.actor.default-dispatcher-4] [akka://SalesProcessingTest/user/saleActor] message of:	 apples	 with the cost 520.88
[INFO] [06/19/2017 19:05:23.952] [SalesProcessingTest-akka.actor.default-dispatcher-4] [akka://SalesProcessingTest/user/saleActor] message of:	 66 potatoes	 with the cost 634.54
[INFO] [06/19/2017 19:05:23.953] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/saleActor] message of:	 melons	 with the cost 292.39
[INFO] [06/19/2017 19:05:23.955] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/saleActor] message of:	 37 melons	 with the cost 100.23
[INFO] [06/19/2017 19:05:23.957] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/saleActor] message of:	 oranges	 with the cost 784.08
[INFO] [06/19/2017 19:05:23.959] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/saleActor] message of:	 91 potatoes	 with the cost 624.12
[INFO] [06/19/2017 19:05:23.959] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/reportActor] ---------------------------------
[INFO] [06/19/2017 19:05:23.959] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/reportActor] Begin report for the last 10 sales:
[INFO] [06/19/2017 19:05:23.960] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/reportActor] Product: apples; number of transactions: 1; number of sails: 1; total value: 520.88
[INFO] [06/19/2017 19:05:23.960] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/reportActor] Product: carrots; number of transactions: 1; number of sails: 1; total value: 147.66
[INFO] [06/19/2017 19:05:23.960] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/reportActor] Product: melons; number of transactions: 2; number of sails: 38; total value: 4101.12
[INFO] [06/19/2017 19:05:23.960] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/reportActor] Product: oranges; number of transactions: 1; number of sails: 1; total value: 784.08
[INFO] [06/19/2017 19:05:23.960] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/reportActor] Product: potatoes; number of transactions: 5; number of sails: 250; total value: 402370.02
[INFO] [06/19/2017 19:05:23.960] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/reportActor] end of report
[INFO] [06/19/2017 19:05:23.960] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/reportActor] ---------------------------------
[INFO] [06/19/2017 19:05:23.962] [SalesProcessingTest-akka.actor.default-dispatcher-4] [akka://SalesProcessingTest/user/saleActor] message of:	 apples	 with the cost 579.08
[INFO] [06/19/2017 19:05:23.964] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/saleActor] message of:	 29 melons	 with the cost 138.93
[INFO] [06/19/2017 19:05:23.966] [SalesProcessingTest-akka.actor.default-dispatcher-4] [akka://SalesProcessingTest/user/saleActor] message of:	 potatoes	 with the cost 581.51
[INFO] [06/19/2017 19:05:23.968] [SalesProcessingTest-akka.actor.default-dispatcher-4] [akka://SalesProcessingTest/user/saleActor] message of:	 79 carrots	 with the cost 474.70
[INFO] [06/19/2017 19:05:23.969] [SalesProcessingTest-akka.actor.default-dispatcher-4] [akka://SalesProcessingTest/user/saleActor] message of:	 apples	 with the cost 860.99
[INFO] [06/19/2017 19:05:23.971] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/saleActor] message of:	 17 carrots	 with the cost 954.27
[INFO] [06/19/2017 19:05:23.973] [SalesProcessingTest-akka.actor.default-dispatcher-4] [akka://SalesProcessingTest/user/saleActor] message of:	 carrots	 with the cost 240.06
[INFO] [06/19/2017 19:05:23.974] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/saleActor] message of:	 oranges	 with the cost 682.83
[INFO] [06/19/2017 19:05:23.976] [SalesProcessingTest-akka.actor.default-dispatcher-4] [akka://SalesProcessingTest/user/saleActor] message of:	 41 melons	 with the cost 603.46
[INFO] [06/19/2017 19:05:23.978] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/saleActor] message of:	 oranges	 with the cost 914.54
[INFO] [06/19/2017 19:05:23.978] [SalesProcessingTest-akka.actor.default-dispatcher-6] [akka://SalesProcessingTest/user/reportActor] ---------------------------------
[INFO] [06/19/2017 19:05:23.978] [SalesProcessingTest-akka.actor.default-dispatcher-6] [akka://SalesProcessingTest/user/reportActor] Begin report for the last 10 sales:
[INFO] [06/19/2017 19:05:23.978] [SalesProcessingTest-akka.actor.default-dispatcher-6] [akka://SalesProcessingTest/user/reportActor] Product: apples; number of transactions: 2; number of sails: 2; total value: 1440.06
[INFO] [06/19/2017 19:05:23.978] [SalesProcessingTest-akka.actor.default-dispatcher-6] [akka://SalesProcessingTest/user/reportActor] Product: carrots; number of transactions: 3; number of sails: 97; total value: 129351.39
[INFO] [06/19/2017 19:05:23.978] [SalesProcessingTest-akka.actor.default-dispatcher-6] [akka://SalesProcessingTest/user/reportActor] Product: melons; number of transactions: 2; number of sails: 70; total value: 46270.85
[INFO] [06/19/2017 19:05:23.978] [SalesProcessingTest-akka.actor.default-dispatcher-6] [akka://SalesProcessingTest/user/reportActor] Product: oranges; number of transactions: 2; number of sails: 2; total value: 1597.37
[INFO] [06/19/2017 19:05:23.978] [SalesProcessingTest-akka.actor.default-dispatcher-6] [akka://SalesProcessingTest/user/reportActor] Product: potatoes; number of transactions: 1; number of sails: 1; total value: 581.51
[INFO] [06/19/2017 19:05:23.978] [SalesProcessingTest-akka.actor.default-dispatcher-6] [akka://SalesProcessingTest/user/reportActor] end of report
[INFO] [06/19/2017 19:05:23.978] [SalesProcessingTest-akka.actor.default-dispatcher-6] [akka://SalesProcessingTest/user/reportActor] ---------------------------------
[INFO] [06/19/2017 19:05:23.979] [SalesProcessingTest-akka.actor.default-dispatcher-6] [akka://SalesProcessingTest/user/saleActor] message of:	 oranges	 with the cost 815.04
[INFO] [06/19/2017 19:05:23.981] [SalesProcessingTest-akka.actor.default-dispatcher-4] [akka://SalesProcessingTest/user/saleActor] message of:	 23 carrots	 with the cost 411.46
[INFO] [06/19/2017 19:05:23.983] [SalesProcessingTest-akka.actor.default-dispatcher-6] [akka://SalesProcessingTest/user/saleActor] message of:	 potatoes	 with the cost 670.13
[INFO] [06/19/2017 19:05:23.984] [SalesProcessingTest-akka.actor.default-dispatcher-4] [akka://SalesProcessingTest/user/saleActor] message of:	 32 oranges	 with the cost 738.66
[INFO] [06/19/2017 19:05:23.985] [SalesProcessingTest-akka.actor.default-dispatcher-6] [akka://SalesProcessingTest/user/saleActor] message of:	 apples	 with the cost 310.88
[INFO] [06/19/2017 19:05:23.987] [SalesProcessingTest-akka.actor.default-dispatcher-4] [akka://SalesProcessingTest/user/saleActor] message of:	 oranges	 with the cost 888.24
[INFO] [06/19/2017 19:05:23.988] [SalesProcessingTest-akka.actor.default-dispatcher-6] [akka://SalesProcessingTest/user/saleActor] message of:	 45 carrots	 with the cost 103.65
[INFO] [06/19/2017 19:05:23.990] [SalesProcessingTest-akka.actor.default-dispatcher-4] [akka://SalesProcessingTest/user/saleActor] message of:	 potatoes	 with the cost 548.50
[INFO] [06/19/2017 19:05:23.991] [SalesProcessingTest-akka.actor.default-dispatcher-6] [akka://SalesProcessingTest/user/saleActor] message of:	 potatoes	 with the cost 654.91
[INFO] [06/19/2017 19:05:23.993] [SalesProcessingTest-akka.actor.default-dispatcher-4] [akka://SalesProcessingTest/user/saleActor] message of:	 50 apples	 with the cost 696.65
[INFO] [06/19/2017 19:05:23.993] [SalesProcessingTest-akka.actor.default-dispatcher-4] [akka://SalesProcessingTest/user/saleActor] Stop accepting new orders
[INFO] [06/19/2017 19:05:23.993] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/reportActor] ---------------------------------
[INFO] [06/19/2017 19:05:23.993] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/reportActor] Begin report for the last 10 sales:
[INFO] [06/19/2017 19:05:23.993] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/reportActor] Product: apples; number of transactions: 2; number of sails: 51; total value: 35839.86
[INFO] [06/19/2017 19:05:23.993] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/reportActor] Product: carrots; number of transactions: 2; number of sails: 68; total value: 16511.60
[INFO] [06/19/2017 19:05:23.994] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/reportActor] Product: oranges; number of transactions: 3; number of sails: 34; total value: 26079.16
[INFO] [06/19/2017 19:05:23.994] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/reportActor] Product: potatoes; number of transactions: 3; number of sails: 3; total value: 1873.53
[INFO] [06/19/2017 19:05:23.994] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/reportActor] end of report
[INFO] [06/19/2017 19:05:23.994] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/reportActor] ---------------------------------
[INFO] [06/19/2017 19:05:24.014] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/reportActor] ---------------------------------
[INFO] [06/19/2017 19:05:24.014] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/reportActor] Begin final report:
[INFO] [06/19/2017 19:05:24.014] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/reportActor] Product type: apples; starting price: 879.26; final price: 696.65
[INFO] [06/19/2017 19:05:24.014] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/reportActor] Product type: carrots; starting price: 634.70; final price: 103.65
[INFO] [06/19/2017 19:05:24.014] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/reportActor] Product type: melons; starting price: 727.54; final price: 603.46
[INFO] [06/19/2017 19:05:24.014] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/reportActor] Product type: oranges; starting price: 567.52; final price: 888.24
[INFO] [06/19/2017 19:05:24.014] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/reportActor] Product type: potatoes; starting price: 781.66; final price: 654.91
[INFO] [06/19/2017 19:05:24.015] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/reportActor] end of report
[INFO] [06/19/2017 19:05:24.015] [SalesProcessingTest-akka.actor.default-dispatcher-3] [akka://SalesProcessingTest/user/reportActor] ---------------------------------
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.694 sec

Results :

Tests run: 2, Failures: 0, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 4.300 s
[INFO] Finished at: 2017-06-19T19:05:24+01:00
[INFO] Final Memory: 17M/223M
[INFO] ------------------------------------------------------------------------
