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

  constructor(private standingServiceService:StandingServiceService) { }

  ngOnInit(): void {
    this.standingServiceService.findAll().subscribe(data =>{this.standings = data;});
    this.standingServiceService.findAllLeagues().subscribe(data =>{this.leagues = data;})
  }

}
