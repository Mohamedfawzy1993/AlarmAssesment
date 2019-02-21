import {Component, OnInit} from '@angular/core';
import {Chart} from '../../style/js/chart.js/Chart.min.js';
import {StatisticsService} from './statistics.service';
import {SharedSubjectService} from '../shared/shared-subject-service.service';
import {Router} from '@angular/router';
import {ConfigParams} from '../config/config-params';

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css'],
  providers: [StatisticsService]
})
export class StatisticsComponent implements OnInit {
  title = 'Dashboards';

  constructor(private statService: StatisticsService,
              private sharedService: SharedSubjectService,
              private router: Router) {
  }

  myLineChart: Chart;
  areaDataObject = {
    type: 'line',
    data: {
      labels: ['Warning', 'Minor', 'Major', 'Critical'],
      datasets: [{
        label: 'Alarms',
        lineTension: 0.3,
        backgroundColor: 'rgba(2,117,216,0.2)',
        borderColor: 'rgba(2,117,216,1)',
        pointRadius: 5,
        pointBackgroundColor: 'rgba(2,117,216,1)',
        pointBorderColor: 'rgba(255,255,255,0.8)',
        pointHoverRadius: 5,
        pointHoverBackgroundColor: 'rgba(2,117,216,1)',
        pointHitRadius: 50,
        pointBorderWidth: 2,
        data: [],
      }],
    },
    options: {
      scales: {
        xAxes: [{
          time: {
            unit: 'date'
          },
          gridLines: {
            display: false
          },
          ticks: {
            maxTicksLimit: 7
          }
        }],
        yAxes: [{
          ticks: {
            min: 0,
            max: 40000,
            maxTicksLimit: 4
          },
          gridLines: {
            color: 'rgba(0, 0, 0, .125)',
          }
        }],
      },
      legend: {
        display: true
      }
    }
  };
  pieDataObject = {
    type: 'pie',
    data: {
      labels: ['Active', 'Ceased'],
      datasets: [{
        data: [],
        backgroundColor: ['#007bff', '#dc3545'],
      }],
    },
  };
  barDataObject = {
    type: 'bar',
    data: {
      labels: [],
      datasets: [{
        label: 'Alarms',
        backgroundColor: 'rgba(2,117,216,1)',
        borderColor: 'rgba(2,117,216,1)',
        data: [],
      }],
    },
    options: {
      scales: {
        xAxes: [{
          time: {
            unit: 'month'
          },
          gridLines: {
            display: false
          },
          ticks: {
            maxTicksLimit: 5
          }
        }],
        yAxes: [{
          ticks: {
            min: 0,
            max: 15000,
            maxTicksLimit: 4
          },
          gridLines: {
            display: true
          }
        }],
      },
      legend: {
        display: false
      }
    }
  };

  ngOnInit() {
    document.getElementById('bread').innerText = this.title;
    document.getElementById('title').innerText = this.title;
    this.initCharts();

    // Refresh Charts on Firing Event "Click on Menu Dashboard Item"
    this.sharedService.dashboardRefreshEvent.subscribe(
      success => {

        const route = this.router.url;
        if (route == '/' + ConfigParams.DASHBOARD_ROUTE || route == '/' + ConfigParams.HOME_ROUTE) {
          console.log('Event Emitted');
          this.initCharts();
        }

      }
    );
  }


  areaChartInit() {
    const ctx = document.getElementById('myAreaChart');
    // this.areaDataObject.data.datasets[0].data = [];
    this.statService.findAlarmSeverityStatistics().subscribe(
      (success: any) => {
        const data: number[] = [];
        if (success != null && success.data[0] != null) {
          data.push((success.data[0].warning != null ? success.data[0].warning : 0));
          data.push(success.data[0].minor != null ? success.data[0].minor : 0);
          data.push(success.data[0].major != null ? success.data[0].major : 0);
          data.push(success.data[0].critical != null ? success.data[0].critical : 0);
          //
          const maxTrend = Math.max(...data);
          this.areaDataObject.data.datasets[0].data = data;
          this.areaDataObject.options.scales.yAxes[0].ticks.max = Math.ceil(maxTrend / 10) * 10;
          this.myLineChart = new Chart(ctx, this.areaDataObject);
        }
      }
    );
  }


  pieChartInit() {

    const ctx = document.getElementById('myPieChart');


    this.statService.findAlarmActiveAndCeasedStatistics().subscribe(
      (success: any) => {
        const data = [success.data[0].Active, success.data[0].Ceased];
        this.pieDataObject.data.datasets[0].data = data;
        const myPieChart = new Chart(ctx, this.pieDataObject);
      }
    );

  }

  barCharInit() {
    const ctx = document.getElementById('myBarChart');

    this.statService.findTopFiveSites().subscribe(
      (success: any) => {
        this.barDataObject.data.labels = Object.keys(success.data[0]);
        this.barDataObject.data.datasets[0].data = Object.values(success.data[0]);
        const values: number[] = Object.values(success.data[0]);
        const maxTrend = Math.max(...values);
        this.barDataObject.options.scales.yAxes[0].ticks.max = Math.ceil(maxTrend / 10) * 10;
        const myLineChart = new Chart(ctx, this.barDataObject);


      }
    );

  }


  initCharts() {
    this.areaChartInit();
    this.pieChartInit();
    this.barCharInit();
  }


}
