import { PlayerModel } from "../models/player-model";
import { StatisticsModel } from "../models/statistics-model";

// banco de dados em memória
const database: PlayerModel[] = [
  {
    id: 1,
    name: "Lionel Messi",
    club: "Inter Miami",
    nationality: "Argentina",
    position: "Forward",
    statistics: {
      Overall: 93,
      Pace: 80,
      Shooting: 92,
      Passing: 91,
      Dribbling: 95,
      Defending: 34,
      Physical: 65,
    },
  },
  {
    id: 2,
    name: "Cristiano Ronaldo",
    club: "Al Nassr",
    nationality: "Portugal",
    position: "Forward",
    statistics: {
      Overall: 88,
      Pace: 81,
      Shooting: 92,
      Passing: 78,
      Dribbling: 84,
      Defending: 34,
      Physical: 75,
    },
  },
  {
    id: 3,
    name: "Kevin De Bruyne",
    club: "Manchester City",
    nationality: "Belgium",
    position: "Midfielder",
    statistics: {
      Overall: 91,
      Pace: 74,
      Shooting: 85,
      Passing: 93,
      Dribbling: 87,
      Defending: 64,
      Physical: 78,
    },
  },
  {
    id: 4,
    name: "Kylian Mbappe",
    club: "Paris Saint-Germain",
    nationality: "France",
    position: "Forward",
    statistics: {
      Overall: 91,
      Pace: 97,
      Shooting: 89,
      Passing: 81,
      Dribbling: 92,
      Defending: 36,
      Physical: 76,
    },
  },
  {
    id: 5,
    name: "Robert Lewandowski",
    club: "Barcelona",
    nationality: "Poland",
    position: "Forward",
    statistics: {
      Overall: 90,
      Pace: 77,
      Shooting: 91,
      Passing: 79,
      Dribbling: 85,
      Defending: 42,
      Physical: 83,
    },
  },
  {
    id: 6,
    name: "Virgil van Dijk",
    club: "Liverpool",
    nationality: "Netherlands",
    position: "Defender",
    statistics: {
      Overall: 89,
      Pace: 75,
      Shooting: 60,
      Passing: 71,
      Dribbling: 71,
      Defending: 91,
      Physical: 86,
    },
  },
  {
    id: 7,
    name: "Neymar Jr.",
    club: "Al-Hilal",
    nationality: "Brazil",
    position: "Forward",
    statistics: {
      Overall: 89,
      Pace: 84,
      Shooting: 83,
      Passing: 85,
      Dribbling: 92,
      Defending: 37,
      Physical: 60,
    },
  },
  {
    id: 8,
    name: "Mohamed Salah",
    club: "Liverpool",
    nationality: "Egypt",
    position: "Forward",
    statistics: {
      Overall: 90,
      Pace: 93,
      Shooting: 86,
      Passing: 81,
      Dribbling: 89,
      Defending: 45,
      Physical: 75,
    },
  },
  {
    id: 9,
    name: "Sadio Mane",
    club: "Al Nassr",
    nationality: "Senegal",
    position: "Forward",
    statistics: {
      Overall: 88,
      Pace: 91,
      Shooting: 83,
      Passing: 78,
      Dribbling: 87,
      Defending: 44,
      Physical: 76,
    },
  },
  {
    id: 10,
    name: "Luka Modric",
    club: "Real Madrid",
    nationality: "Croatia",
    position: "Midfielder",
    statistics: {
      Overall: 87,
      Pace: 69,
      Shooting: 75,
      Passing: 89,
      Dribbling: 87,
      Defending: 71,
      Physical: 64,
    },
  },
  {
    id: 11,
    name: "N'Golo Kante",
    club: "Al-Ittihad",
    nationality: "France",
    position: "Midfielder",
    statistics: {
      Overall: 86,
      Pace: 80,
      Shooting: 66,
      Passing: 74,
      Dribbling: 81,
      Defending: 87,
      Physical: 84,
    },
  },
  {
    id: 12,
    name: "Sergio Ramos",
    club: "Sevilla",
    nationality: "Spain",
    position: "Defender",
    statistics: {
      Overall: 86,
      Pace: 68,
      Shooting: 64,
      Passing: 73,
      Dribbling: 68,
      Defending: 87,
      Physical: 83,
    },
  },
  {
    id: 13,
    name: "Harry Kane",
    club: "Bayern Munich",
    nationality: "England",
    position: "Forward",
    statistics: {
      Overall: 90,
      Pace: 69,
      Shooting: 93,
      Passing: 83,
      Dribbling: 82,
      Defending: 47,
      Physical: 83,
    },
  },
  {
    id: 14,
    name: "Erling Haaland",
    club: "Manchester City",
    nationality: "Norway",
    position: "Forward",
    statistics: {
      Overall: 91,
      Pace: 89,
      Shooting: 91,
      Passing: 70,
      Dribbling: 80,
      Defending: 45,
      Physical: 88,
    },
  },
  {
    id: 15,
    name: "Jan Oblak",
    club: "Atletico Madrid",
    nationality: "Slovenia",
    position: "Goalkeeper",
    statistics: {
      Overall: 89,
      Pace: 46,
      Shooting: 17,
      Passing: 31,
      Dribbling: 41,
      Defending: 55,
      Physical: 85,
    },
  },
  {
    id: 16,
    name: "Manuel Neuer",
    club: "Bayern Munich",
    nationality: "Germany",
    position: "Goalkeeper",
    statistics: {
      Overall: 88,
      Pace: 57,
      Shooting: 27,
      Passing: 36,
      Dribbling: 47,
      Defending: 50,
      Physical: 80,
    },
  },
  {
    id: 17,
    name: "Joshua Kimmich",
    club: "Bayern Munich",
    nationality: "Germany",
    position: "Midfielder",
    statistics: {
      Overall: 89,
      Pace: 72,
      Shooting: 72,
      Passing: 87,
      Dribbling: 82,
      Defending: 83,
      Physical: 79,
    },
  },
  {
    id: 18,
    name: "Alisson Becker",
    club: "Liverpool",
    nationality: "Brazil",
    position: "Goalkeeper",
    statistics: {
      Overall: 89,
      Pace: 53,
      Shooting: 14,
      Passing: 32,
      Dribbling: 45,
      Defending: 56,
      Physical: 83,
    },
  },
  {
    id: 19,
    name: "Karim Benzema",
    club: "Al-Ittihad",
    nationality: "France",
    position: "Forward",
    statistics: {
      Overall: 89,
      Pace: 75,
      Shooting: 89,
      Passing: 82,
      Dribbling: 87,
      Defending: 41,
      Physical: 78,
    },
  },
  {
    id: 20,
    name: "Thibaut Courtois",
    club: "Real Madrid",
    nationality: "Belgium",
    position: "Goalkeeper",
    statistics: {
      Overall: 90,
      Pace: 49,
      Shooting: 15,
      Passing: 32,
      Dribbling: 40,
      Defending: 54,
      Physical: 85,
    },
  },
];

export const findAllPlayers = async (): Promise<PlayerModel[]> => {
  return database;
};

// Promise<PlayerModel | undefined> => o retorno pode ser os dados ou indefinido
export const findPlayerById = async (
  id: number
): Promise<PlayerModel | undefined> => {
  return database.find((player) => player.id === id);
};

export const insertPlayer = async (player: PlayerModel) => {
  database.push(player);
};

export const deleteOnePlayer = async (id: number) => {
  const index = database.findIndex((p) => p.id === id); // verifica a posição do item no array
  // se localizar o item, remove-o
  if (index !== -1) {
    database.splice(index, 1);
    return true;
  }
  return false;
};

export const findAndModify = async (
  id: number,
  statistics: StatisticsModel
): Promise<PlayerModel> => {
  const playerIndex = database.findIndex((player) => player.id === id); // verifica a posição do item no array
  // se localizar o item, atualiza-o
  if (playerIndex !== -1) database[playerIndex].statistics = statistics;

  return database[playerIndex];
};
