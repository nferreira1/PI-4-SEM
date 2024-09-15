import "./globals.css";
import { carregarFooter } from "./components/footer.js";
import { carregarHeader } from "./components/header.js";

document.addEventListener("DOMContentLoaded", () => {
    carregarHeader();
    carregarFooter();
});