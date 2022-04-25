import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {Sale} from "../../classes/sale";
import {SaleService} from "../../services/sale.service";
import {Location} from "@angular/common";
import {TablePage} from "../../classes/table-page";

@Component({
    selector: 'app-sale',
    templateUrl: './sale-list.component.html',
    styleUrls: ['./sale-list.component.css']
})
export class SaleListComponent implements OnInit {

    title = 'frontend';

    @ViewChild('readOnlyTemplate', {static: false}) readOnlyTemplate: TemplateRef<any> | undefined;
    @ViewChild('editTemplate', {static: false}) editTemplate: TemplateRef<any> | undefined;

    public sales: Array<Sale>;

    public isNewRecord: boolean = false;

    public statusMessage: string = "";

    public totalAmount: number = 0;

    public amountOnPage: number = 20;

    public dateFrom: string = new Date(Date.now() - 60 * 60 * 24 * 7).toISOString();

    public currentShift: number = 0;

    constructor(private serv: SaleService,
                private location: Location) {
        this.sales = new Array<Sale>();
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
        this.serv.getByPage(new Date(Date.parse(this.dateFrom)), this.currentShift, this.amountOnPage)
            .subscribe((page: TablePage<Sale>) => {
                this.sales = page.items;
                this.totalAmount = page.totalCount;
                this.currentShift = page.currentShift;
            })
    }
}
