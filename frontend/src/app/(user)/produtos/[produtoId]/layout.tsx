export default function Layout({
	children,
}: Readonly<{
	children: React.ReactNode;
}>) {
	return (
		<main className="flex w-full max-w-screen-xl grow flex-col md:mx-auto md:grid md:grid-cols-2 md:gap-8 md:px-5 md:py-8 lg:grid-cols-3">
			{children}
		</main>
	);
}
