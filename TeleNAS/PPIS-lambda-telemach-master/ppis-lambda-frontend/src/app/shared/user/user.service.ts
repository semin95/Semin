import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../../../models/User';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class UserService {

  public API = '//localhost:9090';
  public USER_API = this.API + '/user';

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any> {
    return this.http.get(this.USER_API + '/all');
  }

  getUserById(id: number): Observable<any> {
    return this.http.get(this.USER_API + '/' + id);
  }

  getUserByUsername(username: string): Observable<User> {
    return this.http.get<User>(this.USER_API + '?username=' + username);
  }

  createUser(user: User) {
    return this.http.post(this.USER_API + '/signup', user, httpOptions);
  }
}
