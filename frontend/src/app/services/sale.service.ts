import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable, ReplaySubject, Subject, tap} from "rxjs";
import {environment} from "../../environments/environment";
import {Sale} from "../classes/sale";
import {TablePage} from "../classes/table-page";

@Injectable({
    providedIn: 'root',
})
export class SaleService {

    private url = environment.apiHost;

    private isLoaded: boolean = false;

    private loadedSales: Subject<Sale[]> = new ReplaySubject<Sale[]>(1);

    constructor(private http: HttpClient) {
        this.loadedSales.subscribe(() => this.isLoaded = true);
    }

    public getByPage(dateFrom: Date, shift: number, rowsPerPage: number): Observable<TablePage<Sale>> {
        const params = new HttpParams()
            .set('shift', shift)
            .set("dateFrom", dateFrom.toISOString())
            .set('rowsPerPage', rowsPerPage);
        return this.http.get<TablePage<Sale>>(this.url + '/salesByPage', {params});
    }

    public getLoadedSales(): Observable<Sale[]> {
        if(!this.isLoaded)
            this.getSales().subscribe();
        return this.loadedSales.asObservable();
    }

    public getSales(): Observable<Sale[]> {
        return this.http.get<Sale[]>(this.url + '/sales')
            .pipe(tap(sales => this.loadedSales.next(sales)));
    }

}
