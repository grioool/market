import {Observable, ReplaySubject, Subject, tap} from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Storage} from "../classes/storage";
import {environment} from "../../environments/environment";

@Injectable({
    providedIn: 'root',
})
export class StorageService {

    private url = environment.apiHost;

    private isLoaded: boolean = false;

    private loadedStorages: Subject<Storage[]> = new ReplaySubject<Storage[]>(1);

    constructor(private http: HttpClient) {
        this.loadedStorages.subscribe(() => this.isLoaded = true);
    }

    public getLoadedStorages(): Observable<Storage[]> {
        if(!this.isLoaded)
            this.getStorages().subscribe();
        return this.loadedStorages.asObservable();
    }

    public getStorages(): Observable<Storage[]> {
        return this.http.get<Storage[]>(this.url + '/storages')
            .pipe(tap(storages => this.loadedStorages.next(storages)));
    }
}
