import {Locale} from "./locale";
import {Observable, Subject} from "rxjs";

export class LocaleContextHolder {

    private static current: number = 0;

    private static locales: Locale[] = [Locale.ENGLISH, Locale.RUSSIAN];

    private static localeSubject: Subject<Locale> = new Subject<Locale>();

    public static setLocale(newLocale: Locale) {
        LocaleContextHolder.current =
            LocaleContextHolder.locales.findIndex((locale: Locale) => locale.key === newLocale.key)
            || LocaleContextHolder.current;
        this.localeSubject.next(this.getLocale());
    }

    public static getLocale(): Locale {
        return LocaleContextHolder.locales[LocaleContextHolder.current];
    }

    public static nextLocale() {
        this.current = (this.current + 1) % this.locales.length;
        this.localeSubject.next(this.getLocale());
    }

    public static locale(): Observable<Locale> {
        return this.localeSubject.asObservable();
    }
}
