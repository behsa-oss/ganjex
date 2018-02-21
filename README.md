## Ganjex
Oblivious to its holding services, Ganjex is a passive container facilitating the management of 
the containing elements' alterations and lifecycle at runtime. Ganjex is a platform layer 
container, so that user must define their own framework based on their necessities and 
preferences properly. There are two types of elements Ganjex contains: 1.Library and 2.Service.

## Framework
It is expected that application framework, also called as client, would define how it plans to 
treat containing services. Ganjex container is started by framework with the code below:
 ```
 Ganjex.run(ganjexConfiguration);
 ``` 
 In the above code `ganjexConfiguration` is a configuration instance of `GanjexConfiguration` 
 class which should be created by `GanjexConfiguration.Builder`. As an illustration:
 ```
 	Ganjex ganjex = //you can save an instance of Ganjex container in Ganjex object
 	    Ganjex.run(new GanjexConfiguration.Builder()
 	        .libPath("/opt/ganjex/lib")         //location where libraries should be added
 	        .servicePath("/opt/ganjex/service)  //location where services should be added
 	        .watcherDelay(4)                    //how many seconds watchers should wait to retry
 	        .hooks(new SomeHookContainer())     //list of all objects containing hooks
 	        .build());
 ``` 
Please note that `SomeHookContainer` class in the above example may have methods annotated with 
`@StartupHook` or `@ShutdownHook` in order to manage the lifecycle of services. This is an 
example of this class:
```
public class SomeHookContainer {
    @StartupHook
    public void start(ServiceContext context){
        //consequent behavior changes mandated by the newly added service
    }
    
    @ShutdownHook
    public void destroy(ServiceContext context){
        //consequent fallback changes mandated by the newly added service
    }
} 
```
The service classLoader is required to surf the service code to manage business necessities 
defined by framework, so the service classLoader would be provided by the ServiceContext object.
In other words, Ganjex would behave in a way that is defined in the framework by the client. 

## Service     
Accomplishing specific jobs, services are interchangeable units typically implement a use case. 
As business use cases are being frequently changed, Ganjex services are supposed to be changed
repetitively as well. As soon as a requirement is changed, the implementation of that requirement must 
be changed and deployed consequently. In Ganjex, also, services could be deployed or removed at runtime 
(on the fly). Soon after a service is added to Ganjex container, all of the `@StartupHook` methods  
would be notified with the `ServiceContext` of the newly added service. Similarly, right after a 
service is removed from Ganjex container, all of the frameworks' methods annotated with 
`@ShutdownHook` would be notified with the `ServiceContext` of that service. 
This is framework's responsibility to treat each service properly, due to the fact that Ganjex 
knows nothing of the structure and pattern services utilize. Remember frameworks are not 
expected to be changed frequently.

### Service manifest
Every service should have a file named *manifest.properties* in the root of its classpath. This 
is a manifest clearing the service identity. This should contain two keys: *name* and *version*

## Library
There are cases when multiple services need a class, or a domain model class should be shared 
between services. As we can have many services, this might bring about having duplicate shared code 
in the services which would be inefficient and difficult to maintain. Here Libraries come to 
rescue, Libraries are typical jar files which can be changed and required to be accessible to the
services. A service needing a library, should 
add the library's corresponding Maven dependency with `<scope>provided</scope>` because it would 
be provided by Ganjex at runtime.

Please note that changing the libraries is costly, meaning that soon after a library is changed 
(modified, added or removed), all of the services would be restarted in order to affect the 
consequent changes.

## Use Spring-Boot and Ganjex simultaneously
A Spring-boot-starter has been particularly designed for Ganjex which could be mounted on 
Spring-boot applications. By adding `@EnableGanjexContainer` class-level annotation on the 
Configuration class, Ganjex starts and scans all the beans with `@GanjexHook` annotation. Note 
that, if a class is marked with `@GanjexHook`, that class would be qualified to be a Spring 
component bean as well, so there would be no need to add @Component or @Service by doing so.

### Spring-Boot properties 
To add ganjex-starter to a spring-boot application, add three properties besides spring-boot 
properties:
* ganjex.lib-path
* ganjex.service-path
* ganjex.watch-delay
They are the same as `GanjexConfiguration` fields.

## License
This software is licensed under the Apache License, version 2 ("ALv2"), quoted below.

Copyright 2018 Behsa Corporation

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.