import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {MatchResult} from "../model/match-result";
import {PageMatch} from "../model/page-match";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class ResultServiceService {
  private matchResultUrl: string;

  constructor(private http: HttpClient) {
    this.matchResultUrl = "http://localhost:8080/matches";
  }

  getPageMatch(page:number, size:number): Observable<PageMatch> {
    var url = this.matchResultUrl;
    url = url+'?page='+page+'&size='+size;
    return this.http.get<PageMatch>(url)
      .pipe(
        map(response => {
          const data = response;
          console.log(data.content);
          return data;
        }));
  }
  getPageFavMatch(page:number, size:number): Observable<PageMatch> {
    var url = this.matchResultUrl;
    url = url+'/favourite'+'?page='+page+'&size='+size;
    return this.http.get<PageMatch>(url)
      .pipe(
        map(response => {
          const data = response;
          console.log(data.content);
          return data;
        }));
  }
  public findAll(page:number): Observable<MatchResult[]> {
    return this.http.get<MatchResult[]>(this.matchResultUrl+'?page='+page);
  }
}
