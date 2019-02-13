import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from "@angular/router";
import {map, mergeMap} from "rxjs/operators";
import {TrackerService} from "../../Services/tracker.service";
import {Location} from "../../Domain/Location";
import {Call} from "../../Domain/call";

@Component({
  selector: 't-track',
  templateUrl: './track.component.html',
  styleUrls: ['./track.component.css']
})
export class TrackComponent implements OnInit {
  public zoom = 15;
  public locations: Location[] = [];
  public calls: Call[] = [];

  constructor(private _activatedRoute: ActivatedRoute,
              private _trackerService: TrackerService) {

  }

  ngOnInit() {
    this._activatedRoute.params.pipe(map((it: Params) => it['id']), mergeMap(it => this._trackerService.locations(it)))
      .subscribe(it => {
        this.locations = it;
      });
    this._activatedRoute.params.pipe(map((it: Params) => it['id']), mergeMap(it => this._trackerService.calls(it)))
      .subscribe(it => {
        this.calls = it;
      });

  }
}
