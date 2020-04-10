package com.onthepitch.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class for club entity
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "CLUBS")
public class Club {
    @Id
    private Long club_id;
    private String club_name;
    private String logo;
    private String stadium;
    private String club_info;

    public Club(String club_name, String logo, String stadium, String club_info) {
        this.club_name = club_name;
        this.logo = logo;
        this.stadium = stadium;
        this.club_info = club_info;
    }

}
