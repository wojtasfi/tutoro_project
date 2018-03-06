HOW TO RUN APPLICATION
Please clone repository, cd into tutoro/docker/common and then docker-compose up. After 2 minutes everything is up and ready- now you can play with the API by yourself.

The application is adding test data on startup.

Main microservices:

tutoroservice:
http://localhost:8090/swagger-ui.html#/

All main functionality: adding new user, edit profile, add skills, search for other tutors by skill, adding relation betwenn student and
teacher.



graph-relation-service
http://localhost:8085/swagger-ui.html#/
Takes care of writes relationships to neo4j and transforming them before of course.



recommendation-service
http://localhost:8086/swagger-ui.html#/
Get recommended skills and teachers

Please see the skill_model.png and teacher_model.png.

Skill recommendation explanation: "If user A is learning skill X and user B is also learning skill X and additionally Y- recommend skill Y
to user A"

Teacher recommendation: "If tutor A is learning from tutor B skill X and tutor C is also teaching skill X then recommend tutor C as a
teacher to tutor A"