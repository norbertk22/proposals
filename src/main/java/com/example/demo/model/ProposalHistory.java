package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProposalHistory {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private ProposalData proposalData;

    @CreationTimestamp
    private LocalDateTime createTimestamp;

    private String description;

    private ProposalStatus statusTo;
}
