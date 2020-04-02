import {Component, OnInit} from '@angular/core';
import {MatchResult} from "../model/match-result";
import {ResultServiceService} from "../service/result-service.service";

@Component({
  selector: 'app-result-list',
  templateUrl: './result-list.component.html',
  styleUrls: ['./result-list.component.css']
})
export class ResultListComponent implements OnInit {
  page:number = 1;
  results:MatchResult[];
  //pages:Array<number>;

  constructor(private resultService:ResultServiceService) { }

  // setPage(i,event:any){
  //   this.page=i;
  //   window.location.reload();
  // }

  ngOnInit(): void {
    this.resultService.findAll(this.page).subscribe(data =>{
      console.log(data);
      this.results = data;
      //this.pages = new Array(data[8]);
    });
  }

}
