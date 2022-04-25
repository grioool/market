import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {Purchase} from '../../classes/purchase';
import {PurchaseService} from '../../services/purchase.service';
import {isPresent} from "../../../util";
import {Location} from "@angular/common";
import {TablePage} from "../../classes/table-page";

@Component({
    selector: 'purchase-root',
    templateUrl: './purchase-list.component.html',
    styleUrls: ['./purchase-list.component.css']
})
export class PurchaseList implements OnInit {
    title = 'frontend';

    @ViewChild('readOnlyTemplate', {static: false}) readOnlyTemplate: TemplateRef<any> | undefined;
    @ViewChild('editTemplate', {static: false}) editTemplate: TemplateRef<any> | undefined;

    public editedPurchase: Purchase = null;

    public purchases: Array<Purchase>;

    public isNewRecord: boolean = false;

    public statusMessage: string = "";

    public totalAmount: number = 0;

    public amountOnPage: number = 5;

    constructor(private serv: PurchaseService,
                private location: Location) {
        this.purchases = new Array<Purchase>();
    }

    ngOnInit() {
        this.loadPurchases();
    }

    private loadPurchases() {
        this.setPageSelected(0);
    }

    public addPurchase() {
        this.editedPurchase = new Purchase(0, "", "", 0, 0, 0, 0, 0, 0, 0);
        this.purchases.push(this.editedPurchase);
        this.isNewRecord = true;
    }

    public editPurchase(purchase: Purchase) {
        this.editedPurchase = new Purchase(purchase.id, purchase.date, purchase.productName, purchase.priceForOne, purchase.amount, purchase.purchasePrice, purchase.logistics, purchase.costPrice, purchase.batchPrice, purchase.extra);
    }

    public loadTemplate(purchase: Purchase) {
        if (this.editedPurchase && this.editedPurchase.id === purchase.id) {
            return this.editTemplate;
        } else {
            return this.readOnlyTemplate;
        }
    }

    public savePurchase() {
        if(this.editedPurchase.date) {
            this.editedPurchase.date = new Date(Date.parse(this.editedPurchase.date)).toISOString();
        } else {
            this.editedPurchase.date = new Date().toISOString();
        }
        if (this.isNewRecord) {
            this.serv.createPurchase(this.editedPurchase).subscribe(data => {
                this.statusMessage = 'Данные успешно добавлены';
                this.loadPurchases();
            });
            this.isNewRecord = false;
            this.editedPurchase = null;
            this.loadPurchases();
        } else {
            this.serv.updatePurchase(this.editedPurchase as Purchase).subscribe(data => {
                this.statusMessage = 'Данные успешно обновлены';
                this.loadPurchases();
            });
            this.editedPurchase = null;
            this.loadPurchases();
        }
    }

    public cancel() {
        if (this.isNewRecord) {
            this.purchases.pop();
            this.isNewRecord = false;
        }
        this.editedPurchase = null;
    }

    public deletePurchase(id: number) {
        this.serv.deletePurchase(id).subscribe(data => {
            this.statusMessage = 'Данные успешно удалены';
            this.loadPurchases();
        });
    }

    public isReadOnly(purchase: Purchase): boolean {
        return !this.isEditable(purchase);
    }

    public isEditable(purchase: Purchase): boolean {
        return isPresent(this.editedPurchase) && this.editedPurchase.id === purchase.id;
    }

    public back(): void {
        this.location.back();
    }

    public setPageSelected(shift: number): void {
        this.serv.getByPage(shift, this.amountOnPage)
            .subscribe((page: TablePage<Purchase>) => {
                this.purchases = page.items;
                this.totalAmount = page.totalCount;
            })
    }
}
