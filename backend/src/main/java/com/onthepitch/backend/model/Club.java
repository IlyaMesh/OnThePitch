package com.onthepitch.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CLUBS")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "CLUBS_SEQ")
    @SequenceGenerator(sequenceName = "CLUB_SEC",allocationSize = 1,name = "CLUBS_SEQ")
    private Long club_id;
    private String club_name;
    private String logo;
    private String stadium;
    private String club_info;

    public Club() {
    }

    public Club(String club_name,String logo,String stadium,String club_info){
        this.club_name = club_name;
        this.logo = logo;
        this.stadium = stadium;
        this.club_info = club_info;
    }

    public Long getClub_id() {
        return club_id;
    }

    public void setClub_id(Long club_id) {
        this.club_id = club_id;
    }

    public String getClub_name() {
        return club_name;
    }

    public void setClub_name(String club_name) {
        this.club_name = club_name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getClub_info() {
        return club_info;
    }

    public void setClub_info(String club_info) {
        this.club_info = club_info;
    }
}
