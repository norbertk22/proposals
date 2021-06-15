package com.example.demo.services;

import com.example.demo.model.ProposalData;
import com.example.demo.repositories.ProposalDataRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import java.util.List;
import java.util.Optional;

@Service
public class ProposalDataServiceImpl implements ProposalDataService {

    private final ProposalDataRepository proposalDataRepository;

    public ProposalDataServiceImpl(ProposalDataRepository proposalDataRepository) {
        this.proposalDataRepository = proposalDataRepository;
    }

    @Override
    public Optional<ProposalData> getProposalDataById(Long Id) {
        return proposalDataRepository.findById(Id);
    }

    @Override
    public ProposalData createProposalData(ProposalData proposalData) {
        return proposalDataRepository.save(proposalData);
    }

    @Override
    public ProposalData updateProposalData(ProposalData proposalData) {
        return proposalDataRepository.save(proposalData);
    }

    @Override
    public Page<ProposalData> getAllProposal(Pageable pageable) {
        return proposalDataRepository.findAll(pageable);
    }
}
