import "../globals.css";
import { carregarFooter } from "../components/footer.js";
import { carregarHeaderSimples } from "../components/header-simples.js";

document.addEventListener("DOMContentLoaded", () => {
    carregarHeaderSimples();
    carregarFooter();

    const buttonAlterarSenha = document.getElementById("buttonAlterarSenha");
    const inputSenha = document.getElementById("inputSenha");

    const eyeIcon = `
        <svg class="lucide lucide-eye" fill="none" height="20" stroke="currentColor" stroke-linecap="round"
             stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24" width="20"
             xmlns="http://www.w3.org/2000/svg">
            <path d="M2.062 12.348a1 1 0 0 1 0-.696 10.75 10.75 0 0 1 19.876 0 1 1 0 0 1 0 .696 10.75 10.75 0 0 1-19.876 0"/>
            <circle cx="12" cy="12" r="3"/>
        </svg>
    `;

    const eyeOffIcon = `
        <svg class="lucide lucide-eye-off" fill="none" height="20" stroke="currentColor" stroke-linecap="round"
             stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24" width="20"
             xmlns="http://www.w3.org/2000/svg">
            <path d="M10.733 5.076a10.744 10.744 0 0 1 11.205 6.575 1 1 0 0 1 0 .696 10.747 10.747 0 0 1-1.444 2.49"/>
            <path d="M14.084 14.158a3 3 0 0 1-4.242-4.242"/>
            <path d="M17.479 17.499a10.75 10.75 0 0 1-15.417-5.151 1 1 0 0 1 0-.696 10.75 10.75 0 0 1 4.446-5.143"/>
            <path d="m2 2 20 20"/>
        </svg>
    `;

    buttonAlterarSenha.innerHTML = eyeIcon;

    const handleAlterarTipoSenha = () => {
        const password = inputSenha.type === "password";
        inputSenha.type = password ? "text" : "password";
        buttonAlterarSenha.innerHTML = password ? eyeOffIcon : eyeIcon;
    };

    buttonAlterarSenha.addEventListener("click", handleAlterarTipoSenha);
});
