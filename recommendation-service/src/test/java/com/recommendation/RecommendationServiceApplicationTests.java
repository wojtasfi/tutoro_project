package com.recommendation;

import com.recommendation.dto.RecommendedSkillsDto;
import com.recommendation.dto.RecommendedTeachersDto;
import com.recommendation.service.SkillRecommendationSerivce;
import com.recommendation.service.TeachersRecommendationService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@Import({TestUtils.class, SkillRecommendationSerivce.class, TeachersRecommendationService.class})
@EnableNeo4jRepositories
public class RecommendationServiceApplicationTests {

    @Autowired
    private TestUtils testUtils;

    @Autowired
    private SkillRecommendationSerivce skillRecommendationSerivce;

    @Autowired
    private TeachersRecommendationService teachersRecommendationService;

    @Test
    public void contextLoads() {
    }

    @After
    public void cleanUp() {
        testUtils.clearAll();
    }

    @Test
    public void shouldRecommendCorrectSkill() {
        //given
        String charles = "charles";
        String adam = "adam";
        String beth = "beth";
        String cooking = "cooking";
        String programming = "programming";

        testUtils.createTutorNode(charles);
        testUtils.createTutorNode(adam);
        testUtils.createTutorNode(beth);

        testUtils.createSkillNode(cooking);
        testUtils.createSkillNode(programming);

        testUtils.createIsLearningRelation(charles, cooking);
        testUtils.createIsLearningRelation(beth, cooking);
        testUtils.createIsLearningRelation(beth, programming);
        testUtils.createIsLearningRelation(adam, programming);

        testUtils.createIsTeachingRelation(adam, cooking);

        //when
        RecommendedSkillsDto dto = skillRecommendationSerivce.getRecommendedSkills(charles);

        //then
        assertEquals(dto.getSkillNames().size(), 1);
        assertEquals(dto.getSkillNames().get(0), programming);

    }

    @Test
    public void shouldReccomendCorrectTeacher() {
        //given
        String beth = "beth";
        String dwayne = "dwayne";
        String elen = "elen";
        String programming = "programming";

        testUtils.createTutorNode(elen);
        testUtils.createTutorNode(beth);
        testUtils.createTutorNode(dwayne);

        testUtils.createSkillNode(programming);

        //elen and dwayne teach programming
        testUtils.createIsTeachingRelation(dwayne, programming);
        testUtils.createIsTeachingRelation(elen, programming);

        //beth is learning programming from dwayne
        testUtils.createLearningFromRelation(beth, dwayne);
        testUtils.createTeachingRelation(dwayne, beth);

        testUtils.createIsLearningRelation(beth, programming);

        //when
        List<RecommendedTeachersDto> dto =
                teachersRecommendationService.getRecommendedTeachers(beth);

        assertEquals(dto.size(), 1);
        assertEquals(dto.get(0).getTeacherUsername(), elen);
        assertEquals(dto.get(0).getSkillNames().get(0), programming);
    }

}
