package com.example.demo.services;

import com.example.demo.model.ProposalHistory;
import com.example.demo.repositories.ProposalHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProposalHistoryServiceImpl implements ProposalHistoryService {

    ProposalHistoryRepository proposalHistoryRepository;

    public ProposalHistoryServiceImpl(ProposalHistoryRepository proposalHistoryRepository) {
        this.proposalHistoryRepository = proposalHistoryRepository;
    }

    @Override
    public Optional<ProposalHistory> getProposalHistoryById(Long id) {
        return this.proposalHistoryRepository.findById(id);
    }

    @Override
    public ProposalHistory createProposalHistory(ProposalHistory proposalHistory) {
        return this.proposalHistoryRepository.save(proposalHistory);
    }

    @Override
    public Optional<List<ProposalHistory>> getProposalHistoryByProposalId(Long id) {
        return null;//this.proposalHistoryRepository.findByProposal_Data_Id(id);
    }

    @Override
    public Optional<List<ProposalHistory>> getAllProposalHistory() {
        return this.getAllProposalHistory();
    }
}
