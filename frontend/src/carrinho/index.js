import "../globals.css";
import { carregarFooter } from "../components/footer.js";
import { carregarHeaderSimples } from "../components/header-simples.js";

document.addEventListener("DOMContentLoaded", () => {
    carregarHeaderSimples();
    carregarFooter();
});
