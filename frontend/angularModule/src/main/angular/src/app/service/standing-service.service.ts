import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Standing} from "../model/standing";
import {League} from "../model/league";

@Injectable({
  providedIn: 'root'
})
export class StandingServiceService {

  private standingUrl: string;
  private leaguesUrl:string;

  constructor(private http: HttpClient) {
    this.standingUrl = "http://localhost:8080/standings/";
    this.leaguesUrl = "http://localhost:8080/standings/leagues";
  }

  public findAll(id): Observable<Standing[]> {
    return this.http.get<Standing[]>(this.standingUrl+id);
  }
  public findAllLeagues(): Observable<League[]>{
    return this.http.get<League[]>(this.leaguesUrl);
  }
}
