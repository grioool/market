import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Purchase} from '../classes/purchase';
import {Observable, ReplaySubject, Subject, tap} from "rxjs";
import {environment} from "../../environments/environment";
import {TablePage} from "../classes/table-page";

@Injectable({
    providedIn: 'root',
})
export class PurchaseService {

    private url = environment.apiHost;

    private isLoaded: boolean = false;

    private loadedPurchases: Subject<Purchase[]> = new ReplaySubject<Purchase[]>(1);

    constructor(private http: HttpClient) {
        this.loadedPurchases.subscribe(() => this.isLoaded = true);
    }

    public getByPage(shift: number, rowsPerPage: number): Observable<TablePage<Purchase>> {
        const params = new HttpParams()
            .set('shift', shift)
            .set('rowsPerPage', rowsPerPage);
        return this.http.get<TablePage<Purchase>>(this.url + '/purchasesByPage', {params});
    }

    public getLoadedPurchases(): Observable<Purchase[]> {
        if(!this.isLoaded)
            this.getPurchases().subscribe();
        return this.loadedPurchases.asObservable();
    }

    public getPurchases(): Observable<Purchase[]> {
        return this.http.get<Purchase[]>(this.url + '/purchases')
            .pipe(tap(purchases => this.loadedPurchases.next(purchases)));
    }

    public createPurchase(purchase: Purchase) {
        return this.http.post<Purchase>(this.url + '/purchases', purchase);
    }

    public updatePurchase(purchase: Purchase) {
        return this.http.put<Purchase>(this.url + '/purchases', purchase);
    }

    public deletePurchase(id: number) {
        return this.http.delete<Purchase>(this.url + '/purchases/' + id);
    }

}
