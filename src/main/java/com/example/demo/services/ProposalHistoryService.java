package com.example.demo.services;

import com.example.demo.model.ProposalData;
import com.example.demo.model.ProposalHistory;

import java.util.List;
import java.util.Optional;

public interface ProposalHistoryService {

    Optional<ProposalHistory> getProposalHistoryById(Long id);
    ProposalHistory createProposalHistory(ProposalHistory proposalHistory);
    Optional<List<ProposalHistory>> getProposalHistoryByProposalId(Long id);
    Optional<List<ProposalHistory>> getAllProposalHistory();

}
