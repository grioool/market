import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {User} from '../classes/user';
import {environment} from "../../environments/environment";
import {Observable, ReplaySubject, Subject, tap} from "rxjs";
import {TablePage} from "../classes/table-page";

@Injectable({
    providedIn: 'root',
})
export class UserService {

    private url = environment.apiHost;

    private isLoaded: boolean = false;

    private loadedUsers: Subject<User[]> = new ReplaySubject<User[]>(1);

    constructor(private http: HttpClient) {
        this.loadedUsers.subscribe(() => this.isLoaded = true);
    }

    public getByPage(shift: number, rowsPerPage: number): Observable<TablePage<User>> {
        const params = new HttpParams()
            .set('shift', shift)
            .set('rowsPerPage', rowsPerPage);
        return this.http.get<TablePage<User>>(this.url + '/admin/usersByPage', {params});
    }

    public getUser(id: string) {
        return this.http.get<User>(this.url + '/admin/users/' + id);
    }

    public getUserInformation() {
        return this.http.get<User>(this.url + '/users/information');
    }

    public createUser(user: User) {
        return this.http.post<User>(this.url + '/admin/users', user);
    }

    public updateUser(user: User) {
        return this.http.put<User>(this.url + '/admin/users', user);
    }

    public deleteUser(id: number) {
        return this.http.delete<User>(this.url + '/admin/users/' + id);
    }

    public getLoadedUsers(): Observable<User[]> {
        if (!this.isLoaded)
            this.getUsers().subscribe();
        return this.loadedUsers.asObservable();
    }

    public getUsers(): Observable<User[]> {
        return this.http.get<User[]>(this.url + '/admin/users')
            .pipe(tap(users => this.loadedUsers.next(users)));
    }

}
