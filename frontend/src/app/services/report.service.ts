import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Report} from "../classes/report";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {TablePage} from "../classes/table-page";

@Injectable({
    providedIn: 'root',
})
export class ReportService {

    private url = environment.apiHost;

    constructor(private http: HttpClient) {
    }

    public getByPage(dateFrom: Date, dateTo: Date, shift: number, rowsPerPage: number): Observable<TablePage<Report>> {
        const params = new HttpParams()
            .set("dateFrom", dateFrom.toISOString())
            .set("dateTo", dateTo.toISOString())
            .set('shift', shift)
            .set('rowsPerPage', rowsPerPage);
        return this.http.get<TablePage<Report>>(this.url + '/reportsByPage', {params});
    }

    public getReports() {
        return this.http.get<Array<Report>>(this.url + '/reports');
    }

    public createReport(report: Report) {
        const myHeaders = new HttpHeaders().set("Content-Type", "application/json");
        return this.http.post<Report>(this.url + '/reports', JSON.stringify(report), {headers: myHeaders});
    }

    public updateReport(report: Report) {
        const myHeaders = new HttpHeaders().set("Content-Type", "application/json");
        return this.http.put<Report>(this.url + '/reports', JSON.stringify(report), {headers: myHeaders});
    }

    public deleteReport(id: number) {
        return this.http.delete<Report>(this.url + '/reports/' + id);
    }

}
