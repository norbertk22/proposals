package com.example.demo.repositories;

import com.example.demo.model.ProposalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProposalHistoryRepository extends JpaRepository<ProposalHistory, Long> {

}
