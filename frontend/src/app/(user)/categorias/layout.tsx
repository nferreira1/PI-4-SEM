export default function Layout({
	children,
}: Readonly<{
	children: React.ReactNode;
}>) {
	return (
		<main className="mx-auto w-full max-w-screen-xl grow px-5 py-6">
			{children}
		</main>
	);
}
