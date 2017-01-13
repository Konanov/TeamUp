import { Injectable }     from '@angular/core';
import {Http, Response, Jsonp, URLSearchParams} from '@angular/http';
import { Observable }     from 'rxjs/Observable';
import { Participant } from "./participant.component";
import {Mission} from "./mission.component";

@Injectable()
export class MissionsService {

  private missionsUrl = "http://localhost:8080/missions/";

  private participantsByMission = "http://localhost:8080/participants/byMission/";

  private participantDetailsUrl = "http://localhost:8080/participantInfo/byEmail/";

  private participantDetailsById = "http://localhost:8080/participantInfo/";

  constructor (private http: Http) { }

  getUsersMissions (): Observable<Mission[]> {
    return this.http.get(this.missionsUrl + localStorage.getItem("user_id"))
      .map((res: Response) => res.json())
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

  getParticipantDetails(id: string): Observable<Participant> {

    let params = new URLSearchParams();
    params.set('id', id);

    return this.http
      .get(this.participantDetailsById, { search: params })
      .map((res: Response) => res.json());
  }
}
