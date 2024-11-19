const { execSync } = require("child_process");

const NEXT_PUBLIC_API_URL = process.env.NEXT_PUBLIC_API_URL;
if (!NEXT_PUBLIC_API_URL) {
  console.error("Erro: NEXT_PUBLIC_API_URL não está definida.");
  process.exit(1);
}

console.log(`Usando NEXT_PUBLIC_API_URL: ${NEXT_PUBLIC_API_URL}`);

execSync(
  `npx openapi-typescript ${NEXT_PUBLIC_API_URL}/v1/api-docs -o ./.swagger/v1/api-docs.d.ts && next build`,
  { stdio: "inherit" }
);
