import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Log} from "../model/log";
import {Observable} from "rxjs";
import {PageLog} from "../model/page-log";

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

  findLogs(page,size):Observable<PageLog>{
    return this.http.get<PageLog>(API_URL_LOGS+'?page='+page+'&size='+size,httpOptions);
  }
}
