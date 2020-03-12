import { Component, OnInit } from '@angular/core';
import {Standing} from "../model/standing";
import {StandingServiceService} from "../service/standing-service.service";

@Component({
  selector: 'app-standing-list',
  templateUrl: './standing-list.component.html',
  styleUrls: ['./standing-list.component.css']
})
export class StandingListComponent implements OnInit {

  standings:Standing[];

  constructor(private standingServiceService:StandingServiceService) { }

  ngOnInit(): void {
    this.standingServiceService.findAll().subscribe(data =>{this.standings = data;});
  }

}
