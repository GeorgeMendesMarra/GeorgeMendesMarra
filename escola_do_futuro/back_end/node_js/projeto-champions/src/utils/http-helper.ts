import { HttpResponse } from "../models/http-response-model";

// Sempre que utilizar async -> retornar uma Promise<>
export const ok = async (data: any): Promise<HttpResponse> => {
    return {
        statusCode: 200,
        body: data
    };
};

export const created = async (): Promise<HttpResponse> => {
    return {
        statusCode: 201,
        body: {
            message: "Successful",
        },
    };
};

export const noContent = async (): Promise<HttpResponse> => {
    return {
        statusCode: 204,
        body: null
    };
};

export const badRequest = async (): Promise<HttpResponse> => {
    return {
        statusCode: 400,
        body: null
    };
};