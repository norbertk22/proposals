package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProposalData {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Long proposalId;

    @NotBlank(message = "title is mandatory")
    private String title;

    @NotBlank(message = "content is mandatory")
    private String content;

    private ProposalStatus status = ProposalStatus.CREATED;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "proposal_data_id")
    @JsonManagedReference
//    @JsonIgnore
    private List<ProposalHistory> history = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createTimestamp;

    public void delete() {
        setStatus(status.delete(this));
    }

    public void verify() {
        setStatus(status.verify(this));
    }

    public void accept() { setStatus(status.accept(this));}

    public void reject() { setStatus(status.reject(this));}

    public void publish() { setStatus(status.publish(this));}


}
