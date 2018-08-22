**Service Fabric Java Reliable Cache** is an implementation of JSR 107. This cache leverages Service Fabric's CAP features, which makes it highly available.

The samples in this repo demonstrate how to configure and use SF-Reliable cache. Because the implementation is over JSR 107, the application developer can still write code in the way he/she used to, and can leverage the highly available cache by simply providing a configuration to use Reliable caching provider.

The sample employee directory service is designed as a partitioned micro service where each partition could handle a portion of the employee requests. Each partition here has its own cache for lookups and talks to the backend DB for cache-miss. This is one way in which the load is effectively distributed across different partitions of the micro service.

The SF PartitionResolutionHandler interceptor allows user to register partition computation logic which will be used to determine if the webrequest has landed on the partition, if not, the request is routed to the correct partition.
The compute function gets the id of the employee from the http request and computes modulo using the number of partitions available, and the returns the partition key.
This key will then be used by the interceptor to determine if the request has to be forwarded to local web server or a remote one.

This sample demonstrates how can Reliable cache be used in a typical application. The code cannot be compiled and executed for now, as the library development is in progress and is not available yet.
