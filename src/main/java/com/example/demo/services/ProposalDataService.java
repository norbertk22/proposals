package com.example.demo.services;

import com.example.demo.model.ProposalData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProposalDataService {

    Optional<ProposalData> getProposalDataById(Long Id);
    ProposalData createProposalData(ProposalData proposalData);
    ProposalData updateProposalData(ProposalData proposalData);
    Page<ProposalData> getAllProposal(Pageable pageable);

}
