import {Component, OnInit} from '@angular/core';
import {Club} from "../model/club";
import {ActivatedRoute, Router} from "@angular/router";
import {PostServiceService} from "../service/post-service.service";
import {Post} from "../model/post";
import {ClubServiceService} from "../service/club-service.service";

@Component({
  selector: 'app-club-view',
  templateUrl: './club-view.component.html',
  styleUrls: ['./club-view.component.css']
})
export class ClubViewComponent implements OnInit {
  club: Club;
  club_id: number;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private clubService: ClubServiceService,
  ) {}

  ngOnInit(): void {
    this.club = new Club();
    this.club_id = this.route.snapshot.params['club_id'];
    this.clubService.getById(this.club_id).subscribe(data => {
      this.club = data;
    })
  }

}
