import { Request, Response } from "express";

export const welcome = (req: Request, res: Response) => {
  res.status(200).json({ message: "Bem vindo(a) à API da Champions League." });
};
