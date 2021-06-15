package com.example.demo.repositories;

import com.example.demo.model.ProposalData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProposalDataRepository extends PagingAndSortingRepository<ProposalData, Long> {

    Page<ProposalData> findAll(Pageable pageable);
}
