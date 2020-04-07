import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class RatingServiceService {
  private ratingUrl: string;
  constructor(private http: HttpClient) {
    this.ratingUrl = "http://localhost:8080/note/";
  }

  setLike(note_id:number):Observable<any>{
    return this.http.get(this.ratingUrl+note_id+'/like',httpOptions);
  }
  setDislike(note_id:number){
   return this.http.get(this.ratingUrl+note_id+'/dislike',httpOptions);
  }
}
