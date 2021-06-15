package com.example.demo.model;

public enum ProposalStatus implements ProposalStatusActions {

    CREATED(new ProposalStatusCreated()),
    DELETED(new ProposalStatusDeleted()),
    VERYFIED(new ProposalStatusVerified()),
    REJECTED(new ProposalStatusRejected()),
    ACCEPTED(new ProposalStatusAccepted()),
    PUBLISHED(new ProposalStatusPublished());

    private final ProposalStatusActions proposalStatusActions;

    ProposalStatus(ProposalStatusActions proposalStatusActions) {
        this.proposalStatusActions = proposalStatusActions;
    }

    @Override
    public ProposalStatus delete(ProposalData proposalData) {
        return proposalStatusActions.delete(proposalData);
    }

    @Override
    public ProposalStatus verify(ProposalData proposalData) {
        return proposalStatusActions.verify(proposalData);
    }

    @Override
    public ProposalStatus accept(ProposalData proposalData) {
        return proposalStatusActions.accept(proposalData);
    }

    @Override
    public ProposalStatus reject(ProposalData proposalData) {
        return proposalStatusActions.reject(proposalData);
    }

    @Override
    public ProposalStatus publish(ProposalData proposalData) {
        return proposalStatusActions.publish(proposalData);
    }
}
