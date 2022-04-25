export class Locale {

    public static readonly ENGLISH: Locale = new Locale("en", "EN")
    public static readonly RUSSIAN: Locale = new Locale("ru", "RU")

    constructor(public readonly key: string,
                public readonly label: string) {
    }

}
