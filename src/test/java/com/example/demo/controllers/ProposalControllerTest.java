package com.example.demo.controllers;

import com.example.demo.model.Comment;
import com.example.demo.model.ProposalData;
import com.example.demo.model.ProposalStatus;
import com.example.demo.services.ProposalDataService;
import com.example.demo.services.ProposalHistoryService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProposalController.class)
class ProposalControllerTest {

    @MockBean
    ProposalDataService proposalDataService;

    @MockBean
    ProposalHistoryService proposalHistoryService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getAll() throws Exception {
        List<ProposalData> proposalDataMock = this.prepareMockData();

        Mockito.when(proposalDataService.getAllProposal(any())).thenReturn(new PageImpl<ProposalData>(proposalDataMock, PageRequest.of(0, 10, Sort.by("title")), proposalDataMock.size()));

        mockMvc.perform(get("/api/v1/proposals/?page=1&size=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(4)))
                .andExpect(jsonPath("$[0].title", Matchers.is("one")));
    }

    @Test
    void getOne() throws Exception {
        Mockito.when(proposalDataService.getProposalDataById(any())).thenReturn(Optional.of(this.prepareMockData().get(0)));

        mockMvc.perform(get("/api/v1/proposals/1/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", Matchers.is("one")));
    }

    @Test
    void veryfi() throws Exception {
        Mockito.when(proposalDataService.getProposalDataById(any())).thenReturn(Optional.of(this.prepareMockData().get(0)));

        mockMvc.perform(patch("/api/v1/proposals/verify/1/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Matchers.is("VERYFIED")));
    }

    @Test
    void delete() throws Exception {
        Mockito.when(proposalDataService.getProposalDataById(any())).thenReturn(Optional.of(this.prepareMockData().get(0)));

        mockMvc.perform(patch("/api/v1/proposals/delete/1/").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(Comment.builder().description("time to delete").build())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Matchers.is("DELETED")));
    }

    @Test
    void failedDelete() throws Exception {
        Mockito.when(proposalDataService.getProposalDataById(any())).thenReturn(Optional.of(this.prepareMockData().get(0)));

        mockMvc.perform(patch("/api/v1/proposals/delete/1/"))
                .andExpect(status().is4xxClientError())
                .andExpect(mvcResult -> assertEquals("Required request body is missing: com.example.demo.model.ProposalData com.example.demo.controllers.ProposalController.updateProposal(java.lang.Long,com.example.demo.model.Comment)", mvcResult.getResolvedException().getMessage()));

    }

    @Test
    void accept() throws Exception {
        Mockito.when(proposalDataService.getProposalDataById(any())).thenReturn(Optional.of(this.prepareMockData().get(1)));

        mockMvc.perform(patch("/api/v1/proposals/accept/2/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Matchers.is("ACCEPTED")));
    }

    @Test
    void reject() throws Exception {
        Mockito.when(proposalDataService.getProposalDataById(any())).thenReturn(Optional.of(this.prepareMockData().get(2)));

        mockMvc.perform(patch("/api/v1/proposals/reject/2/").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(Comment.builder().description("time to delete").build())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Matchers.is("REJECTED")));
    }

    @Test
    void failedReject() throws Exception {
        Mockito.when(proposalDataService.getProposalDataById(any())).thenReturn(Optional.of(this.prepareMockData().get(2)));

        mockMvc.perform(patch("/api/v1/proposals/reject/2/"))
                .andExpect(status().is4xxClientError())
                .andExpect(mvcResult -> assertEquals("Required request body is missing: com.example.demo.model.ProposalData com.example.demo.controllers.ProposalController.rejectProposal(java.lang.Long,com.example.demo.model.Comment)", mvcResult.getResolvedException().getMessage()));
    }

    @Test
    void publish() throws Exception {
        Mockito.when(proposalDataService.getProposalDataById(any())).thenReturn(Optional.of(this.prepareMockData().get(2)));

        mockMvc.perform(patch("/api/v1/proposals/publish/2/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Matchers.is("PUBLISHED")))
                .andExpect(jsonPath("$.proposalId", Matchers.notNullValue()));
    }

    @Test
    void canNotAccept() throws Exception {
        Mockito.when(proposalDataService.getProposalDataById(any())).thenReturn(Optional.of(this.prepareMockData().get(0)));

        mockMvc.perform(patch("/api/v1/proposals/accept/2/"))
                .andExpect(status().is4xxClientError())
                .andExpect(mvcResult -> assertEquals("Action 'accept' not permited", mvcResult.getResolvedException().getMessage()));
    }

    @Test
    void canNotPublish() throws Exception {
        Mockito.when(proposalDataService.getProposalDataById(any())).thenReturn(Optional.of(this.prepareMockData().get(0)));

        mockMvc.perform(patch("/api/v1/proposals/publish/1/"))
                .andExpect(status().is4xxClientError())
                .andExpect(mvcResult -> assertEquals("Action 'publish' not permited", mvcResult.getResolvedException().getMessage()));
    }

    @Test
    void crateNew() throws Exception {

        Mockito.when(proposalDataService.createProposalData(any())).thenReturn(this.prepareMockData().get(0));

        mockMvc.perform(post("/api/v1/proposals").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(this.prepareMockData().get(0))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Matchers.is("CREATED")));
//                .andExpect(jsonPath("$.history[0].statusTo", Matchers.is("CREATED")));

    }

    @Test
    void failedCrateNew() throws Exception {

        Mockito.when(proposalDataService.createProposalData(any())).thenReturn(this.prepareMockData().get(0));


        mockMvc.perform(post("/api/v1/proposals").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(ProposalData.builder().content("missing").build())))
                .andExpect(status().is4xxClientError());
//                .andExpect(mvcResult -> assertEquals("Validation failed for argument [0] in com.example.demo.model.ProposalData com.example.demo.controllers.ProposalController.addNewProposal(com.example.demo.model.ProposalData): [Field error in object 'proposalData' on field 'title': rejected value [null]; codes [NotBlank.proposalData.title,NotBlank.title,NotBlank.java.lang.String,NotBlank]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [proposalData.title,title]; arguments []; default message [title]]; default message [title is mandatory]]", mvcResult.getResolvedException().getMessage()));


    }

    private List<ProposalData> prepareMockData() {
        List<ProposalData> proposalDataList = Arrays.asList(
                ProposalData.builder().proposalId(Long.valueOf(1)).title("one").content("some content").status(ProposalStatus.CREATED).build(),
                ProposalData.builder().proposalId(Long.valueOf(2)).title("two").content("some content").status(ProposalStatus.VERYFIED).build(),
                ProposalData.builder().proposalId(Long.valueOf(3)).title("three").content("some content").status(ProposalStatus.ACCEPTED).build(),
                ProposalData.builder().proposalId(Long.valueOf(4)).title("four").content("some content").status(ProposalStatus.CREATED).build()
        );
        return proposalDataList;
    }

}