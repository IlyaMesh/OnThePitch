package com.onthepitch.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class for league(or competition) entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LEAGUES")
public class League {
    @Id
    private Long league_id;
    private String league_title;
    private String logo;
}
