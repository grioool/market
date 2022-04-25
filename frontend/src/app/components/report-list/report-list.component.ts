import {Component, OnInit} from '@angular/core';
import {Report} from '../../classes/report';
import {ReportService} from '../../services/report.service';
import {Supply} from "../../classes/supply";
import {SupplyService} from "../../services/supply.service";
import {Location} from "@angular/common";
import {TablePage} from "../../classes/table-page";

@Component({
    selector: 'purchase-root',
    templateUrl: './report-list.component.html',
    styleUrls: ['./report-list.component.css']
})
export class ReportList implements OnInit {
    title = 'frontend';

    reports: Array<Report>;

    isNewRecord: boolean = false;

    statusMessage: string = "";

    public dateFrom: string = new Date(Date.now() - 60 * 60 * 24 * 7).toISOString();

    public dateTo: string = new Date(Date.now()).toISOString();

    public supplies: Supply[] = [];

    public currentShift: number = 0;

    public totalAmount: number = 0;

    public amountOnPage: number = 9;

    public supplyNotFound: string = "Нет данных о поставке";

    constructor(private serv: ReportService,
                public supplyService: SupplyService,
                private location: Location) {
        this.reports = new Array<Report>();
    }

    ngOnInit() {
        this.load();
    }

    public back(): void {
        this.location.back();
    }

    public setPageSelected(shift: number): void {
        this.currentShift = shift;
        this.load();
    }

    public load() {
        this.serv.getByPage(new Date(Date.parse(this.dateFrom)), new Date(Date.parse(this.dateTo)), this.currentShift, this.amountOnPage)
            .subscribe((page: TablePage<Report>) => {
                this.reports = page.items;
                this.totalAmount = page.totalCount;
                this.currentShift = page.currentShift;
            })
    }
}
