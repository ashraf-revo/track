import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HomeComponent} from './Views/home/home.component';
import {LoginComponent} from './Views/login/login.component';
import {RouterModule} from '@angular/router';
import {routes} from './Views/routes';
import {BaseComponent} from './Views/base/base.component';
import {DefaultService} from './Services/default.service';
import {AuthService} from './Services/auth.service';
import {UserService} from './Services/user.service';
import {HttpClientModule, HttpClientXsrfModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {BaseInComponent} from './Views/base-in/base-in.component';
import {BaseOutComponent} from './Views/base-out/base-out.component';
import {NavComponent} from './Component/nav/nav.component';
import {AgmCoreModule} from "@agm/core";
import {TrackComponent} from './Views/track/track.component';
import {TrackerService} from "./Services/tracker.service";
import {MomentModule} from 'angular2-moment';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    BaseComponent,
    BaseInComponent,
    BaseOutComponent,
    NavComponent,
    TrackComponent
  ],
  imports: [
    BrowserModule, HttpClientModule,
    HttpClientXsrfModule.withOptions({
      cookieName: 'XSRF-TOKEN',
      headerName: 'X-XSRF-TOKEN',
    }),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyALyxtTf3GWbF903DLkWnVyrO9ij0m5ilU'
    }),
    FormsModule, RouterModule.forRoot(routes), MomentModule
  ],
  providers: [DefaultService, AuthService, UserService, TrackerService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
