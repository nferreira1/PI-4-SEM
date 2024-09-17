export const api = {
    async request(method, url, options = {}) {
        let data = undefined;
        let response = undefined;
        let error = undefined;
        let loading = true;

        if (options.body && typeof options.body === "object") {
            options.body = JSON.stringify(options.body);
            options.headers = {
                "Content-Type": "application/json",
                ...options.headers
            };
        }

        try {
            response = await fetch(`${import.meta.env.VITE_API_URL}${url}`, {
                ...options,
                method
            });

            if (!response.ok) {
                error = await response.json();
                data = undefined;
            } else {
                data = await response.json();
                error = undefined;
            }
        } catch (err) {
            data = undefined;
            error = err;
        } finally {
            loading = false; // Garante que o loading seja atualizado para false aqui
        }

        // Retorna o estado completo após a execução completa (success, error ou finalmente)
        return {data, response, error, loading};
    },

    GET(url, options) {
        return this.request("GET", url, options);
    },

    POST(url, options) {
        return this.request("POST", url, options);
    },

    PUT(url, options) {
        return this.request("PUT", url, options);
    },

    DELETE(url, options) {
        return this.request("DELETE", url, options);
    }
};
