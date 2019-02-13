import {Routes} from "@angular/router";
import {BaseComponent} from "./base/base.component";
import {BaseInComponent} from "./base-in/base-in.component";
import {BaseOutComponent} from "./base-out/base-out.component";
import {LoginComponent} from "./login/login.component";
import {HomeComponent} from "./home/home.component";
import {TrackComponent} from "./track/track.component";

export const routes: Routes = [
  {
    path: '', component: BaseComponent, children: [
      {
        path: '', component: BaseOutComponent, children: [
          {path: '', pathMatch: 'full', redirectTo: 'login'},
          {path: 'login', component: LoginComponent}
        ]
      },
      {
        path: '', component: BaseInComponent, children: [
          {path: 'home', component: HomeComponent},
          {path: 'track/:id', component: TrackComponent},
        ]
      }, {path: '**', redirectTo: ''}]
  },
];
