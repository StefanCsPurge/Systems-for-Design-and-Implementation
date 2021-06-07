
[15 p] Lab 10: Angular, Spring, JPA

- continue to work individually on the previous project (same repo)
- Spring Boot is, from now on, allowed (the project structure should be the same as before i.e. core, web)
- CRUD operations (the link entity/entities e.g. Rental, StudentDiscipline is/are for now not required; relations - for now, not required)
- Filter, sort operations (client-side and server-side; on server-side, with Spring Data JPA)
- Use both reactive and template-driven forms and validate user input (self study)
- Use ES6 features (or above) and follow redux principles (see readme)
- There should be four root entities and the requirements may be split over them, e.g., CRUD operations on entity1, client-side filters on entity2, server-side filters on entity3 etc


[15 p] Lab 11: Angular, Spring, JPA - mapping relationships

- continue to work individually on the previous project (same repo)
- Implement relationships between entities; the fetch type will be eager 
- There should be at least four root entities and one repository per root aggregate; there should be no repository for the link entities (e.g. StudentDiscipline); 
- there should be at least one relationship from each of the following: many-to-many, one-to-many, one-to-one 
  (many-to-many may be implemented with two one-to-many associations; one-to-one may be implemented with embeddable)
- Operations on the link entity (e.g. enroll students to disciplines, assign/view/etc grades); 
- reports/statistics


[15 p] Lab 12: handling the n + 1 select problem; testing

- continue to work individually on the previous project (same repo)
- all associations must be lazily loaded
- after switching to Lazy fetching, check if the LazyInitializationException actually appears before trying to ‘handle' it (in SpringBoot some settings might be needed in this sense - otherwise everything is fetched eagerly)
- query the entities using: Spring Queries with Named Entity Graphs,  JPQL, Criteria API, Native SQL
- in each repository (e.g: BookRepository, ClientRepository etc) there should be at least two methods using NamedEntityGraphs
- for each repository (e.g: BookRepository, ClientRepository etc), in the corresponding fragment/customized interface, there should be at least two additional methods; these  additional methods should have three different implementations with: JPQL, CriteriaAPI, NativeSql
- in the services only the 'main' repositories should be used (e.g: BookRepository and ClientRepository, not the fragment/customized ones)
- the application should work alternatively with all of the following configurations: EntityGraphs + JPQL, EntityGraphs + CriteriaAPI, EntityGraphs + NativeSql. The configuration switch should be possible by changing annotations or property files, but not java code
- write integration tests for your repositories and services; use DbUnit, xml datasets
- write unit tests for your controllers using Mockito 
