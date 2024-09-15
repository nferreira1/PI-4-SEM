export function carregarLoading() {
    document.querySelector("footer").innerHTML = `
        <div class="">
            <svg class="lucide lucide-loader-circle" 
                    fill="none" height="24" stroke="currentColor" stroke-linecap="round"
                    stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24" width="24"
                    xmlns="http://www.w3.org/2000/svg"
            >
                <path d="M21 12a9 9 0 1 1-6.219-8.56"/>
            </svg>        
       </div>
    `;
}