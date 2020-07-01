package com.rob.video.server.integration.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.rob.video.server.model.Comment;
import com.rob.video.server.model.Instructor;
import com.rob.video.server.model.Style;
import com.rob.video.server.model.Tag;
import com.rob.video.server.model.Technique;
import com.rob.video.server.model.Video;
import com.rob.video.server.repository.VideoRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(
		  locations = "classpath:application-integrationtest.properties")
public class VideoServerIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private VideoRepository videoRepository;

    Video video;
    
    private static String FIRST_COMMENT = "First comment for video";
    
    @Before
    public void setUp() {
    	video = new Video("Delayed Sword", "delayed-sword.mkv", "C://folder//location//delayed-sword.mkv", "This delayed sword");
    	Instructor instructor = new Instructor("Eddie Downey");
    	Technique technique = new Technique("Delayed Sword");
    	Technique technique2 = new Technique("Right punch");
    	Style style = new Style("Kenpo");
    	Style style2 = new Style("Karate");
    	Tag tag = new Tag("yellow belt");
    	Comment comment = new Comment(FIRST_COMMENT);
    	video.getInstructors().add(instructor);
    	video.getTechniques().add(technique);
    	video.getTechniques().add(technique2);
    	video.getStyles().add(style);
    	video.getStyles().add(style2);
    	video.getComments().add(comment);
    	entityManager.persist(video);
        entityManager.flush();	
    }
    
    @Test
    public void testGetAllVideos() {
    	List<Video> found = videoRepository.findAll();
    	assertEquals(1, found.size());
    }
    
    @Test
    public void testGetByInstructorName() {
    	List<Video> found = videoRepository.findByInstructors_Name("Eddie Downey");
    	Video foundVideo = found.get(0);
    	assertEquals(video, foundVideo);
    }
    
    @Test
    public void testGetByInstructorNameWithWrongInstructor() {
    	List<Video> found = videoRepository.findByInstructors_Name("Richard Mullen");
    	assertEquals(0, found.size());
    }
    
    @Test
    public void testGetByStyleName() {
    	List<Video> found = videoRepository.findByStyles_Name("Kenpo");
    	Video foundVideo = found.get(0);
    	assertEquals(video, foundVideo);
    }
    
    @Test
    public void testGetByStyleNameWithWrongStyle() {
    	List<Video> found = videoRepository.findByInstructors_Name("Shotokan");
    	assertEquals(0, found.size());
    }
    
    @Test
    public void testGetByTechniqueName() {
    	List<Video> found = videoRepository.findByTechniques_Name("Delayed Sword");
    	Video foundVideo = found.get(0);
    	assertEquals(video, foundVideo);
    	
    	found = videoRepository.findByTechniques_Name("Right punch");
    	foundVideo = found.get(0);
    	assertEquals(video, foundVideo);
    }
    
    @Test
    public void testGetByTechniqueNameWithWrongTechnique() {
    	List<Video> found = videoRepository.findByInstructors_Name("Sword");
    	assertEquals(0, found.size());
    }
    
    @Test
    public void testGetByFormNameWhenFormIsEmpty() {
    	List<Video> found = videoRepository.findByForms_Name("Short Form 1");
    	assertEquals(0, found.size());
    }
    
    @Test
    public void testCheckVideoCommentsCount() {
    	List<Video> found = videoRepository.findAll();
    	Video video = found.get(0);
    	List<Comment> comments = video.getComments();
    	assertEquals(1, comments.size());
    	assertEquals(FIRST_COMMENT, comments.get(0).getContent());
    }
    
    @Test
    public void testCheckVideoCommentsContent() {
    	List<Video> found = videoRepository.findAll();
    	Video video = found.get(0);
    	List<Comment> comments = video.getComments();
    	assertEquals(FIRST_COMMENT, comments.get(0).getContent());
    }

}
