export class Purchase {
  constructor(
    public id: number,
    public date: string,
    public productName: string,
    public priceForOne: number,
    public amount: number,
    public purchasePrice: number,
    public logistics: number,
    public costPrice: number,
    public batchPrice: number,
    public extra: number
    ) { }
}
