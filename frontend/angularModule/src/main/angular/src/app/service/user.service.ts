import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../model/user";

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

  getUserList():Observable<User[]>{
    return this.http.get<User[]>(API_URL);
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
