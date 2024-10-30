import Image from "next/image";

export default function UserLayout({
	children,
}: Readonly<{
	children: React.ReactNode;
}>) {
	return <main className="grow">{children}</main>;
}
