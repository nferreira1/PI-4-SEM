import { Loader2 } from "lucide-react";

export default function Loading() {
	return (
		<div className="pointer-events-auto fixed inset-0 z-[100] flex items-center justify-center bg-black/85">
			<Loader2 className="animate-spin text-primary" size={50} />
		</div>
	);
}
