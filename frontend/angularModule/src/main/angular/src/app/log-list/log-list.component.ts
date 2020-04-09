import { Component, OnInit } from '@angular/core';
import {LogServiceService} from "../service/log-service.service";
import {Log} from "../model/log";

@Component({
  selector: 'app-log-list',
  templateUrl: './log-list.component.html',
  styleUrls: ['./log-list.component.css']
})
export class LogListComponent implements OnInit {

  logs:Log[];

  constructor(private logService:LogServiceService) { }

  ngOnInit(): void {
    this.logService.findLogs().subscribe(data=>{this.logs = data});
  }

}
