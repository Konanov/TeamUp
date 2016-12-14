import { Injectable }     from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable }     from 'rxjs/Observable';
import {Participant} from "./participant.component";

@Injectable()
export class ParticipantsService {

  private participantsUrl = "http://localhost:8080/participants/all";

  constructor (private http: Http) {}

  getParticipants (): Observable<Participant[]> {
    return this.http.get(this.participantsUrl)
      .map((res: Response) => res.json())
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }
}
