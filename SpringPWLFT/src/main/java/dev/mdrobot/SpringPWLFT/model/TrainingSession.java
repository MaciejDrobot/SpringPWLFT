package dev.mdrobot.SpringPWLFT.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity

@Table(name = "training_session")
public class TrainingSession {

    @Id
    @Column(name = "session_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long sessionId;

    @Column(name = "date")
    private LocalDate date;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "trainingSession", cascade = CascadeType.ALL)
    private List<Exercise> exerciseList = new ArrayList<>();

    @Column(name = "comments")
    private String comments;

    public TrainingSession() {

    }

}
