/** @type {import('next').NextConfig} */
const nextConfig = {
	images: {
		remotePatterns: [
			{
				protocol: "http",
				hostname: "104.41.34.213",
				pathname: "/techcommerce/images/**",
			},
		],
	},
    output: "standalone"
};

export default nextConfig;
