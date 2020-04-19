import {Component, OnInit} from '@angular/core';
import {Club} from "../model/club";
import {ActivatedRoute, Router} from "@angular/router";
import {ClubServiceService} from "../service/club-service.service";
import {Comment} from "../model/comment";
import {TokenStorageService} from "../service/token-storage.service";
import {User} from "../model/user";

@Component({
  selector: 'app-club-view',
  templateUrl: './club-view.component.html',
  styleUrls: ['./club-view.component.css']
})
export class ClubViewComponent implements OnInit {
  club: Club;
  currentUser:User;
  club_id: number;
  isLoggedIn = false;
  isError = false;
  isPressed = false;
  errorMessage = '';

  constructor(
    private route: ActivatedRoute,
    private clubService: ClubServiceService,
    private tokenStorageService: TokenStorageService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    this.currentUser = this.tokenStorageService.getUser();
    this.club = new Club();
    this.club_id = this.route.snapshot.params['club_id'];
    this.clubService.getById(this.club_id).subscribe(data => {
      this.club = data;
    })
    if(this.currentUser.club_id == this.club_id){
      this.isPressed = true;
    }
    console.log(this.isPressed);
    console.log(this.club);
  }
  onStar(club_id) {
    if(!this.isLoggedIn){
      this.gotoLoginForm();
    }
    else {
    this.isPressed = true;
    this.clubService.update(this.club_id).subscribe(result => this.reloadPage());
    this.currentUser.club_id = this.club_id;
    this.tokenStorageService.saveUser(this.currentUser);
    }
  }
  gotoLoginForm() {
    this.router.navigate(['/login']);
  }

  reloadPage() {
    window.location.reload();
  }

}
