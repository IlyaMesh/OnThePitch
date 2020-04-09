import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Log} from "../model/log";
import {Observable} from "rxjs";

const API_URL_LOGS = 'http://localhost:8080/admin/logs';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class LogServiceService {


  constructor(private http : HttpClient) {
  }

  findLogs():Observable<Log[]>{
    return this.http.get<Log[]>(API_URL_LOGS,httpOptions);
  }
}
