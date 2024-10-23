/** @type {import('next').NextConfig} */
const nextConfig = {
    images: {
        remotePatterns: [
            {
              protocol: 'http',
              hostname: '191.232.170.198',
              pathname: '/techcommerce/images/**', 
            },
        ],
    }
};

export default nextConfig;
