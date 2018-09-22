export class Request {
    id?: number;
    name: String;
    description: String;
    wayOfResponse: String;
    status: {
        id: number
    };
    userPriority: {
        id: number
    };
    adminPriority: {
        id: number
    };
    department: {
        id: number
    };
    user: {
        id: number
    };
    product: {
        id: number
    };
}
