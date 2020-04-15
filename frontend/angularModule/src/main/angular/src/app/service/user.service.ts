import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../model/user";
import {PageUser} from "../model/page-user";

const API_URL = 'http://localhost:8080/admin/users';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }
  // getPublicContent(): Observable<any> {
  //   return this.http.get(API_URL + 'all', { responseType: 'text' });
  // }

  // getUserBoard(): Observable<any> {
  //   return this.http.get(API_URL + 'user', { responseType: 'text' });
  // }

  getUserList(page, size):Observable<PageUser>{
    return this.http.get<PageUser>(API_URL+'?page='+page+'&size='+size);
  }

  promote(user_id: number) {
    return this.http.get(API_URL+'/'+user_id+'/promote')
  }
  demote(user_id:number){
    return this.http.get(API_URL+'/'+user_id+'/demote');
  }

  ban(user_id: number) {
    return this.http.get(API_URL+'/'+user_id+'/ban');
  }
}
