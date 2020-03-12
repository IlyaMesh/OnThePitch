import {Component, OnInit} from '@angular/core';
import {MatchResult} from "../model/match-result";
import {ResultServiceService} from "../service/result-service.service";

@Component({
  selector: 'app-result-list',
  templateUrl: './result-list.component.html',
  styleUrls: ['./result-list.component.css']
})
export class ResultListComponent implements OnInit {

  results:MatchResult[];

  constructor(private resultService:ResultServiceService) { }

  ngOnInit(): void {
    this.resultService.findAll().subscribe(data =>{this.results = data;});
  }

}
