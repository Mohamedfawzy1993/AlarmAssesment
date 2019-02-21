import { Component, OnInit } from '@angular/core';
import {$} from 'protractor';

@Component({
  selector: 'app-full-layout',
  templateUrl: './full-layout.component.html',
  styleUrls: ['./full-layout.component.scss']
})
export class FullLayoutComponent implements OnInit {

  constructor() { }
  bodyClass = '';
  sidebarClass = '';

  ngOnInit() {
  }


  // Toggle the side navigation
  toggleSidebar(e) {
    e.preventDefault();
    if(document.body.className != 'sidebar-toggled') {
      document.body.className = 'sidebar-toggled';
    } else {
      document.body.className = '';
    }
    const list = document.getElementsByClassName('sidebar');

    Array.from(list).forEach((element, i) => {
      if(this.sidebarClass == 'toggled')
      {
        element.classList.remove(this.sidebarClass);
        this.sidebarClass = '';
      }
      else{
        this.sidebarClass = 'toggled';
        element.classList.add(this.sidebarClass);
      }
    });
  }
}
