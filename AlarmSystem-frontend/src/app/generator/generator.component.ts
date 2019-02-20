import { Component, OnInit } from '@angular/core';
import {GeneratorService} from './generator.service';

@Component({
  selector: 'app-generator',
  templateUrl: './generator.component.html',
  styleUrls: ['./generator.component.css'],
  providers: [GeneratorService]
})
export class GeneratorComponent implements OnInit {

  title = 'Generator';
  generatorLog = '';
  generatorStatus;
  generatorInterval = 5000;
  constructor(private generatorService: GeneratorService) { }

  ngOnInit() {
    console.log('ngCalled');
    document.getElementById('bread').innerText = this.title;
    document.getElementById('title').innerText = this.title;
    this.generatorService.findSchedulerStatus().subscribe(
      (success: any) => {
        this.generatorStatus = success.isRunning;
      }
    );
  }

  startGenerator() {
    this.generatorService.startScheduler(this.generatorInterval).subscribe(
      success => {
        this.generatorStatus = 1;
      }
    );
  }

  stopGenerator() {
    this.generatorService.stopScheduler().subscribe(
      success => {
        this.generatorStatus = 0;
      }
    );
  }

}
