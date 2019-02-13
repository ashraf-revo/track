import {Component, OnInit} from '@angular/core';
import {TrackerService} from "../../Services/tracker.service";
import {Tracker} from "../../Domain/Tracker";

@Component({
  selector: 't-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public trackers: Tracker[] = [];

  constructor(private _trackerService: TrackerService) {
  }

  ngOnInit() {
    this._trackerService.trackers().subscribe(it => {
      this.trackers = it;
    })
  }

}
