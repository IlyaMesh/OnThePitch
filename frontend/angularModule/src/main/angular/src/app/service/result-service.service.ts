import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {MatchResult} from "../model/match-result";

@Injectable({
  providedIn: 'root'
})
export class ResultServiceService {
  private matchResultUrl: string;

  constructor(private http: HttpClient) {
    this.matchResultUrl = "http://localhost:8080/matches";
  }

  public findAll(): Observable<MatchResult[]> {
    return this.http.get<MatchResult[]>(this.matchResultUrl);
  }
}
