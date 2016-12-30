import { Injectable }     from '@angular/core';
import {Http, Response, Jsonp, URLSearchParams} from '@angular/http';
import { Observable }     from 'rxjs/Observable';
import { Participant } from "./participant.component";

@Injectable()
export class MissionsService {

  private participantsUrl = "http://localhost:8080/participants/all";

  private participantsByMission = "http://localhost:8080/participants/byMission/";

  private participantDetailsUrl = "http://localhost:8080/participantInfo/byEmail/";

  private participantDetailsById = "http://localhost:8080/participantInfo/";

  participant: Observable<Participant>;

  constructor (private http: Http) { }

  getCurrentParty(): Observable<Participant[]> {
    return this.http.get(this.participantsByMission + localStorage.getItem("currentMissionId"))
      .map((res: Response) => res.json())
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

  getParticipantByEmail(): Observable<Participant> {
    return this.http.get(this.participantDetailsUrl + localStorage.getItem("email"))
      .map((response: Response) => response.json())
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

  getParticipants (): Observable<Participant[]> {
    return this.http.get(this.participantsUrl)
      .map((res: Response) => res.json())
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

  /*getParticipantDetails (params: Params): Observable<Participant> {
   return this.http.get(this.participantDetailsUrl, { search: params })
   .map((res: Response) => res.json());
   }*/

  getParticipantDetails(id: string): Observable<Participant> {

    let params = new URLSearchParams();
    params.set('id', id);

    return this.http
      .get(this.participantDetailsById, { search: params })
      .map((res: Response) => res.json());
  }
}
