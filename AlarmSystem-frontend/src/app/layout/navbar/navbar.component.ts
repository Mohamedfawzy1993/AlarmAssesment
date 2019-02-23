import { Component, OnInit } from '@angular/core';
import {SharedSubjectService} from '../../shared/shared-subject-service.service';
import {ConfigParams} from '../../shared/config/config-params';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']})
export class NavbarComponent implements OnInit {

  constructor(private sharedService: SharedSubjectService) { }
  config = ConfigParams;
  ngOnInit() {
  }

  updateCharts() {
    console.log('emitting');
    this.sharedService.dashboardRefreshEvent.emit(1);
  }
}
