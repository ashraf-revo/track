export class Location {

  constructor(id: string, trackerId: string, date: string, lat: number, lng: number) {
    this.id = id;
    this.trackerId = trackerId;
    this.date = date;
    this.lat = lat;
    this.lng = lng;
  }

  id: string;
  trackerId: string;
  date: string;
  lat: number;
  lng: number;
}
