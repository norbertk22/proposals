package com.example.demo.model;

public interface ProposalStatusActions {

    ProposalStatus delete(ProposalData proposalData);

    ProposalStatus verify(ProposalData proposalData);

    ProposalStatus accept(ProposalData proposalData);

    ProposalStatus reject(ProposalData proposalData);

    ProposalStatus publish(ProposalData proposalData);
}
