import {Component, OnInit} from '@angular/core';
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
  generatorInterval = 5;

  constructor(private generatorService: GeneratorService) {
  }

  ngOnInit() {
    console.log('ngCalled');
    document.getElementById('bread').innerText = this.title;
    document.getElementById('title').innerText = this.title;
    this.generatorService.findSchedulerStatus().subscribe(
      (success: any) => {
        this.generatorStatus = success.isRunning;
      }
    );

    this.generatorService.getLog().subscribe(
      (success: any) => {
        this.generatorLog = success.join('\n');
        this.scrollTextAreaToBottom();

      }
    );
  }

  startGenerator() {
    this.generatorService.startScheduler(this.generatorInterval * 1000).subscribe(
      (success: any) => {
        this.generatorStatus = 1;
        this.generatorLog = success.join('' +
          '\n');
        this.scrollTextAreaToBottom();
      }
    );
  }

  stopGenerator() {
    this.generatorService.stopScheduler().subscribe(
      (success: any) => {
        this.generatorStatus = 0;
        this.generatorLog = success.join('' +
          '\n');
        this.scrollTextAreaToBottom();
      }
    );
  }

  scrollTextAreaToBottom() {
    const textarea = document.getElementById('logArea');
    textarea.scrollTop = textarea.scrollHeight;
  }
}
