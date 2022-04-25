import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'app-list-navigator',
  templateUrl: './list-navigator.component.html',
  styleUrls: ['./list-navigator.component.css']
})
export class ListNavigatorComponent {

    @Input() amountOnPage: number = 1;
    @Input() totalAmount: number = 0;
    @Input() pageShortcutsAmount: number = 3;

    @Output() onChanged = new EventEmitter();

    pageAmount: number = 0;
    currentPageIndex: number = 0;
    pagesShortcuts: number[] = [];

    ngOnChanges() {
        this.pageAmount = Math.ceil(this.totalAmount / this.amountOnPage);
        this.pagesShortcuts = Array(Math.min(this.pageShortcutsAmount, this.pageAmount));
        this.setCurrentPageIndex(Math.min(this.currentPageIndex, this.pageAmount - 1));
    }

    setCurrentPageIndex(newValue: number): void {
        if (newValue < 0 || newValue >= this.pageAmount) return;
        const pageChanged: boolean = this.currentPageIndex !== newValue;
        this.currentPageIndex = newValue;
        this.recalculateShortcuts();
        if(pageChanged)
            this.onChanged.emit(newValue * this.amountOnPage);
    }

    private recalculateShortcuts() {
        let selectedPageShortcutIndex = Math.floor(this.pagesShortcuts.length / 2 - 0.1);
        selectedPageShortcutIndex = Math.min(selectedPageShortcutIndex, this.currentPageIndex);
        if (this.pagesShortcuts.length - selectedPageShortcutIndex > this.pageAmount - this.currentPageIndex) {
            selectedPageShortcutIndex = this.pagesShortcuts.length - (this.pageAmount - this.currentPageIndex);
        }
        for (let i = 0; i < this.pagesShortcuts.length; i++) {
            this.pagesShortcuts[i] = this.currentPageIndex + (i - selectedPageShortcutIndex) + 1;
        }
    }
}
