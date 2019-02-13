import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {DefaultService} from "./default.service";
import {Observable} from "rxjs";
import {Tracker} from "../Domain/Tracker";
import {Call} from "../Domain/call";
import {Location} from "../Domain/Location";

@Injectable({
  providedIn: 'root'
})
export class TrackerService {
  private url = '/api/admin/';

  constructor(private _http: HttpClient, private _defaultService: DefaultService) {
    this.url = this._defaultService.url + this.url;
  }

  public locations(id: string): Observable<Location[]> {
    return this._http.get<Location[]>(this.url + "location/" + id)
  }

  public trackers(): Observable<Tracker[]> {
    return this._http.get<Tracker[]>(this.url + "tracker")
  }

  public calls(id: string): Observable<Call[]> {
    return this._http.get<Call[]>(this.url + "call/" + id)
  }
}
