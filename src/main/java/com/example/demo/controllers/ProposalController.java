package com.example.demo.controllers;

import com.example.demo.model.exceptions.ActionNotPermitedException;
import com.example.demo.model.exceptions.ProposalNotFoundException;
import com.example.demo.model.*;
import com.example.demo.services.ProposalDataService;
import com.example.demo.services.ProposalHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
//@RequestMapping(value = "/api/v1/proposals", produces = "application/json", method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.POST})
@RequestMapping("/api/v1/proposals")
public class ProposalController {

    private final ProposalDataService proposalDataService;
    private final ProposalHistoryService proposalHistoryService;


    @Autowired
    public ProposalController(ProposalDataService proposalDataService, ProposalHistoryService proposalHistoryService) {
        this.proposalDataService = proposalDataService;
        this.proposalHistoryService = proposalHistoryService;
    }

    @GetMapping("/")
    List<ProposalData> getAll(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(defaultValue = "title") String sortBy
                              ) {

        Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));

        return proposalDataService.getAllProposal(paging).getContent();
    }

    @GetMapping("/{id}/")
    ProposalData getProposalById(@PathVariable Long id) {
        return proposalDataService.getProposalDataById(id).orElseThrow(() -> new ProposalNotFoundException(id));
    }

    @PostMapping
    ProposalData addNewProposal(@Valid @RequestBody ProposalData newProposalData) {
        newProposalData.setStatus(ProposalStatus.CREATED);

        ProposalData proposalData = proposalDataService.createProposalData(newProposalData);

        addHistory(proposalData, proposalData.getStatus(), null);

        return proposalData;
    }

    @PutMapping("/{id}")
    ProposalData updateProposal(@RequestBody ProposalData updateProposalData, @PathVariable Long id) {

        ProposalData proposalData = proposalDataService.getProposalDataById(id).orElseThrow(() -> new ProposalNotFoundException(id));;
        if(!proposalData.getStatus().equals(ProposalStatus.CREATED) && !proposalData.getStatus().equals(ProposalStatus.VERYFIED)) {
            throw new ActionNotPermitedException("update");
        }

        proposalData.setTitle(updateProposalData.getTitle());
        proposalData.setContent(updateProposalData.getContent());

        return proposalDataService.updateProposalData(proposalData);
    }

    @PatchMapping("/verify/{id}")
    ProposalData verifyProposal(@PathVariable Long id) {

        ProposalData proposalData = proposalDataService.getProposalDataById(id).orElseThrow(() -> new ProposalNotFoundException(id));;
        proposalData.verify();

        proposalDataService.updateProposalData(proposalData);

        addHistory(proposalData, proposalData.getStatus(), null);

        return proposalData;
    }

    @PatchMapping("/delete/{id}")
    ProposalData updateProposal(@PathVariable Long id, @Valid @RequestBody Comment comment) {

        ProposalData proposalData = proposalDataService.getProposalDataById(id).orElseThrow(() -> new ProposalNotFoundException(id));;
        proposalData.delete();

        proposalDataService.updateProposalData(proposalData);

        addHistory(proposalData, proposalData.getStatus(), comment.getDescription());

        return proposalData;
    }

    @PatchMapping("/accept/{id}")
    ProposalData acceptProposal(@PathVariable Long id) {

        ProposalData proposalData = proposalDataService.getProposalDataById(id).orElseThrow(() -> new ProposalNotFoundException(id));;
        proposalData.accept();

        proposalDataService.updateProposalData(proposalData);

        addHistory(proposalData, proposalData.getStatus(), null);

        return proposalData;
    }

    @PatchMapping("/reject/{id}")
    ProposalData rejectProposal(@PathVariable Long id, @Valid @RequestBody Comment comment) {

        ProposalData proposalData = proposalDataService.getProposalDataById(id).orElseThrow(() -> new ProposalNotFoundException(id));;
        proposalData.reject();

        proposalDataService.updateProposalData(proposalData);

        addHistory(proposalData, proposalData.getStatus(), comment.getDescription());

        return proposalData;
    }

    @PatchMapping("/publish/{id}")
    ProposalData publishProposal(@PathVariable Long id) {

        ProposalData proposalData = proposalDataService.getProposalDataById(id).orElseThrow(() -> new ProposalNotFoundException(id));;
        proposalData.publish();

        Long proposalId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        proposalData.setProposalId(proposalId);

        proposalDataService.updateProposalData(proposalData);

        addHistory(proposalData, proposalData.getStatus(), null);

        return proposalData;
    }

    private void addHistory(ProposalData proposalData, ProposalStatus status, String description) {
        ProposalHistory proposalHistory = ProposalHistory.builder()
                .proposalData(proposalData)
                .statusTo(status)
                .description(description)
                .build();
        proposalHistoryService.createProposalHistory(proposalHistory);
    }
}
