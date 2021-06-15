package com.example.demo.model;

import com.example.demo.model.exceptions.ActionNotPermitedException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProposalStatusCreated implements ProposalStatusActions {
    @Override
    public ProposalStatus delete(ProposalData proposalData) {
        log.info("Deleting...");
        return ProposalStatus.DELETED;
    }

    @Override
    public ProposalStatus verify(ProposalData proposalData) {
        log.info("Verifying...");
        return ProposalStatus.VERYFIED;
    }

    @Override
    public ProposalStatus accept(ProposalData proposalData) {
        throw new ActionNotPermitedException("accept");
    }

    @Override
    public ProposalStatus reject(ProposalData proposalData) {
        throw new ActionNotPermitedException("reject");

    }

    @Override
    public ProposalStatus publish(ProposalData proposalData) {
        throw new ActionNotPermitedException("publish");

    }
}
