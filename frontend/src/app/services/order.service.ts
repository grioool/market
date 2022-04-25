import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable, ReplaySubject, Subject, tap} from "rxjs";
import {environment} from "../../environments/environment";
import {Order} from "../classes/order";
import {TablePage} from "../classes/table-page";

@Injectable({
    providedIn: 'root',
})
export class OrderService {

    private url = environment.apiHost;

    private isLoaded: boolean = false;

    private loadedOrders: Subject<Order[]> = new ReplaySubject<Order[]>(1);

    constructor(private http: HttpClient) {
        this.loadedOrders.subscribe(() => this.isLoaded = true);
    }

    public getByPage(dateFrom: Date, shift: number, rowsPerPage: number): Observable<TablePage<Order>> {
        const params = new HttpParams()
            .set('shift', shift)
            .set("dateFrom", dateFrom.toISOString())
            .set('rowsPerPage', rowsPerPage);
        return this.http.get<TablePage<Order>>(this.url + '/ordersByPage', {params});
    }

    public getLoadedOrders(): Observable<Order[]> {
        if(!this.isLoaded)
            this.getOrders().subscribe();
        return this.loadedOrders.asObservable();
    }

    public getOrders(): Observable<Order[]> {
        return this.http.get<Order[]>(this.url + '/orders')
            .pipe(tap(orders => this.loadedOrders.next(orders)));
    }

}
