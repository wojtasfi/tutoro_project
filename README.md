## SHORT DESCRIPTION

Tutoro is my ongoing project which helps me to develop my skills. The basic idea is to create service where people can
share their passions by teaching them to someone and for people who want to learn new skills from hobbyist and
professionals. Moreover I am developing recommendation service which will help to find new skills or teachers. This is
currently the backend part of the project. I started developing frontend in Angular 5 here: https://gitlab.com/wojtasfi/tutoro-front.

Technologies/solutions:

Java 8

Spring Framework

JPA

neo4j

docker

microservices (Netflix stack)

oAuth2 (with JWT)

config-server


## HOW TO RUN APPLICATION

Please clone repository, cd into tutoro/docker/common and then docker-compose up. After 2 minutes everything is up and
ready- now you can play with the API by yourself.

The application is adding test data on startup. Note that it also start with default profile which disables authentciation
for testing purposes (see below). Of course all api is accesible also through Zuul, for example token:
http://localhost:5555/api/auth/oauth/token

### Main microservices:

#### tutoroservice:

http://localhost:8090/swagger-ui.html#/

All main functionality: adding new user, edit profile, add skills, search for other tutors by skill, adding relation
between student and teacher.


#### graph-relation-service

http://localhost:8085/swagger-ui.html#/

Takes care of writes relationships to neo4j and transforming them before of course.


#### recommendation-service

http://localhost:8086/swagger-ui.html#/

Get recommended skills and teachers



Please see the skill_model.png and teacher_model.png.

Skill recommendation explanation: "If user A is learning skill X and user B is also learning skill X and additionally Y-
recommend skill Y to user A".

Teacher recommendation: "If tutor A is learning from tutor B skill X and tutor C is also teaching skill X then recommend
tutor C as a teacher to tutor A".

## FUTURE

I plan to add more features to recommendation service, make searching based on Elasticsearch. Additionally I would like to
evaluate rank of the tutors by applying some graph algorithms like Page Rank- I will implement it using Apache Spark
with GraphX.