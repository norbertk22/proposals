package com.example.demo.model;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProposalStateNew implements ProposalState {

    @Override
    public void create() {
      log.info("State to -> CREATED");
    }

    @Override
    public void delete() {
        throw new Error("State not possible");
    }

    @Override
    public void veryfi() {
        throw new Error("State not possible");
    }

    @Override
    public void reject() {
        throw new Error("State not possible");
    }

    @Override
    public void accept() {
        throw new Error("State not possible");
    }

    @Override
    public void publish() {
        throw new Error("State not possible");
    }
}
