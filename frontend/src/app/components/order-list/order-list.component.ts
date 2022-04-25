import {Component, OnInit, TemplateRef, ViewChild} from "@angular/core";
import {Order} from "../../classes/order";
import {OrderService} from "../../services/order.service";
import {Location} from "@angular/common";
import {TablePage} from "../../classes/table-page";

@Component({
    selector: 'app-order',
    templateUrl: './order-list.component.html',
    styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit {
    title = 'frontend';

    @ViewChild('readOnlyTemplate', {static: false}) readOnlyTemplate: TemplateRef<any> | undefined;
    @ViewChild('editTemplate', {static: false}) editTemplate: TemplateRef<any> | undefined;

    public orders: Array<Order>;

    public isNewRecord: boolean = false;

    public statusMessage: string = "";

    public totalAmount: number = 0;

    public amountOnPage: number = 20;

    public dateFrom: string = new Date(Date.now() - 60 * 60 * 24 * 7).toISOString();

    public currentShift: number = 0;

    constructor(private serv: OrderService,
                private location: Location) {
        this.orders = new Array<Order>();
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
            .subscribe((page: TablePage<Order>) => {
                this.orders = page.items;
                this.totalAmount = page.totalCount;
                this.currentShift = page.currentShift;
            })
    }
}
