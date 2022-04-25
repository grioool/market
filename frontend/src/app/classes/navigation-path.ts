export enum NavigationPath {
    LOGIN = "login",
    REGISTRATION = "registration",
    MAIN = "main",
    SUPPLIES = "supplies",
    PURCHASES = "purchases",
    REPORTS = "reports",
    USERS = "users",
    PROFILE = "profile",
    INFORMATION = "information",
    SALES = "sales",
    ORDERS = "orders",
    PASSWORD = "password",
    UPASSWORD = "upassword"
}

export const getFirstUrlToken: (url: string) => string = (url: string): string =>
    url.replace(/^\//, "").replace(/\/.*$/, "")
