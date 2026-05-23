import { User } from "../../../core/auth/models/user.model";

export interface AuthStateModel {

  user: User | null;

  loading: boolean;

  authenticated: boolean;

  error: string | null;
}