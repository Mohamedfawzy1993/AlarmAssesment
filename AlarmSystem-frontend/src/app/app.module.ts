import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { FullLayoutComponent } from './layout/full-layout/full-layout.component';
import {RouterModule, Routes} from '@angular/router';
import { NavbarComponent } from './layout/navbar/navbar.component';
import { StatisticsComponent } from './statistics/statistics.component';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import { GeneratorComponent } from './generator/generator.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { AlarmsComponent } from './alarms/alarms.component';
import {SharedSubjectService} from './shared/shared-subject-service.service';
import {ConfigParams} from './shared/config/config-params';
import { CommentsComponent } from './alarms/components/comments/comments.component';

//
const coreRoutes: Routes = [
  {path: ConfigParams.DASHBOARD_ROUTE , component: StatisticsComponent},
  {path: ConfigParams.HOME_ROUTE , component: StatisticsComponent},
  {path: ConfigParams.GENERATOR_ROUTE , component: GeneratorComponent},
  {path: ConfigParams.ALARMS_ROUTE , component: AlarmsComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    FullLayoutComponent,
    NavbarComponent,
    StatisticsComponent,
    GeneratorComponent,
    AlarmsComponent,
    CommentsComponent,

  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(coreRoutes),
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [HttpClient , SharedSubjectService],
  bootstrap: [AppComponent]

})
export class AppModule { }
