import { Request, Response } from "express";
import * as service from "../services/players-service";
import { StatisticsModel } from "../models/statistics-model";

export const getPlayer = async (req: Request, res: Response) => {
  const httpResponse = await service.getPlayerService(); // retorna a resposta completa (service)
  res.status(httpResponse.statusCode).json(httpResponse.body);
};

export const getPlayerById = async (req: Request, res: Response) => {
  const id = parseInt(req.params.id); // param.id -> mesmo nome de parâmetro passado na rota.
  const httpResponse = await service.getPlayerByIdService(id); // retorna a resposta completa
  res.status(httpResponse.statusCode).json(httpResponse.body);
};

export const postPlayer = async (req: Request, res: Response) => {
  const bodyValue = req.body; // pega todos os valores passados no body.
  const httpResponse = await service.createPlayerService(bodyValue);
  if (httpResponse) res.status(httpResponse.statusCode).json(httpResponse.body);
};

export const deletePlayer = async (req: Request, res: Response) => {
  const id = parseInt(req.params.id); // param.id -> mesmo nome de parâmetro passado na rota.
  const httpResponse = await service.deletePlayerService(id);
  res.status(httpResponse.statusCode).json(httpResponse.body);
};

export const updatePlayer = async (req: Request, res: Response) => {
  const id = parseInt(req.params.id); // pega id informado
  const bodyValue: StatisticsModel = req.body; // pega os dados passados no body
  const httpResponse = await service.updatePlayerService(id, bodyValue); // atualiza jogador
  res.status(httpResponse.statusCode).json(httpResponse.body);
};
