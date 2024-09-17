export const toast = {
    success(title, description) {
        showToast(title, description, "success");
    },
    error(title, description) {
        showToast(title, description, "error");
    },
    info(title, description) {
        showToast(title, description, "info");
    },
    warning(title, description) {
        showToast(title, description, "warning");
    }
};

function showToast(title, description, type) {
    if (!title && !description) {
        console.error("O Toast requer pelo menos um título ou descrição.");
        return;
    }

    let toastContainer = document.getElementById("toast-container");
    if (!toastContainer) {
        toastContainer = document.createElement("div");
        toastContainer.id = "toast-container";
        toastContainer.classList.add(
            "fixed",
            "z-50",
            "bottom-4",
            "left-1/2",
            "transform",
            "-translate-x-1/2",
            "flex",
            "flex-col",
            "space-y-3",
            "md:left-auto",
            "md:right-4",
            "md:translate-x-0"
        );
        document.body.appendChild(toastContainer);
    }

    const toastElement = document.createElement("div");
    toastElement.classList.add(
        "toast",
        "flex",
        "items-center",
        "p-4",
        "rounded-lg",
        "shadow-lg",
        "border",
        "min-w-[340px]",
        "max-w-[340px]",
        "animate-fadein"
    );

    const colors = getToastColorClass(type);
    toastElement.style.backgroundColor = colors.backgroundColor;
    toastElement.style.borderColor = colors.borderColor;
    toastElement.style.color = colors.color;

    const icon = getIconSVG(type);

    const iconElement = document.createElement("div");
    iconElement.classList.add("mr-3");
    iconElement.innerHTML = icon;

    const content = document.createElement("span");
    content.classList.add("toast-content", "flex", "flex-col");

    if (title) {
        const titleElement = document.createElement("span");
        titleElement.classList.add("font-semibold", "text-sm");
        titleElement.textContent = title;
        content.appendChild(titleElement);
    }

    if (description) {
        const descriptionElement = document.createElement("div");
        descriptionElement.classList.add("text-sm", "truncate", "max-w-[280px]");
        descriptionElement.textContent = description;
        content.appendChild(descriptionElement);
    }

    toastElement.appendChild(iconElement);
    toastElement.appendChild(content);

    toastContainer.appendChild(toastElement);

    // setTimeout(() => {
    //     toastElement.classList.add("animate-fadeout");
    //     setTimeout(() => {
    //         toastElement.remove();
    //     }, 300);
    // }, 5000);
}

function getToastColorClass(type) {
    switch (type) {
        case "success":
            return {backgroundColor: "#001F0F", borderColor: "#003D1C", color: "#59F3A6"};
        case "error":
            return {backgroundColor: "#2D0607", borderColor: "#4D0408", color: "#FF9EA1"};
        case "info":
            return {backgroundColor: "#000D1F", borderColor: "#00113D", color: "#5896F3"};
        case "warning":
            return {backgroundColor: "#1D1F00", borderColor: "#3D3D00", color: "#F3CF58"};
        default:
            return {backgroundColor: "#fff", borderColor: "#000", color: "#000"};
    }
}

function getIconSVG(type) {
    switch (type) {
        case "success":
            return `
                <svg width="24" height="24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="text-green-500">
                    <path d="M9 12l2 2 4-4"></path>
                    <circle cx="12" cy="12" r="10"></circle>
                </svg>
            `;
        case "error":
            return `
                <svg width="24" height="24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="text-red-500">
                    <circle cx="12" cy="12" r="10"></circle>
                    <path d="M15 9l-6 6M9 9l6 6"></path>
                </svg>
            `;
        case "info":
            return `
                <svg width="24" height="24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="text-blue-500">
                    <circle cx="12" cy="12" r="10"></circle>
                    <path d="M12 16v-4M12 8h.01"></path>
                </svg>            
            `;
        case "warning":
            return `
                <svg width="24" height="24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="text-yellow-500">
                    <path d="M10.29 3.86l-7.89 13.61A1 1 0 0 0 3.11 19h17.78a1 1 0 0 0 .86-1.53L13.71 3.86a1 1 0 0 0-1.71 0z"></path>
                    <line x1="12" y1="9" x2="12" y2="13"></line><line x1="12" y1="17" x2="12.01" y2="17"></line>
                </svg>
            `;
        default:
            return "";
    }
}