package com.example.demo.model;

import com.example.demo.model.exceptions.ActionNotPermitedException;

public class ProposalStatusRejected implements ProposalStatusActions {
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
