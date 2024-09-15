export const api = {
    async request(method, url, options = {}) {
        let data = null;
        let response = null;
        let error = null;
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

            data = await response.json();
            error = undefined;
        } catch (err) {
            data = undefined;
            error = err || "Erro desconhecido.";
        } finally {
            loading = false;
        }

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
