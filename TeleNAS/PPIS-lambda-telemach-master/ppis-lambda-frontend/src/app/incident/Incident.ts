export class Incident {
    name: String;
    description: String;
    wayOfResponse: String;
    wayOfSubmition: String;
    status: {
        id: number;
    };
    priority: {
        id: number;
    };
    user: {
        id: number;
    };
    product: {
        id: number;
    }
}