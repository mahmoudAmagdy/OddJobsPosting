Hello, this is a mini project mirroring a job board.
The job board is meant to contain jobs posted by job issuers. These jobs are to be taken by job takers for a specific reward.

There are three main entities:

Job.java ===> this entity is the job created by the entity JobIssuer, soon to be discussed. This entity contains some information
about the job that is to be taken and the rewards. It should also contain a delivery/start date and available slots for the job.

JobIssuer.java ==> this entity represents the folks who are supposed to be creating the Job entity. The very existence of the
Job entity is tied to the JobIssuer entity, with a many to one relation between the Job entity and the JobIssuer entity.
The JobIssuer entity has the typical information about a person that is required by a governmental/corporation entity in order
to guarantee trust and be able to reach said person. It also contains a rating value to evaluate how well this JobIssuer delivers
on previously mentioned payments.

JobTaker.java ==> this entity is the beneficiary of the Job entity created by the JobIssuer entity. While the JobTaker entity 
has the same details as the JobIssuer, they have relatively different relations. While they are not directly related, they are
indirectly related based on the Job a JobIssuer "issues" and the Job the JobTaker "takes". By this logic, the JobTaker and the
Job entities have a many to many relation between each other. The JobTaker has general information about themselves to make them
trustworthy to governmental/corporation entities. Finally, there is also a rating system upon which JobIssuers can rate JobTaker
entities depending on their level of satisfaction towards the delivered product/job.

============================================

JPA implementation:
This project is supposed to be linked to a Postgresql server with the application.properties file having all the required 
information regarding connections. The port is 8080(default). The server name is DWTD.

There is a mixture in the design patterns used. For each entity there are three classes in the com.el7eita.cashisking.repositories
package; an interface repository which extends JpaRepository(for all three), an interface Service (DAO) pattern class
for each of the three, and a ServiceImpl which implements the previously mentioned Service interface. The ServiceImpl creates
the repositories required for it to actually access each class. The entities themselves have their own getters and setters
created by the lombok library annotation (@Data) which automatically creates getters and setters for each annotated class.

For the JobRepository, it extends the JpaRepository with the typical methods, with an extra two methods which enable the users
to find Job entities based on the associated JobTaker entities and JobIssuer entities. 

JobService and its Impl have the typical CRUD operations. 

For the JobIssuerRepository, it has the same implementation as the JobRepository. 

JobIssuerService and its Impl have the typical CRUD operations.

For the JobTakerRepository, it has the same implementation as the aforementioned repositories.

JobTakerService and its Impl have the typical CRUD operations.

NOTE: METHODS NOT INCLUDED BECAUSE OF THE IMPERMENANCE OF THE METHODS CURRENTLY DECIDED ON, PROJECT STILL UNDER DEVELOPMENT.

============================================

Exceptions:

There are exceptions created for 6 situations:

JobAlreadyExistsException.java ==> an exception thrown when a Job entity is being created while it already exists.

JobIssuerAlreadyExistsException.java ==> an exception thrown when a JobIssuer entity is being created while it already exists.

JobTakerAlreadyExistsException.java ==> an exception thrown when a JobTaker entity is being created while it already exists.

NoJobExistsException.java ==> an exception thrown when an operation is attempted on a Job entity that does not currently exist.

NoJobIssuerExistsException.java ==> an exception thrown when an operation is attempted on a JobIssuer entity that does not 
currently exist.

NoJobTakerExistsException.java ==> an exception thrown when an operation is attempted on a JobTaker entity that does not 
currently exist.