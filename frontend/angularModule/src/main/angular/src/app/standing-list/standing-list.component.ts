import { Component, OnInit } from '@angular/core';
import {Standing} from "../model/standing";
import {StandingServiceService} from "../service/standing-service.service";
import {League} from "../model/league";

@Component({
  selector: 'app-standing-list',
  templateUrl: './standing-list.component.html',
  styleUrls: ['./standing-list.component.css']
})
export class StandingListComponent implements OnInit {

  standings:Standing[];
  leagues:League[];
  currentLeagueId:number;

  constructor(private standingServiceService:StandingServiceService) {
    this.currentLeagueId = 2002;
  }

  ngOnInit(): void {
    this.standingServiceService.findAll(this.currentLeagueId).subscribe(data =>{this.standings = data;});
    this.standingServiceService.findAllLeagues().subscribe(data =>{this.leagues = data;})
  }
  selectLeague(id){
    this.currentLeagueId = id;
    this.standingServiceService.findAll(this.currentLeagueId).subscribe(data=>{this.standings = data;});
  }

  // onSelect(league_id: number) {
  //   //   this.currentLeagueId = league_id;
  //   // }
}
