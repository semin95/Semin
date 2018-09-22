import {User} from './User';
import {Incident} from './Incident';

export class FeedbackIncident {
  id?: number;
  comment: String;
  date: Date;
  user: User;
  incident: Incident;
}
