import { Router } from "express"; // Importa gerenciador de rotas do express
import { welcome } from "../controllers/welcome-controller";
import * as PlayerController from "../controllers/players-controller";
import * as ClubsController from "../controllers/club-controller";

const router = Router();

router.get("/", welcome);
router.get("/players", PlayerController.getPlayer);
router.post("/players", PlayerController.postPlayer);
router.delete("/players/:id", PlayerController.deletePlayer);
router.patch("/players/:id", PlayerController.updatePlayer); // atualização parcial de dados
router.get("/players/:id", PlayerController.getPlayerById); // rota com parâmetro (path param)

router.get("/clubs", ClubsController.getClubs); // lista clubs 

export default router;