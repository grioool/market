export interface Auth {
  username: string;
  password: string;
  token: string;
  canModify: boolean;
  expiredDate: number;
}
