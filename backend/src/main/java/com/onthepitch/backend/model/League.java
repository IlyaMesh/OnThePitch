package com.onthepitch.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LEAGUES")
public class League {
    @Id
    private Long league_id;
    private String league_title;
    private String logo;

    public League() {
    }

    public League(Long league_id,String league_title,String logo){
        this.league_id = league_id;
        this.league_title = league_title;
        this.logo = logo;
    }
    public Long getLeague_id() {
        return league_id;
    }

    public void setLeague_id(Long league_id) {
        this.league_id = league_id;
    }

    public String getLeague_title() {
        return league_title;
    }

    public void setLeague_title(String league_title) {
        this.league_title = league_title;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
