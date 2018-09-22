import {Prioritet} from './Prioritet';
import {Status} from './Status';
import {Department} from './Department';
import {User} from './User';
import {Product} from './Product';

export class Request {
  id?: number;
  name: String;
  description: String;
  date: Date;
  radniNalog: string; // TODO
  wayOfSubmission: String;
  wayOfResponse: String;
  status?: Status;
  userPriority?: Prioritet;
  adminPriority?: Prioritet;
  department: Department;
  user?: User;
  product?: Product;

  skratiNalog(): string {
    if (!this.radniNalog) {
      return '';
    } else {
      return this.radniNalog.substring(0, 10);
    }
  }
}
