package com.onthepitch.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "LOGS")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOG_SEQ")
    @SequenceGenerator(sequenceName = "LOGS_SEQ", allocationSize = 1, name = "LOG_SEQ")
    private Long id;

    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private User user;

    private Date created_at;
}
