import "./globals.css";
import { carregarFooter } from "./components/footer.js";
import { carregarHeader } from "./components/header.js";

document.addEventListener("DOMContentLoaded", () => {

    const paginas = [
        "/",
        "/404/",
        "/categorias/",
        "/produtos/"
    ];

    if (window.location.pathname === "/404/") {
        return;
    }

    carregarHeader();
    carregarFooter();
});
