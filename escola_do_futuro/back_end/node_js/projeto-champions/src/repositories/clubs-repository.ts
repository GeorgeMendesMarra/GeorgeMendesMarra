import { ClubModel } from "../models/club-model";
import fs from "fs/promises"; // módulo para leitura de arquivos do SO

export const findAllClubs = async (): Promise<ClubModel[]> => {
    const data = await fs.readFile("./src/data/clubs.json", "utf-8"); // lê o arquivo respeitando a acentuação
    const clubs: ClubModel[] = JSON.parse(data); //converte data (texto) para objeto (json)
    return clubs;
  };