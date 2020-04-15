import { Component, OnInit } from '@angular/core';
import {LogServiceService} from "../service/log-service.service";
import {Log} from "../model/log";
import {PageLog} from "../model/page-log";

@Component({
  selector: 'app-log-list',
  templateUrl: './log-list.component.html',
  styleUrls: ['./log-list.component.css']
})
export class LogListComponent implements OnInit {

  logPage:PageLog;
  selectedPage: number = 0;
  size: number = 5;
  page: number = 1;
  logs:Log[];

  constructor(private logService:LogServiceService) { }

  ngOnInit(): void {
    this.getPagePost(0);
  }

  onSelect(page: number): void {
    console.log("selected page : " + page);
    this.selectedPage = page;
    this.getPagePost(page);
  }

  getPagePost(page: number): void {
    this.logService.findLogs(page, this.size)
      .subscribe(page => {
        this.logPage = page;
      });
  }

}
