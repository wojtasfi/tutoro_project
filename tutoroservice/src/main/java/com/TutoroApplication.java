package com;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tutoro.dao.TutorRepository;
import com.tutoro.dto.UserDto;
import com.tutoro.entities.LearnRelation;
import com.tutoro.entities.Skill;
import com.tutoro.entities.Tutor;
import com.tutoro.service.LearnRelationService;
import com.tutoro.service.SkillService;
import com.tutoro.service.TutorService;
import com.tutoro.service.UserService;
import com.tutoro.utils.UserContextInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static java.time.format.DateTimeFormatter.ofPattern;

@EnableDiscoveryClient
@EnableResourceServer
@SpringBootApplication
public class TutoroApplication implements ApplicationRunner {
    public static final DateTimeFormatter FORMATTER = ofPattern("dd-MM-yyyy");

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TutorService tutorService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private LearnRelationService learnRelationService;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    public static void main(String[] args) {

        SpringApplication.run(TutoroApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

        if (activeProfile.equals("default")) {

            Tutor adam = createTestUser("adam");
            Tutor beth = createTestUser("beth");
            Tutor charles = createTestUser("charles");
            Tutor dwayne = createTestUser("dwayne");
            Tutor elen = createTestUser("elen");

            Skill cooking = Skill.builder()
                    .name("cooking")
                    .tutorId(adam.getId())
                    .build();

            Skill programing = Skill.builder()
                    .name("programing")
                    .tutorId(dwayne.getId())
                    .build();

            skillService.saveSkill(cooking);
            skillService.saveSkill(programing);

            LearnRelation adamTeachingCooking = LearnRelation.builder()
                    .skillId(cooking.getId())
                    .teacherId(adam.getId())
                    .studentId(charles.getId())
                    .startDate(LocalDate.now())
                    .build();

            LearnRelation adamTeachingCooking2 = LearnRelation.builder()
                    .skillId(cooking.getId())
                    .teacherId(adam.getId())
                    .studentId(beth.getId())
                    .startDate(LocalDate.now())
                    .build();

            LearnRelation dwayneTeachingProgramming = LearnRelation.builder()
                    .skillId(programing.getId())
                    .teacherId(dwayne.getId())
                    .studentId(beth.getId())
                    .startDate(LocalDate.now())
                    .build();

            LearnRelation elenTeachingProgramming = LearnRelation.builder()
                    .skillId(programing.getId())
                    .teacherId(elen.getId())
                    .studentId(adam.getId())
                    .startDate(LocalDate.now())
                    .build();

            learnRelationService.saveLearnRelation(adamTeachingCooking);
            learnRelationService.saveLearnRelation(adamTeachingCooking2);
            learnRelationService.saveLearnRelation(dwayneTeachingProgramming);
            learnRelationService.saveLearnRelation(elenTeachingProgramming);
        }
    }

    private Tutor createTestUser(String username) {
        Tutor tutor = Tutor.builder()
                .username(username)
                .name(username)
                .email(username + "@gmail.com")
                .build();

        UserDto dto = new UserDto();
        dto.setUsername(username);
        dto.setPassword("test");
        dto.setEmail(username + "@gmail.com");

        tutorService.saveTutor(tutor);
        String token = userService.saveNewUser(dto);
        userService.verifyToken(UUID.fromString(token));

        return tutor;
    }

    @Primary
    @Bean
    public RestTemplate getCustomRestTemplate() {
        RestTemplate template = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = template.getInterceptors();
        if (interceptors == null) {
            template.setInterceptors(Collections.singletonList(
                    new UserContextInterceptor()));
        } else {
            interceptors.add(new UserContextInterceptor());
            template.setInterceptors(interceptors);
        }
        return template;
    }

    @Bean
    @Primary
    public ObjectMapper serializingObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer());
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        objectMapper.registerModule(javaTimeModule);
        return objectMapper;
    }

    public class LocalDateSerializer extends JsonSerializer<LocalDate> {

        @Override
        public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(value.format(FORMATTER));
        }
    }

    public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
        @Override
        public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            return LocalDate.parse(p.getValueAsString(), FORMATTER);
        }
    }
}
