package com.onthepitch.backend.commands;

public class ClubForm {
    private Long club_id;
    private String club_name;
    private String logo;
    private String stadium;
    private String club_info;

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
