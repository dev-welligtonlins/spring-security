export interface User {
    id: string;
    login: string;
    role: 'CLIENT' | 'BARBERSHOP'
}