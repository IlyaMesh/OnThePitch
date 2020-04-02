import { Component, OnInit } from '@angular/core';
import {AuthService} from "../service/auth.service";
import {ClubServiceService} from "../service/club-service.service";
import {Club} from "../model/club";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form: any = {};
  clubs:Club[];
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private authService: AuthService, private clubService: ClubServiceService) { }

  ngOnInit(): void {
    this.clubService.getAll().subscribe(data=>{this.clubs=data});
  }

  onSubmit() {
    this.authService.register(this.form).subscribe(
      data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      },
      err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

}
