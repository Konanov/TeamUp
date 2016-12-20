import { Injectable }     from '@angular/core';
import {Http, Response, Jsonp, URLSearchParams} from '@angular/http';
import { Observable }     from 'rxjs/Observable';
import { Participant } from "./participant.component";

@Injectable()
export class ParticipantsService {

  private participantsUrl = "http://localhost:8080/participants/all";

  private participantDetailsUrl = "http://localhost:8080/participantInfo/";

  constructor (private http: Http) { }

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
      .get(this.participantDetailsUrl, { search: params })
      .map((res: Response) => res.json());
  }
}
