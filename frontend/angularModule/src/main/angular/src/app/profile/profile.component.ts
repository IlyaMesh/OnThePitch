import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../service/token-storage.service";
import {User} from "../model/user";
import {Club} from "../model/club";
import {ClubServiceService} from "../service/club-service.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  currentUser: User;
  roles: String[];
  club: Club;

  constructor(private token: TokenStorageService, private clubService: ClubServiceService) { }

  ngOnInit(): void {
    this.roles = this.token.getUser().roles;
    this.currentUser = this.token.getUser();
    console.log(this.currentUser.role);
    this.club = new Club();
    this.clubService.getById(this.currentUser.club_id).subscribe(data => {
      this.club = data;
    })
  }

}
