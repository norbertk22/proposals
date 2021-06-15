package com.example.demo.model.exceptions;


public class ProposalNotFoundException extends RuntimeException {

    public ProposalNotFoundException(Long id) {
        super("Proposal not found:" + id);
    }
}
