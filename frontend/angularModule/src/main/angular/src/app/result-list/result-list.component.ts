import {Component, OnInit} from '@angular/core';
import {MatchResult} from "../model/match-result";
import {ResultServiceService} from "../service/result-service.service";
import {PageMatch} from "../model/page-match";
import {TokenStorageService} from "../service/token-storage.service";

@Component({
  selector: 'app-result-list',
  templateUrl: './result-list.component.html',
  styleUrls: ['./result-list.component.css']
})
export class ResultListComponent implements OnInit {
  //results:MatchResult[];
  pageMatch : PageMatch ;
  selectedPage : number = 0;
  size:number = 7;
  isLoggedIn = false;
  //pages:Array<number>;

  // getClient(): void {
  //   this.resultService.get()
  //     .subscribe(clients => this.clients = clients);
  // }

  isZero(number){
    return !(number == null);
  }
  getPageClient(page:number): void {
    if(!this.isLoggedIn){
    this.resultService.getPageMatch(page,this.size)
      .subscribe(page => {
        this.pageMatch = page;
      });
    }
    else {
      this.resultService.getPageFavMatch(page,this.size)
        .subscribe(page => {
          this.pageMatch = page;
          console.log(this.pageMatch);
        });
    }
  }
  constructor(private resultService:ResultServiceService, private tokenStorageService: TokenStorageService,) { }

  // setPage(i,event:any){
  //   this.page=i;
  //   window.location.reload();
  // }
  onSelect(page: number): void {
    console.log("selected page : "+page);
    this.selectedPage=page;
    this.getPageClient(page);
  }
  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    this.getPageClient(0);
    //   console.log(data);
      //this.results = data;
      //this.pages = new Array(data[8]);
    //});
  }

}
