import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Post} from "../model/post";
import {Standing} from "../model/standing";

@Injectable({
  providedIn: 'root'
})
export class StandingServiceService {

  private standingUrl: string;

  constructor(private http: HttpClient) {
    this.standingUrl = "http://localhost:8080/standings/2002";
  }

  public findAll(): Observable<Standing[]> {
    return this.http.get<Standing[]>(this.standingUrl);
  }
}