import {Country} from "./country";
import {Town} from "./town";

export class Storage{
    constructor(
        public id: number,
        public country: Country,
        public town: Town
    ) {
    }
}
