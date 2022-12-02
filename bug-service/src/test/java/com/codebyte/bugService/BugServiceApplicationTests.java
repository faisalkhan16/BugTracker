package com.codebyte.bugService;

import com.codebyte.bugservice.dto.BugRequestDTO;
import com.codebyte.bugservice.dto.CommentRequestDTO;
import com.codebyte.bugservice.mapper.BugMapper;
import com.codebyte.bugservice.repository.BugRepository;
import com.codebyte.bugservice.repository.CommentRepository;
import com.codebyte.bugservice.service.BugService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BugServiceApplicationTests {

    @Autowired
    private BugRepository bugRepository;

    @Autowired
    private BugService bugService;

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BugMapper bugMapper;

    @Test
    @Order(1)
    void shouldCreateBug() throws Exception {
        BugRequestDTO bugRequestDTO = getBugRequest();
        String bugRequestString = objectMapper.writeValueAsString(bugRequestDTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/bugs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bugRequestString))
                .andExpect(status().isCreated());
        Assertions.assertEquals(3, bugRepository.findAll().size());
    }

    @Test
    @Order(2)
    void shouldFetchBugs() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bugs")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Assertions.assertEquals(3, bugRepository.findAll().size());
    }

    @Test
    @Order(3)
    void shouldUpdateBug() throws Exception {
        BugRequestDTO bugRequestDTO = getUpdatedBugRequest();
        String bugUpdatedRequestString = objectMapper.writeValueAsString(bugRequestDTO);
        mockMvc.perform(MockMvcRequestBuilders.patch("/bug/{bug_id}",1l)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bugUpdatedRequestString))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(4)
    void shouldDeleteBug() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/bug/{bug_id}",1l)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        Assertions.assertEquals(2, bugRepository.findAll().size());
    }


    @Test
    @Order(5)
    void shouldCreateComment() throws Exception {
        CommentRequestDTO commentRequestDTO = getCommentRequest();
        String bugRequestString = objectMapper.writeValueAsString(commentRequestDTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bugRequestString))
                .andExpect(status().isCreated());
        Assertions.assertEquals(3, commentRepository.findByBugId(commentRequestDTO.getBugId()).size());
    }

    @Test
    @Order(6)
    void shouldFetchCommentsByBugId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bugs/{bug_id}/comments",2l)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Assertions.assertEquals(3, commentRepository.findByBugId(2l).size());
    }

    @Test
    @Order(7)
    void shouldUpdateComment() throws Exception {
        CommentRequestDTO commentRequestDTO = getUpdatedCommentRequest();
        String bugUpdatedRequestString = objectMapper.writeValueAsString(commentRequestDTO);
        mockMvc.perform(MockMvcRequestBuilders.patch("/comment/{comment_id}","01GH225V8G8JWEA4PNR4QACD4Y")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bugUpdatedRequestString))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(8)
    void shouldDeleteComment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/comment/{comment_id}","01GH22524S13YQV8GCG1XVNYSP")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        Assertions.assertEquals(2, commentRepository.findAll().size());
    }

    private CommentRequestDTO getCommentRequest() {
        return CommentRequestDTO.builder()
                .description("User Service Is Not Working kindly fixed the bug")
                .bugId(2l).build();
    }

    private CommentRequestDTO getUpdatedCommentRequest() {
        return CommentRequestDTO.builder()
                .description("User Service Is Working")
                .bugId(2l).build();
    }

    private BugRequestDTO getBugRequest() {
       return BugRequestDTO.builder()
                .name("User Service Bug")
        .description("User Service Is Not Working")
                .status(0)
                .severity(3)
                .productId("01GGWM8SNJ94HTBXSK8BFT2VA6")
                .assignByUserId("01GGWK8CADQC31446200AVN5SA")
                .assignToUserId("01GGTFP1AJA2C4P0HNM0R9YPDG").build();
    }

    private BugRequestDTO getUpdatedBugRequest() {
        return BugRequestDTO.builder()
                .name("User Service Bug")
                .description("User Service Is Not Working Update")
                .status(1)
                .severity(1)
                .productId("01GGWM8SNJ94HTBXSK8BFT2VA6")
                .assignByUserId("01GGWK8CADQC31446200AVN5SA")
                .assignToUserId("01GGTFP1AJA2C4P0HNM0R9YPDG").build();
    }

    @Test
    @Order(9)
    void deleteAllBugs() throws Exception {
        bugRepository.deleteAll();
        Assertions.assertEquals(0, bugRepository.findAll().size());
    }


    @Test
    @Order(10)
    void saveBug() throws Exception {
        BugRequestDTO bugRequestDTO = getBugRequest();
        bugService.saveBug(bugRequestDTO);
        Assertions.assertEquals(1, bugService.getBugs().size());
    }
    
}
