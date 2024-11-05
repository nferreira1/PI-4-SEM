"use client";

import { useEffect } from "react";

export default function Error({
	error,
	reset,
}: {
	error: Error & { digest?: string };
	reset: () => void;
}) {
	useEffect(() => {
		console.error(error);
	}, [error]);

	return (
		<div>
			<p>{error.message}</p>
			<h2>Ocorreu um erro!</h2>
			<button onClick={() => reset()}>tentar novamente</button>
		</div>
	);
}
