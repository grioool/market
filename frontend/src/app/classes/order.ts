export class Order {
    constructor(
        public gNumber: string,
        public date: number,
        public lastChangeDate: number,
        public supplierArticle: string,
        public techSize: string,
        public barcode: string,
        public quantity: number,
        public totalPrice: number,
        public discountPercent: number,
        public warehouseName: string,
        public oblast: string,
        public incomeID: number,
        public odid: number,
        public nmid: number,
        public subject: string,
        public category: string,
        public brand: string,
        public is_cancel: number
    ) {
    }
}
