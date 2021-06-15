package com.example.demo.model;

import com.example.demo.model.exceptions.ActionNotPermitedException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProposalStatusVerified implements ProposalStatusActions {

    @Override
    public ProposalStatus delete(ProposalData proposalData) {
        throw new ActionNotPermitedException("delete");
    }

    @Override
    public ProposalStatus verify(ProposalData proposalData) {
        throw new ActionNotPermitedException("verify");
    }

    @Override
    public ProposalStatus accept(ProposalData proposalData) {
        log.info("Accepting...");
        return ProposalStatus.ACCEPTED;
    }

    @Override
    public ProposalStatus reject(ProposalData proposalData) {
        log.info("Rejecting...");
        return ProposalStatus.REJECTED;
    }

    @Override
    public ProposalStatus publish(ProposalData proposalData) {
        throw new ActionNotPermitedException("publish");
    }

}
