import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Club} from "../model/club";

@Injectable({
  providedIn: 'root'
})
export class ClubServiceService {
  private clubUrl : string;

  constructor(private http: HttpClient) {
    this.clubUrl = "http://localhost:8080/clubs";
  }
  getById(id) : Observable<Club>{
    return this.http.get<Club>(this.clubUrl+'/'+id);
  }
  getAll(): Observable<Club[]>{
    return this.http.get<Club[]>(this.clubUrl);
  }
}
